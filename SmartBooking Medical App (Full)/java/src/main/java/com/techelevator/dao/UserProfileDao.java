package com.techelevator.dao;

import com.techelevator.model.ProfileDto;
import com.techelevator.model.User;

public interface UserProfileDao {

    public ProfileDto getUserProfileType(int userId);
}
