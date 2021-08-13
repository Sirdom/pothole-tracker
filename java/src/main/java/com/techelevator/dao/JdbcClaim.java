package com.techelevator.dao;

import com.techelevator.model.Claim;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

@Component
public class JdbcClaim implements ClaimDAO {

    private JdbcTemplate jdbcTemplate;

    JdbcClaim(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void createClaim(Claim claim){
        String sql = "INSERT into claims (pothole_id, description, name, phone_number, email, date_occurred, "
                + "vehicle_year, vehicle_model, vehicle_make) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        System.out.println(claim.getPhoneNumber());
        jdbcTemplate.update(sql, claim.getPotholeId(), claim.getDescription(), claim.getName(), claim.getPhoneNumber(),
                claim.getEmail(), claim.getDateOccurred(), claim.getVehicleYear(), claim.getVehicleModel(), claim.getVehicleMake());

    }
}
