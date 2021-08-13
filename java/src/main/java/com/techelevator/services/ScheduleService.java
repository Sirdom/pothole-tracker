package com.techelevator.services;

import com.techelevator.dao.JdbcPotholeInformation;
import com.techelevator.dao.JdbcSchedule;
import com.techelevator.model.PotholeInformation;
import com.techelevator.model.Schedule;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class ScheduleService {
    private JdbcSchedule jdbcSchedule;

//    public PotholeInformationService() {}

    public ScheduleService(JdbcSchedule jdbcSchedule) {
        this.jdbcSchedule = jdbcSchedule;
    }

    public void create(Schedule schedule) {
        jdbcSchedule.createSchedule(schedule);
    }

    public ArrayList<Schedule> getSchedules() { return jdbcSchedule.list(); }

    public void updateSchedule(Schedule schedule) { jdbcSchedule.update(schedule);}
}