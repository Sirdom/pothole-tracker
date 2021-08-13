package com.techelevator.dao;

import com.fasterxml.jackson.databind.ser.Serializers;
import com.techelevator.model.PotholeInformation;
import com.techelevator.model.Schedule;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.SqlLobValue;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import javax.xml.bind.DatatypeConverter;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.List;

@Component
public class JdbcPotholeInformation implements PotholeInformationDAO, ResultSetExtractor<String> {

    private JdbcTemplate jdbcTemplate;

//    public JdbcPotholeInformation(){};

    public JdbcPotholeInformation(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<PotholeInformation> getPotholes() {
        String sql = "SELECT id, date_created, longitude, latitude, s.severity, s.status, picture FROM pothole_information p " +
                "JOIN  schedule s ON p.id = s.pothole_id " +
                "ORDER BY s.severity DESC";
//        SqlRowSet result = jdbcTemplate.queryForRowSet(sql);
//        ArrayList<PotholeInformation> potholes = new ArrayList<>();
//        try {
//            while(result.next()) {
//                PotholeInformation potholeInformation = mapRowToPotholeInformation(result);
//                potholes.add(potholeInformation);
//            }
//        }  catch (DataAccessException ex) {
//            System.out.println(ex.getMessage());
//        }
//        return potholes;

        return jdbcTemplate.query(sql, new RowMapper<PotholeInformation>(){
            @Override
            public PotholeInformation mapRow(ResultSet rs, int rowNumber) throws SQLException {
                PotholeInformation p = new PotholeInformation();
                p.setPotholeId(rs.getInt(1));
                p.setDateCreated(rs.getDate(2).toLocalDate());
                p.setLongitude(rs.getDouble(3));
                p.setLatitude(rs.getDouble(4));
                p.setSeverity(rs.getInt(5));
                p.setStatus(rs.getString(6));
                if (rs.getBytes(7) != null) {
                    byte[] picture = rs.getBytes(7);

                    String pictureString = Base64.getEncoder().encodeToString(picture);

                    String type  = detectType(pictureString);

                    p.setPicture("data:" + type + ";base64, " + pictureString);
                }

                return p;
            }
        });
    }

    @Override
    public void deletePothole(int id) {
        String sql = "DELETE FROM claims WHERE pothole_id = ?";
        jdbcTemplate.update(sql, id);
        
        sql = "DELETE FROM schedule WHERE pothole_id = ?";
        jdbcTemplate.update(sql, id);

        sql = "DELETE FROM pothole_information WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }

    @Override
    public void createReport(PotholeInformation pothole) {
        String sql = "INSERT INTO pothole_information (longitude, latitude) VALUES (?, ?) RETURNING id";
        Integer potholeID;
        potholeID = jdbcTemplate.queryForObject(sql, Integer.class, pothole.getLongitude(), pothole.getLatitude());

        sql = "INSERT INTO schedule (pothole_id) VALUES (?)";
        jdbcTemplate.update(sql, potholeID);

        sql = "SELECT date_created FROM pothole_information WHERE id = ?";
        Date date = jdbcTemplate.queryForObject(sql, Date.class, potholeID);

        if (date != null){
            sql = "UPDATE schedule SET date_reported = ? WHERE pothole_id = ?";
            jdbcTemplate.update(sql, date, potholeID);
        }

    }

    @Override
    public void updateSeverity(PotholeInformation pothole) {
        String sql = "UPDATE pothole_information SET severity = ? WHERE id = ?";
        jdbcTemplate.update(sql, pothole.getSeverity(), pothole.getPotholeId());
    }

    private PotholeInformation mapRowToPotholeInformation(SqlRowSet row) {
        PotholeInformation potholes = new PotholeInformation();
        potholes.setPotholeId(row.getInt("id"));
        potholes.setDateCreated(row.getDate("date_created").toLocalDate());
        if(row.getString("status") != null){
        potholes.setStatus(row.getString("status")); }
        potholes.setLongitude(row.getDouble("longitude"));
        potholes.setLatitude(row.getDouble("latitude"));
        potholes.setSeverity(row.getInt("severity"));

        return potholes;
    }

    @Override
    public void updatePicture(MultipartFile file, int id) throws IOException {
        SqlLobValue data = new SqlLobValue( file.getBytes());
        Object[] pothole = new Object[] { data, id };
        int[] types = new int[] { Types.BLOB, Types.INTEGER };
        String sql = "UPDATE pothole_information SET picture = ? WHERE id = ?";

        jdbcTemplate.update(sql, pothole, types);
    }

    @Override
    public String listImages() {
        String sql = "SELECT id, picture FROM pothole_information";

        return jdbcTemplate.query(sql, new JdbcPotholeInformation(jdbcTemplate));
    }

    @Override
    public String extractData(ResultSet resultSet) throws SQLException, DataAccessException {
        if(resultSet.next()) {
            byte[] imageData = resultSet.getBytes("picture");

            return Base64.getEncoder().encodeToString(imageData);
        }
        return null;
    }

    public String detectType(String b64) {
        if(b64.indexOf("/9j/") == 0) {
            System.out.println("true");
            return "image/jpg";
        }
        if(b64.indexOf("iVBORw0KGgo") == 0) {
            System.out.println("PNG");
            return "image/png";
        }
        return null;
    }
}
