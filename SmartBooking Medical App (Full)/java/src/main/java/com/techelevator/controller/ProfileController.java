package com.techelevator.controller;

import com.techelevator.dao.DoctorDao;
import com.techelevator.dao.PatientDao;
import com.techelevator.dao.UserDao;
import com.techelevator.dao.UserProfileDao;
import com.techelevator.exception.DaoException;
import com.techelevator.model.Doctor;
import com.techelevator.model.ProfileDto;
import com.techelevator.model.RegisterUserDto;
import com.techelevator.model.User;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.security.Principal;

@RestController
@CrossOrigin
public class ProfileController {

    private UserProfileDao userProfileDao;
    private UserDao userDao;
    private DoctorDao doctorDao;
    private PatientDao patientDao;

    public ProfileController(UserProfileDao userProfileDao, UserDao userDao, DoctorDao doctorDao, PatientDao patientDao) {
        this.userProfileDao = userProfileDao;
        this.userDao = userDao;
        this.doctorDao = doctorDao;
        this.patientDao = patientDao;
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(path = "/profile", method = RequestMethod.GET)
    public ProfileDto getUserProfileType(@Valid Principal principal) {
        ProfileDto userProfile;
        User loggedInUser;
        try {
            loggedInUser = userDao.getUserByUsername(principal.getName());

            userProfile = userProfileDao.getUserProfileType(loggedInUser.getId());
            if (userProfile == null) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User Not Found.");
            }
        } catch (DaoException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "User Not Found.");
        }
        return userProfile;
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(path = "/save-profile", method = RequestMethod.POST)
    public void updateUserProfile(@Valid @RequestBody ProfileDto profileDto, Principal principal) {
        try {
            User currentUser = userDao.getUserByUsername(principal.getName());
            if (profileDto.isDoctor()) {
                doctorDao.updateDoctorProfile(profileDto.getDoctorProfile(),currentUser.getId());
                doctorDao.updateDoctorOffice(profileDto.getOfficeId() , currentUser.getId());
            } else {
                patientDao.updatePatientProfile(profileDto.getPatientProfile(), currentUser.getId());
            }
        } catch (DaoException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Profile Not Saved.");
        }

    }

}
