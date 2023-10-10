package com.techelevator.controller;
import com.techelevator.dao.UserDao;
import com.techelevator.model.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.techelevator.dao.ScheduleDao;

import javax.validation.Valid;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin
@RequestMapping("/update-availability")
public class ScheduleController {
    private  ScheduleDao scheduleDao;
    private  UserDao userDao;

    public ScheduleController(ScheduleDao scheduleDao, UserDao userDao) {
        this.scheduleDao = scheduleDao;
        this.userDao = userDao;
    }
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/schedule")
    public List<DoctorSchedule> getDoctorSchedule(@RequestParam(required=false) String dayOfTheWeek, Principal principal){
        User currentUser = userDao.getUserByUsername(principal.getName());

        List doctorSchedule= scheduleDao.getDoctorSchedule(currentUser.getId(), dayOfTheWeek);
        //if need day of week then add dayOfWeek above)
        if (doctorSchedule != null) {

        }else {
            System.out.println("No schedule found. Please update.");
        }
       return doctorSchedule;
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping()
    public void updateSchedule(@Valid @RequestBody DoctorScheduleDto doctorScheduleDto, Principal principal) {
        User currentUser = userDao.getUserByUsername(principal.getName());
        scheduleDao.updateSchedule(doctorScheduleDto.getDoctorScheduleIdList(),currentUser.getId());
    }

}
