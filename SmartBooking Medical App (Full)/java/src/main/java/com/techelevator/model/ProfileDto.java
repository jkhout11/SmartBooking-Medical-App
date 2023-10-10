package com.techelevator.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ProfileDto {
    @JsonProperty("isDoctor")
    private boolean doctor;
    @JsonProperty("isVerifiedDoctor")
    private boolean verifiedDoctor;
    private Doctor doctorProfile;
    private Patient patientProfile;
    private int officeId;

    public ProfileDto() {
    }

    public ProfileDto(boolean doctor, boolean verifiedDoctor, Doctor doctorProfile, Patient patientProfile,
                      int officeId) {
        this.doctor = doctor;
        this.verifiedDoctor = verifiedDoctor;
        this.doctorProfile = doctorProfile;
        this.patientProfile = patientProfile;
        this.officeId = officeId;
    }

    public boolean isDoctor() {
        return doctor;
    }

    public void setDoctor(boolean doctor) {
        this.doctor = doctor;
    }

    public boolean isVerifiedDoctor() {
        return verifiedDoctor;
    }

    public void setVerifiedDoctor(boolean verifiedDoctor) {
        this.verifiedDoctor = verifiedDoctor;
    }

    public Doctor getDoctorProfile() {
        return doctorProfile;
    }

    public void setDoctorProfile(Doctor doctorProfile) {
        this.doctorProfile = doctorProfile;
    }

    public Patient getPatientProfile() {
        return patientProfile;
    }

    public void setPatientProfile(Patient patientProfile) {
        this.patientProfile = patientProfile;
    }

    public int getOfficeId() {
        return officeId;
    }

    public void setOfficeId(int officeId) {
        this.officeId = officeId;
    }
}
