package com.techelevator.dao;

import com.techelevator.model.Doctor;
import com.techelevator.model.DoctorOfficeList;
import com.techelevator.model.Patient;

import java.util.List;

public interface DoctorDao {
    Doctor getDoctorByUserId(int userId);

    Doctor getDoctorByDoctorId(int doctorId);

    List<Doctor> getDoctors();

    List<DoctorOfficeList> getDoctorsAndOfficeInfo();

    void addDoctor(int userId, long npiNumber);

    void updateDoctorProfile(Doctor doctor,int userId);
    void updateDoctorOffice(int officeId,int userId);
}
