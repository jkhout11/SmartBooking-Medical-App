package com.techelevator.dao;

import com.techelevator.exception.DaoException;
import com.techelevator.model.Appointment;
import com.techelevator.model.AppointmentResponseDto;
import com.techelevator.model.BookAppointViewDto;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.security.Principal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcAppointmentDao implements AppointmentDao {
    private final JdbcTemplate jdbcTemplate;

    public JdbcAppointmentDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<AppointmentResponseDto> getAppointmentListsByDoctorId(int userId) {
        List<AppointmentResponseDto> appointmentLists = new ArrayList<>();
        try {
            String sql = "SELECT patient.patient_first_name, patient.patient_last_name,appointment.appointment_id," +
                    " appointment.date_selected, schedule.time_slot ,doctor_schedule.doctor_id" +
                    " FROM patient " +
                    " JOIN appointment ON patient.patient_id = appointment.patient_id " +
                    " JOIN doctor_schedule ON appointment.doctor_schedule_id = doctor_schedule.doctor_schedule_id " +
                    " JOIN schedule ON doctor_schedule.schedule_id = schedule.schedule_id " +
                    " WHERE appointment.date_selected >= CURRENT_DATE " +
                    " AND doctor_schedule.doctor_id =(SELECT doctor_id from doctor WHERE user_id =?) " +
                    " ORDER BY appointment.date_selected, schedule.time_slot ASC;";
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, userId);
            while (results.next()) {
                AppointmentResponseDto appointment = mapRowToAppointmentResponseDto(results);
                appointmentLists.add(appointment);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (
                DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }
        return appointmentLists;
    }

    @Override
    public List<AppointmentResponseDto> getNextSevenDayAppointmentListsByDoctorId(int doctorId) {
        List<AppointmentResponseDto> appointmentLists = new ArrayList<>();
        try {
            String sql = "SELECT patient.patient_first_name, patient.patient_last_name, appointment.date_selected, schedule.time_slot " +
                    " FROM patient " +
                    " JOIN appointment ON patient.patient_id = appointment.patient_id " +
                    " JOIN doctor_schedule ON appointment.doctor_schedule_id = doctor_schedule.doctor_schedule_id " +
                    " JOIN schedule ON doctor_schedule.schedule_id = schedule.schedule_id " +
                    " WHERE appointment.date_selected between CURRENT_DATE AND CURRENT_DATE + 7 " +
                    " AND doctor_schedule.doctor_id = ?";
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, doctorId);
            while (results.next()) {
                AppointmentResponseDto appointment = mapRowToAppointmentResponseDto(results);
                appointmentLists.add(appointment);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (
                DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }
        return appointmentLists;
    }
    public List<BookAppointViewDto> getAvailableAppointmentLists() {
        List<BookAppointViewDto> appointmentLists = new ArrayList<>();
        try {
            String sql = "SELECT doctor_schedule.doctor_schedule_id, doctor_schedule.schedule_id,schedule.time_slot, " +
                    " doctor.doctor_id, doctor.doctor_first_name, doctor.doctor_last_name " +
                    " FROM doctor_schedule " +
                    " JOIN doctor ON doctor_schedule.doctor_id = doctor.doctor_id " +
                    " JOIN schedule on 	doctor_schedule.schedule_id = schedule.schedule_id " +
                    " WHERE schedule.day_of_the_week = 'Monday' AND doctor.doctor_id = 1 AND doctor_schedule.slot_available = 'true';";
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
            while (results.next()) {
                BookAppointViewDto appointment = mapRowToBookAppointViewDto(results);
                appointmentLists.add(appointment);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (
                DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }
        return appointmentLists;
    }

    @Override
    public int postBookAnAppointment(Appointment anAppointment, Principal principal) {
        Integer appointmentId;

            try {
                //get logged in patent ID
                String sqlPatientID = "Select patient_id from patient " +
                        " JOIN Users on Users.User_Id = patient.user_id " +
                        " WHERE Users.username = ? ";
                SqlRowSet results = jdbcTemplate.queryForRowSet(sqlPatientID,principal.getName() );
                int patientId =1;
                while (results.next()) {
                    patientId = results.getInt("patient_id");
                }
                String sqlBookAnAppointment = "INSERT INTO appointment (patient_id, doctor_schedule_id, " +
                        "date_selected ) VALUES (?, ?,? ) RETURNING appointment_id";
                appointmentId = jdbcTemplate.queryForObject(sqlBookAnAppointment, Integer.class, patientId, anAppointment.getDoctorScheduleId(), LocalDate.parse(anAppointment.getDateSelected()));

            } catch (CannotGetJdbcConnectionException e) {
                throw new DaoException("Unable to connect to server or database", e);
            } catch (DataIntegrityViolationException e) {
                throw new DaoException("Data integrity violation", e);
            }
        return appointmentId;
    }

    // Maps a row from Database result to AppointViewDto Model object
    private BookAppointViewDto mapRowToBookAppointViewDto(SqlRowSet rs) {
        BookAppointViewDto appointment = new BookAppointViewDto();
        appointment.setDoctorScheduleId(rs.getInt("doctor_schedule_id"));
        appointment.setScheduleId(rs.getInt("schedule_id"));
        appointment.setDoctor_id(rs.getInt("doctor_id"));
        appointment.setDoctorFirstName(rs.getString("doctor_first_name"));
        appointment.setDoctorLastName(rs.getString("doctor_last_name"));
        appointment.setTimeSlot(rs.getString("time_slot"));
        return appointment;
    }
    // Maps a row from Database result to AppointmentResponseDto Model object
    private AppointmentResponseDto mapRowToAppointmentResponseDto(SqlRowSet rs) {
        AppointmentResponseDto appointment = new AppointmentResponseDto();
        appointment.setPatientFirstName(rs.getString("patient_first_name"));
        appointment.setPatientLastName(rs.getString("patient_last_name"));
        appointment.setAppointmentId(rs.getInt("appointment_id"));
        appointment.setDateSelected(rs.getString("date_selected"));
        appointment.setTimeSlot(rs.getString("time_slot"));
        appointment.setDoctorId(rs.getInt("doctor_id"));
        return appointment;
    }
}
