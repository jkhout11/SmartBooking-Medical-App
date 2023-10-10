package com.techelevator.controller;

import com.techelevator.model.Doctor;
import com.techelevator.model.DoctorOfficeList;
import com.techelevator.dao.DoctorDao;
import com.techelevator.model.OfficeInfo;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/doctors")
public class DoctorController {

    private final DoctorDao doctorDao;

    public DoctorController(DoctorDao doctorDao){
        this.doctorDao = doctorDao;
    }

    @GetMapping("/all")
    public List<Doctor> viewDoctors() {
        List doctor = doctorDao.getDoctors();
        if (doctor != null) {

        } else {
            System.out.println("No Doctors Found");
        }
        return doctor;
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/view-doctor")
    public List<DoctorOfficeList> viewDoctorsAndOffices() {
        List doctorOfficeInfo = doctorDao.getDoctorsAndOfficeInfo();
        if (doctorOfficeInfo != null) {

        } else {
            System.out.println("No Information Found");
        }
        return doctorOfficeInfo;
    }
}
