package com.techelevator.model;

import java.util.List;

public class DoctorScheduleDto {
    private List<Integer> doctorScheduleIdList;

    public List<Integer> getDoctorScheduleIdList() {
        return doctorScheduleIdList;
    }

    public void setDoctorScheduleIdList(List<Integer> doctorScheduleIdList) {
        this.doctorScheduleIdList = doctorScheduleIdList;
    }
}
