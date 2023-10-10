package com.techelevator.dao;

import com.techelevator.exception.DaoException;
import com.techelevator.model.*;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Component
public class JdbcDoctorDao implements DoctorDao {
    private final JdbcTemplate jdbcTemplate;

    public JdbcDoctorDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Doctor> getDoctors() {
        List<Doctor> listOfDoctors = new ArrayList<>();
        String sql = "SELECT * FROM doctor";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
        while (results.next()) {
            Doctor doctor = mapRowToDoctor(results);
            listOfDoctors.add(doctor);
        }
        return listOfDoctors;
    }

    @Override
    public List<DoctorOfficeList> getDoctorsAndOfficeInfo() {
        List<DoctorOfficeList> doctorOfficeLists = new ArrayList<>();
        String sql = "SELECT doctor_first_name, doctor_last_name, doctor_office.office_id, office_street_address, office_city, office_state, office_zip_code " +
                "FROM doctor " + "JOIN doctor_office ON doctor.doctor_id = doctor_office.doctor_id " +
                "JOIN office ON doctor_office.office_id = office.office_id;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
        while (results.next()) {
            DoctorOfficeList doctorOfficeInfo = mapRowToDoctorOfficeList(results);
            doctorOfficeLists.add(doctorOfficeInfo);
        }
        return doctorOfficeLists;
    }

    // if user id is present in doctors table
    @Override
    public Doctor getDoctorByUserId(int userId) {
        Doctor doctor = null;
        String sql = "SELECT * FROM doctor WHERE user_id = ?;";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, userId);
            if (results.next()) {
                doctor = mapRowToDoctor(results);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return doctor;

    }

    @Override
    public Doctor getDoctorByDoctorId(int doctorId) {
        Doctor doctor = null;
        String sql = "SELECT * FROM doctor WHERE doctor_id = ?;";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, doctorId);
            if (results.next()) {
                doctor = mapRowToDoctor(results);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return doctor;
    }

    public void addDoctor(int userId, long npiNumber) {
        String sql = "INSERT INTO doctor(doctor_npi,user_id,active) VALUES (?,?,?);";
        try {
            jdbcTemplate.update(sql, npiNumber, userId, false);
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }
    }

    public void updateDoctorProfile(Doctor doctor,int userId) {

        String sql = "UPDATE doctor SET user_id=?,doctor_first_name=?, doctor_last_name=?, " +
                "doctor_email=? where user_id=?;";
        try {
            jdbcTemplate.update(sql, userId, doctor.getDoctorFirstName(), doctor.getDoctorLastName(),
                     doctor.getDoctorEmail(), userId);
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }
    }

    public void updateDoctorOffice(int officeId,int userId) {

        String sql = "UPDATE doctor_office SET office_id=? WHERE doctor_id=" +
                "(SELECT doctor_id from doctor WHERE user_id=?);";
        String sql1 = "select doctor_id from doctor_office where doctor_id =" +
                "(SELECT doctor_id from doctor WHERE user_id=?);";
        String sql2 = "INSERT INTO doctor_office (office_id,doctor_id) " +
                "values( ?,(SELECT doctor_id from doctor WHERE user_id=?));";

        try {
            //check if the doctor_id is present in the doctor_office table
            SqlRowSet result = jdbcTemplate.queryForRowSet(sql1, userId);
            if(result.next()){
                if(result.getInt("doctor_id") != 0)
                    //Update the office id if doctor_id is present in doctor_office table
                jdbcTemplate.update(sql, officeId, userId);
            }
            else{
                //insert a record if doctor_id ia not available in the table
                jdbcTemplate.update(sql2, officeId, userId);
            }

        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }
    }

    private Doctor mapRowToDoctor(SqlRowSet rs) {
        Doctor doctor = new Doctor();
        doctor.setDoctorId(rs.getInt("doctor_id"));
        doctor.setDoctorFirstName(rs.getString("doctor_first_name"));
        doctor.setDoctorLastName(rs.getString("doctor_last_name"));
        //doctor.setDoctorEmail(Objects.requireNonNull(rs.getString("doctor_email")));
        doctor.setDoctorEmail(rs.getString("doctor_email"));
        doctor.setDoctorNpi(rs.getString("doctor_npi"));
        return doctor;
    }

    private DoctorOfficeList mapRowToDoctorOfficeList(SqlRowSet rs) {
        DoctorOfficeList doctorOffice = new DoctorOfficeList();
        doctorOffice.setDoctorFirstName(rs.getString("doctor_first_name"));
        doctorOffice.setDoctorLastName(rs.getString("doctor_last_name"));
        doctorOffice.setOfficeId(rs.getInt("office_id"));
        doctorOffice.setStreetAddress(rs.getString("office_street_address"));
        doctorOffice.setCity(rs.getString("office_city"));
        doctorOffice.setState(rs.getString("office_state"));
        doctorOffice.setZipCode(rs.getInt("office_zip_code"));

        return doctorOffice;
    }
}
