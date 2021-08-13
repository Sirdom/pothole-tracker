package com.techelevator.dao;

import com.techelevator.model.PotholeInformation;
import com.techelevator.model.Schedule;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class JdbcSchedule implements ScheduleDAO {
    private JdbcTemplate jdbcTemplate;

    public JdbcSchedule(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }
    @Override
    public void createSchedule(Schedule schedule) {
        String sql = "INSERT INTO schedule (pothole_id) VALUES (?)";
        jdbcTemplate.update(sql, schedule.getPotholeId());
    }

    @Override
    public ArrayList<Schedule> list() {
        String sql = "SELECT schedule_id, pothole_id, status, p.date_created, date_inspected, date_repaired, s.severity FROM schedule s " +
                "JOIN pothole_information p ON p.id = pothole_id";
        SqlRowSet result = jdbcTemplate.queryForRowSet(sql);
        ArrayList<Schedule>  schedules = new ArrayList<>();
        try {
            while(result.next()) {
                Schedule schedule = mapRowToSchedule(result);
                schedules.add(schedule);
            }
        } catch(DataAccessException ex) {
            System.out.println(ex.getMessage());
        }
        return schedules;
    }

    @Override
    public void update(Schedule schedule) {
        String sql = "UPDATE schedule SET date_inspected = ?, date_repaired = ?, status = ?, severity = ? " +
                "WHERE pothole_id = ?";
        jdbcTemplate.update(sql, schedule.getDateInspected(), schedule.getDateRepaired(), schedule.getStatus(), schedule.getSeverity(), schedule.getPotholeId());
    }

    private Schedule mapRowToSchedule(SqlRowSet row) {
        Schedule schedule = new Schedule();
        schedule.setId(row.getInt("schedule_id"));
        schedule.setPotholeId(row.getInt("pothole_id"));
        schedule.setStatus(row.getString("status"));
        if(row.getDate("date_created") != null) {
            schedule.setDateCreated(row.getDate("date_created").toLocalDate());
        }
        if(row.getDate("date_inspected") != null) {
            schedule.setDateInspected(row.getDate("date_inspected").toLocalDate());
        }
        if(row.getDate("date_repaired") != null) {
            schedule.setDateRepaired(row.getDate("date_repaired").toLocalDate());
        }
        schedule.setSeverity(row.getInt("severity"));

        return schedule;
    }
}
