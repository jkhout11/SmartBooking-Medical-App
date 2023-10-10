package com.techelevator.model;

public class BookAppointViewDto {
    private int doctorScheduleId;
    private int scheduleId;
    private int doctor_id;
    private String doctorFirstName;
    private String doctorLastName;

    public String getTimeSlot() {
        return timeSlot;
    }

    public void setTimeSlot(String timeSlot) {
        this.timeSlot = timeSlot;
    }

    private String timeSlot;

    public BookAppointViewDto() { }

    public BookAppointViewDto(int doctorScheduleId, int scheduleId, int doctor_id, String doctorFirstName,String doctorLastName, String timeSlot){
           this.doctorScheduleId =doctorScheduleId;
           this.scheduleId = scheduleId;
           this.doctor_id = doctor_id;
           this.doctorFirstName = doctorFirstName;
           this.doctorLastName = doctorLastName;
           this.timeSlot = timeSlot;
        }
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

    public int getDoctor_id() {
        return doctor_id;
    }

    public void setDoctor_id(int doctor_id) {
        this.doctor_id = doctor_id;
    }

    public String getDoctorFirstName() {
        return doctorFirstName;
    }

    public void setDoctorFirstName(String doctorFirstName) {
        this.doctorFirstName = doctorFirstName;
    }

    public String getDoctorLastName() {
        return doctorLastName;
    }

    public void setDoctorLastName(String doctorLastName) {
        this.doctorLastName = doctorLastName;
    }
}


