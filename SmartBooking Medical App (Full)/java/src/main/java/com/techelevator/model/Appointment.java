package com.techelevator.model;

public class Appointment {

    private int appointmentId;
    //private int patientId;
    private int doctorScheduleId;
    private String dateSelected;

    public Appointment() { }

    public Appointment( int appointmentId, int doctorScheduleId, String dateSelected){
            this.appointmentId = appointmentId;
            //this.patientId = patientId;
            this.doctorScheduleId = doctorScheduleId;
            this.dateSelected = dateSelected;
        }

    public int getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(int appointmentId) {
        this.appointmentId = appointmentId;
    }

    public int getDoctorScheduleId() {
        return doctorScheduleId;
    }

    public void setDoctorScheduleId(int doctorScheduleId) {
        this.doctorScheduleId = doctorScheduleId;
    }

    public String getDateSelected() {
        return dateSelected;
    }

    public void setDateSelected(String dateSelected) {
        this.dateSelected = dateSelected;
    }
}


