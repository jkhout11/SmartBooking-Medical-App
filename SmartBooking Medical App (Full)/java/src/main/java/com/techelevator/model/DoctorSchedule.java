package com.techelevator.model;


import java.sql.Time;
import java.util.Date;

public class DoctorSchedule extends Schedule{
private int doctorScheduleId;
private int scheduleId;
private int doctorId;
private boolean slotAvailable;
private Time timeslot;
private String dayOfTheWeek;

    @Override
    public Time getTimeslot() {
        return timeslot;
    }

    @Override
    public void setTimeslot(Time timeslot) {
        this.timeslot = timeslot;
    }

    public String getDayOfTheWeek() {
        return dayOfTheWeek;
    }

    public void setDayOfTheWeek(String dayOfTheWeek) {
        this.dayOfTheWeek = dayOfTheWeek;
    }

    public DoctorSchedule(){}

    public int getDoctorScheduleId() {
        return doctorScheduleId;
    }

    public void setDoctorScheduleId(int doctorScheduleId) {
        this.doctorScheduleId = doctorScheduleId;
    }

    public int getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(int scheduleId) {
        this.scheduleId = scheduleId;
    }

    public int getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(int doctorId) {
        this.doctorId = doctorId;
    }
    public boolean getSlotAvailable() {
        return slotAvailable;
    }

    public void setSlotAvailable(boolean slotAvailable) {
        this.slotAvailable = slotAvailable;
    }

    public DoctorSchedule(int doctorScheduleId, int doctorId, int scheduleId, boolean slotAvailable, Time timeslot,String dayOfTheWeek){
        this.doctorScheduleId=doctorScheduleId;
        this.doctorId=doctorId;
        this.scheduleId=scheduleId;
        this.slotAvailable=true;
        this.timeslot=timeslot;
        this.dayOfTheWeek=dayOfTheWeek;




    }

}
