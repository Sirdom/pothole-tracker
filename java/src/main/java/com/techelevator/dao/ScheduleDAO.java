package com.techelevator.dao;

import com.techelevator.model.Schedule;

import java.util.ArrayList;

public interface ScheduleDAO {
    void createSchedule(Schedule schedule);

    ArrayList<Schedule> list();

    void update(Schedule schedule);
}
