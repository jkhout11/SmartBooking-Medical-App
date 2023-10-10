package com.techelevator.model;

import java.sql.Time;

public class Schedule {
    private int scheduleId;
    private String dayOfTheWeek;
    private Time timeslot;

public Schedule() {}

public Schedule(int scheduleId, String dayOfTheWeek, Time timeslot){
    this.scheduleId=scheduleId;
    this.dayOfTheWeek=dayOfTheWeek;
    this.timeslot=timeslot;
}

    public int getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(int scheduleId) {
        this.scheduleId = scheduleId;
    }

    public String getDayOfTheWeek() {
        return dayOfTheWeek;
    }

    public void setDayOfTheWeek(String dayOfWeek) {
        this.dayOfTheWeek = dayOfWeek;
    }

    public Time getTimeslot() {
        return timeslot;
    }

    public void setTimeslot(Time timeslot) {
        this.timeslot = timeslot;
    }
}