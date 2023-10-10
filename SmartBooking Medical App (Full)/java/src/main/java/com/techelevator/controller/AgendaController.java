package com.techelevator.controller;

import com.techelevator.dao.AppointmentDao;
import com.techelevator.dao.PatientDao;
import com.techelevator.dao.UserDao;
import com.techelevator.model.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import com.techelevator.exception.DaoException;

import javax.validation.Valid;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
@RestController
@CrossOrigin
@RequestMapping("/agenda")
public class AgendaController {
    private final AppointmentDao appointmentDao;
    private final PatientDao patientDao;
    private final UserDao userDao;

    public AgendaController(AppointmentDao appointmentDao, PatientDao patientDao, UserDao userDao) {
        this.appointmentDao = appointmentDao;
        this.patientDao = patientDao;
        this.userDao = userDao;
    }
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(path = "/view-appointments", method = RequestMethod.GET)
    public List<AppointmentResponseDto> getAppointmentList(Principal principal) {
        List<AppointmentResponseDto> appointmentList ;
        User currentUser = userDao.getUserByUsername(principal.getName());
        try {
            appointmentList = appointmentDao.getAppointmentListsByDoctorId(currentUser.getId());
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Appointment List By Doctor Id failed.");
        }
        return appointmentList;
    }
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(path = "/nextsevendayappointmentlistbydoctorid/{doctorId}", method = RequestMethod.GET)
    public List<AppointmentResponseDto> sevendayslistappointments(@PathVariable int doctorId) {
        List<AppointmentResponseDto> nextSevenDayAppointmentList = new ArrayList<>();
        try {
            nextSevenDayAppointmentList = appointmentDao.getNextSevenDayAppointmentListsByDoctorId(doctorId);

        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Next Seven Day Appointment List By Doctor Id failed.");
        }
        return nextSevenDayAppointmentList;
    }
    @RequestMapping(path = "/patientlist", method = RequestMethod.GET)
    public List<Patient> listpatients() {
        List<Patient> patientList = new ArrayList<>();
        try {
            patientList = patientDao.getPatientLists();

        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Patient List failed.");
        }
        return patientList;
    }
    @RequestMapping(path = "/patientlistbyid/{patienId}", method = RequestMethod.GET)
    public List<Patient> listpatientsById(@PathVariable int patienId) {
        List<Patient> patientList = new ArrayList<>();
        try {
            patientList = patientDao.getPatientListByPatientId(patienId);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Patient List By Id failed.");
        }
        return patientList;
    }
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(path = "/listbookappointments", method = RequestMethod.GET)
    public List<BookAppointViewDto> listbookappointments() {
        List<BookAppointViewDto> bookAppointmentList = new ArrayList<>();
        try {
            bookAppointmentList = appointmentDao.getAvailableAppointmentLists();
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Appointment List By Doctor Id failed.");
        }
        return bookAppointmentList;
    }
    @ResponseStatus(HttpStatus.ACCEPTED)
    @RequestMapping(path = "/bookanappointment", method = RequestMethod.POST)
    public int bookAnAppointment(@RequestBody Appointment anAppointment, Principal principal) {
        int bookAnAppointmentID = 0;
        try {
            bookAnAppointmentID = appointmentDao.postBookAnAppointment(anAppointment, principal);
        } catch (DaoException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return bookAnAppointmentID;
    }
}
