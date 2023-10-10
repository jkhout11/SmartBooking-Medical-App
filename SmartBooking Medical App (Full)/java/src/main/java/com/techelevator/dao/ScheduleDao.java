package com.techelevator.dao;

import com.techelevator.model.DoctorOfficeList;
import com.techelevator.model.Schedule;
import com.techelevator.model.DoctorSchedule;

import java.security.Principal;
import java.util.List;

import java.util.ArrayList;
public interface ScheduleDao {
    List<DoctorSchedule> getDoctorSchedule(int userId, String dayOfTheWeek);

    void updateSchedule(List<Integer> unAvailableList, int userId);

}
