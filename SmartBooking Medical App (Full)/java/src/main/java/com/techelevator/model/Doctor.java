package com.techelevator.model;

public class Doctor {
    private int doctorId;
    private String doctorNpi;
    private int userId;
    private String doctorFirstName;
    private String doctorLastName;
    private String doctorEmail;
    private String doctorAddress;
    private boolean active;

    public Doctor() {
    }

    public Doctor(int doctorId, String doctorNpi, int userId, String doctorFirstName,
                  String doctorLastName, String doctorEmail, String address, boolean active) {
        this.doctorId = doctorId;
        this.doctorNpi = doctorNpi;
        this.userId = userId;
        this.doctorFirstName = doctorFirstName;
        this.doctorLastName = doctorLastName;
        this.doctorEmail = doctorEmail;
        this.doctorAddress = address;
        this.active = active;
    }

    public int getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(int doctorId) {
        this.doctorId = doctorId;
    }

    public String getDoctorNpi() {
        return doctorNpi;
    }

    public void setDoctorNpi(String doctorNpi) {
        this.doctorNpi = doctorNpi;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
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

    public String getDoctorEmail() {
        return doctorEmail;
    }

    public void setDoctorEmail(String doctorEmail) {
        this.doctorEmail = doctorEmail;
    }

    public String getAddress() {
        return doctorAddress;
    }

    public void setAddress(String address) {
        this.doctorAddress = address;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public String toString() {
        return "Doctor{" +
                "doctorId=" + doctorId +
                ", doctorNpi='" + doctorNpi + '\'' +
                ", userId=" + userId +
                ", doctorFirstName='" + doctorFirstName + '\'' +
                ", doctorLastName='" + doctorLastName + '\'' +
                ", doctorEmail='" + doctorEmail + '\'' +
                ", active=" + active +
                '}';
    }
}

