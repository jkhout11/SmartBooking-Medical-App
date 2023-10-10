package com.techelevator.dao;

import com.techelevator.exception.DaoException;
import com.techelevator.model.Patient;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcPatientDao implements PatientDao {
    private final JdbcTemplate jdbcTemplate;

    public JdbcPatientDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Patient> getPatientListByPatientId(int patientId) {
        List<Patient> PatientLists = new ArrayList<>();
        try {
            String sql = "SELECT * FROM patient WHERE patient_id = ? ;";
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, patientId);
            while (results.next()) {
                Patient patient = mapRowToPatient(results);
                PatientLists.add(patient);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (
                DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }
        return PatientLists;
    }

    @Override
    public List<Patient> getPatientLists() {
        List<Patient> PatientLists = new ArrayList<>();
        try {
            String sql = "SELECT * FROM patient;";
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
            while (results.next()) {
                Patient patient = mapRowToPatient(results);
                PatientLists.add(patient);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (
                DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }
        return PatientLists;
    }

    public void addPatient(int userId) {
        String sql = "INSERT INTO patient(user_id,active) VALUES (?,?);";
            try{
                jdbcTemplate.update(sql, userId,true);
            } catch (CannotGetJdbcConnectionException e) {
                throw new DaoException("Unable to connect to server or database", e);
            } catch (DataIntegrityViolationException e) {
                throw new DaoException("Data integrity violation", e);
            }
    }

    public void updatePatientProfile(Patient patient ,int userId) {

        String sql = "UPDATE patient SET patient_first_name=?, patient_last_name =?, patient_street_address=?, " +
                "patient_city =?, patient_state =?, patient_zip_code =?, patient_phone_number =?, patient_email =?, " +
                "patient_dob =? where user_id =? ;";

        try{
            jdbcTemplate.update(sql, patient.getPatientFirstName(),patient.getPatientLastName(),
                    patient.getPatientStreetAddress(),patient.getPatientCity(),patient.getPatientState(),patient.getPatientZipCode(),
                    patient.getPatientPhoneNumber(), patient.getPatientEmail(), patient.getPatientDob(), userId);
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }
    }

    // Maps a row from Database result to Patient object
    private Patient mapRowToPatient(SqlRowSet rs) {
        Patient patient = new Patient();
        patient.setPatientId(rs.getInt("patient_id"));
        patient.setUserId(rs.getInt("user_id"));
        patient.setPatientFirstName(rs.getString("patient_first_name"));
        patient.setPatientLastName(rs.getString("patient_last_name"));
        patient.setPatientStreetAddress(rs.getString("patient_street_address"));
        patient.setPatientCity(rs.getString("patient_city"));
        patient.setPatientZipCode(rs.getString("patient_zip_code"));
        patient.setPatientPhoneNumber(rs.getString("patient_phone_number"));
        patient.setPatientEmail(rs.getString("patient_email"));
        patient.setPatientDob(rs.getDate("patient_dob").toLocalDate());
        return patient;
    }
}
