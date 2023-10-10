package com.techelevator.dao;

import com.techelevator.exception.DaoException;
import com.techelevator.model.ProfileDto;
import com.techelevator.model.User;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

@Component
public class JdbcUserProfileDao implements UserProfileDao {
    private final JdbcTemplate jdbcTemplate;

    public JdbcUserProfileDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public ProfileDto getUserProfileType(int userId){
        ProfileDto currentUserProfile = new ProfileDto();
        String sql = "SELECT doctor_id,doctor_npi,user_id, active FROM doctor WHERE user_id = ?;";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, userId);
            if (results.next()) {
                String fetchedUserId = results.getString("user_id");
                Boolean isValidDoctor =  results.getBoolean("active");

                if(!fetchedUserId.isEmpty() || !fetchedUserId.equals("")){
                    currentUserProfile.setDoctor(true);
                }
                currentUserProfile.setVerifiedDoctor(isValidDoctor);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }

        return currentUserProfile;
    }


}
