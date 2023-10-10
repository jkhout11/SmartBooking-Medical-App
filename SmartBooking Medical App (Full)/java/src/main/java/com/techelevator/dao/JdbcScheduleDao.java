package com.techelevator.dao;


import com.techelevator.exception.DaoException;
import com.techelevator.model.*;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component

public class JdbcScheduleDao implements ScheduleDao {
    private final JdbcTemplate jdbcTemplate;

    public JdbcScheduleDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    @Override
    public List<DoctorSchedule> getDoctorSchedule(int userId, String dayOfTheWeek) {
        List<DoctorSchedule> doctorScheduleLists = new ArrayList<>();
        String sql = "SELECT day_of_the_week, time_slot, slot_available, doctor_schedule_id ,doctor_schedule.schedule_id ,doctor_id " +
                "FROM doctor_schedule JOIN schedule ON doctor_schedule.schedule_id = schedule.schedule_id " +
                "WHERE doctor_id=(SELECT doctor_id from doctor WHERE user_id =?) AND day_of_the_week=? " +
                "ORDER BY time_slot ASC;";
        //if by day of week, then add AND day_of_the_week=? and below add dayOfWeek & inScheduleDao"
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, userId, dayOfTheWeek);
        while (results.next()) {
            DoctorSchedule doctorScheduleTimes = mapRowToDoctorScheduleList(results);
            doctorScheduleLists.add(doctorScheduleTimes);
        }
        return doctorScheduleLists;

    }

    @Override
    public void updateSchedule( List<Integer> unAvailableList, int userId) {

        String updateSql = "UPDATE doctor_schedule SET slot_available = ? WHERE doctor_schedule_id =?";
        try {
            for(int i : unAvailableList){
                int rowsUpdated = jdbcTemplate.update(updateSql, false,i);
            }
        } catch (DataAccessException e) {
            throw new DaoException("Error updating schedule", e);
        }



    }

    private DoctorSchedule mapRowToDoctorScheduleList(SqlRowSet rs) {
        DoctorSchedule doctorSchedule = new DoctorSchedule();
        doctorSchedule.setDoctorScheduleId(rs.getInt("doctor_schedule_id"));
        doctorSchedule.setTimeslot(rs.getTime("time_slot"));
        doctorSchedule.setDayOfTheWeek(rs.getString("day_of_the_week"));
        doctorSchedule.setSlotAvailable(rs.getBoolean("slot_available"));
        doctorSchedule.setDoctorId(rs.getInt("doctor_id"));
        doctorSchedule.setScheduleId(rs.getInt("schedule_id"));
        return doctorSchedule;
        //is this really a DTO?
    }
}
