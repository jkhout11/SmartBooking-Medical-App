package com.techelevator.model;

import java.sql.Time;

public class OfficeInfo {

    private int id;
    private String officeName;
    private String streetAddress;
    private String city;
    private String state;
    private int zipCode;
    private String phone;
    private Time openTime;
    private Time closeTime;
    private String openDays;

    public OfficeInfo() { }

    public OfficeInfo(int id,String officeName, String streetAddress, String city, String state, int zipCode,
                             String phone, Time openTime, Time closeTime, String openDays) {
        this.id = id;
        this.officeName = officeName;
        this.streetAddress = streetAddress;
        this.city = city;
        this.state = state;
        this.zipCode = zipCode;
        this.phone = phone;
        this.openTime = openTime;
        this.closeTime = closeTime;
        this.openDays = openDays;
    }

    // Getters and setters for all fields

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOfficeName() {
        return officeName;
    }

    public void setOfficeName(String officeName) {
        this.officeName = officeName;
    }

    public String getStreetAddress() {
        return streetAddress;
    }

    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public int getZipCode() {
        return zipCode;
    }

    public void setZipCode(int zipCode) {
        this.zipCode = zipCode;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Time getOpenTime() {
        return openTime;
    }

    public void setOpenTime(Time openTime) {
        this.openTime = openTime;
    }

    public Time getCloseTime() {
        return closeTime;
    }

    public void setCloseTime(Time closeTime) {
        this.closeTime = closeTime;
    }

    public String getOpenDays() {
        return openDays;
    }

    public void setOpenDays(String openDays) {
        this.openDays = openDays;
    }
}
