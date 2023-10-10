package com.techelevator.dao;

import com.techelevator.exception.DaoException;
import com.techelevator.model.DoctorOfficeDto;
import com.techelevator.model.OfficeInfo;
import com.techelevator.model.User;
import com.techelevator.dao.UserDao;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;


@Component
public class JdbcOfficeInfoDao implements OfficeInfoDao {

    private final JdbcTemplate jdbcTemplate;
    private UserDao userDao;

    public JdbcOfficeInfoDao(JdbcTemplate jdbcTemplate, UserDao userDao) {
        this.jdbcTemplate = jdbcTemplate;
        this.userDao = userDao;
    }

    //get a list of all office info records
    @Override
    public List<OfficeInfo> getOfficeLists() {
        List<OfficeInfo> officeLists = new ArrayList<>();
        String sql = "SELECT office_id, office_name, office_street_address, office_city, office_state, " +
                "office_zip_code, office_phone, office_open_time, office_close_time, office_open_days FROM office;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
        while (results.next()) {
            OfficeInfo officeInfo = mapRowToOfficeInfo(results);
            officeLists.add(officeInfo);
        }
        return officeLists;
    }

    //get office info by unique id
    @Override
    public OfficeInfo getOfficeById(int id) {
        String sql = "SELECT * FROM office WHERE office_id = ?";
        SqlRowSet result = jdbcTemplate.queryForRowSet(sql, id);
        if (result.next()) {
            return mapRowToOfficeInfo(result);
        } else {
            return null;
        }
    }

    //create a new office info recorded in the database
    @Override
    public OfficeInfo createOffice(OfficeInfo office) {
        OfficeInfo newOfficeInfo = null;
        String sql = "INSERT INTO office (office_name, office_street_address, office_city, office_state," +
                "office_zip_code, office_phone, office_open_time, office_close_time, office_open_days) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?) RETURNING office_id;";

        try {
            int newOfficeId = jdbcTemplate.queryForObject(sql, int.class, office.getOfficeName(), office.getStreetAddress(),
                    office.getCity(), office.getState(), office.getZipCode(), office.getPhone(), office.getOpenTime(),
                    office.getCloseTime(), office.getOpenDays());

            newOfficeInfo = getOfficeById(newOfficeId);

        } catch (DataAccessException e) {
            throw new DaoException("Error creating office", e);
        }
        return newOfficeInfo;
    }


@Override
    public DoctorOfficeDto getOfficeIdByUserId(Principal principal){
        User currentUser=userDao.getUserByUsername(principal.getName());
        int userId=currentUser.getId();
        String sql="SELECT doctor_office.office_id, doctor.doctor_id " +
                "FROM doctor_office " +
                "JOIN doctor ON doctor.doctor_id=doctor_office.doctor_id " +
                "WHERE doctor.user_id=?;";
        SqlRowSet results=jdbcTemplate.queryForRowSet(sql, userId);
        if (results.next()) {
            return mapRowToOfficeIdDrId(results);
        } else {
            return null;
        }

    }

    //update preexisting office info recorded in the database
    @Override
    public OfficeInfo updateOffice(OfficeInfo office) {
        OfficeInfo updatedOfficeInfo = null;
        String updateSql = "UPDATE office SET office_name = ?, office_street_address = ?, office_city = ?, " +
                "office_state = ?, office_zip_code = ?, office_phone = ?, office_open_time = ?, " +
                "office_close_time = ?, office_open_days = ? WHERE office_id = ?";
        try {
            int rowsUpdated = jdbcTemplate.update(updateSql, office.getOfficeName(), office.getStreetAddress(), office.getCity(),
                    office.getState(), office.getZipCode(), office.getPhone(), office.getOpenTime(), office.getCloseTime(),
                    office.getOpenDays(), office.getId());

            if (rowsUpdated > 0) {
                updatedOfficeInfo = getOfficeById(office.getId());
            }

        } catch (DataAccessException e) {
            throw new DaoException("Error updating office", e);
        }

        return updatedOfficeInfo;
    }


    // maps a row from database result to officeinfo object
    private OfficeInfo mapRowToOfficeInfo(SqlRowSet rs) {
        OfficeInfo officeInfo = new OfficeInfo();
        officeInfo.setId(rs.getInt("office_id"));
        officeInfo.setOfficeName(rs.getString("office_name"));
        officeInfo.setStreetAddress(rs.getString("office_street_address"));
        officeInfo.setCity(rs.getString("office_city"));
        officeInfo.setState(rs.getString("office_state"));
        officeInfo.setZipCode(rs.getInt("office_zip_code"));
        officeInfo.setPhone(rs.getString("office_phone"));
        officeInfo.setOpenTime(rs.getTime("office_open_time"));
        officeInfo.setCloseTime(rs.getTime("office_close_time"));
        officeInfo.setOpenDays(rs.getString("office_open_days"));

        return officeInfo;
    }

    private DoctorOfficeDto mapRowToOfficeIdDrId(SqlRowSet rs){
        DoctorOfficeDto doctorOfficeInfo = new DoctorOfficeDto();
        doctorOfficeInfo.setDoctorId(rs.getInt("doctor_id"));
        doctorOfficeInfo.setOfficeId(rs.getInt("office_id"));

        return doctorOfficeInfo;
    }


}
