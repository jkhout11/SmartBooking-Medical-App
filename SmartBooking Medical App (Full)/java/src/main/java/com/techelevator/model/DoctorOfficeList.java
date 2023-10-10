package com.techelevator.model;

public class DoctorOfficeList {
    private String doctorFirstName;
    private String doctorLastName;
    private int officeId;
    private String officeName;
    private String streetAddress;
    private String city;
    private String state;
    private int zipCode;
    private String phoneNumber;

    public DoctorOfficeList(){}

    public DoctorOfficeList(String doctorFirstName, String doctorLastName,int officeId, String officeName, String streetAddress,
                            String city, String state, int zipCode, String phoneNumber) {
        this.doctorFirstName = doctorFirstName;
        this.doctorLastName = doctorLastName;
        this.officeId = officeId;
        this.officeName = officeName;
        this.streetAddress = streetAddress;
        this.city = city;
        this.state = state;
        this.zipCode = zipCode;
        this.phoneNumber = phoneNumber;
    }

    public String getDoctorFirstName(){
        return doctorFirstName;
    }
    public void setDoctorFirstName(String doctorFirstName){
        this.doctorFirstName = doctorFirstName;
    }
    public String getDoctorLastName(){
        return doctorLastName;
    }
    public void setDoctorLastName(String doctorLastName){
        this.doctorLastName = doctorLastName;
    }

    public int getOfficeId() {
        return officeId;
    }

    public void setOfficeId(int officeId) {
        this.officeId = officeId;
    }

    public String getOfficeName(){
        return officeName;
    }
    public void setOfficeName(String officeName){
        this.officeName = officeName;
    }
    public String getStreetAddress(){
        return streetAddress;
    }
    public void setStreetAddress(String streetAddress){
        this.streetAddress = streetAddress;
    }
    public String getCity(){
        return city;
    }
    public void setCity(String city){
        this.city = city;
    }
    public String getState(){
        return state;
    }
    public void setState(String state){
        this.state = state;
    }
    public int getZipCode(){
        return zipCode;
    }
    public void setZipCode(int zipCode){
        this.zipCode = zipCode;
    }
    public String getPhoneNumber(){
        return phoneNumber;
    }
    public void setPhoneNumber(String phoneNumber){
        this.phoneNumber = phoneNumber;
    }
}
