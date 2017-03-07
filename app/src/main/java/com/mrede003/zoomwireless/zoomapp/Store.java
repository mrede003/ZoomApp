package com.mrede003.zoomwireless.zoomapp;

import java.util.ArrayList;

/**
 * Created by mrede003 on 2/21/17.
 */

public class Store {
    private ArrayList<String> staff;
    private String managerName;
    private String email;
    private String address;
    private String name;
    private String storeImg;
    private String bio;
    private String storeImgPre;
    private String phoneNumber;
    private String googleRateLink;
    private Double latitude;
    private Double longitude;
    private Double milesAway;

    public Store()
    {
        staff=new ArrayList<String>();
        managerName="Suman Bandey";
        email="mrede003@gmail.com";
        address="null";
        name="null";
        storeImg="null";
        bio="null";
        storeImgPre="null";
        phoneNumber="null";
        googleRateLink="http://search.google.com/local/writereview?placeid=ChIJAVRwnXmwuokRzAL4ZwNYXTk";
        latitude=-1.0;
        longitude=-1.0;
        milesAway=-1.0;
    }
    public Store(String address, String name, String managerName, String email, ArrayList<String>  staff,
                 String storeImg, String bio, String storeImgPre, String phoneNumber,Double latitude, Double longitude, String googleRateLink)
    {
        this.address=address;
        this.name=name;
        this.staff=staff;
        this.managerName=managerName;
        this.email=email;
        this.storeImg=storeImg;
        this.bio=bio;
        this.storeImgPre=storeImgPre;
        this.phoneNumber=phoneNumber;
        this.latitude=latitude;
        this.longitude=longitude;
        this.googleRateLink = googleRateLink;
        milesAway=-1.0;
    }
    public ArrayList<String> getStaff() {
        return staff;
    }
    public String getManagerName() {
        return managerName;
    }
    public String getEmail() {
        return email;
    }
    public String getAddress() {
        return address;
    }
    public String getName() {
        return name;
    }
    public String getStoreImg() {
        return storeImg;
    }
    public String getBio() {
        return bio;
    }
    public String getStoreImgPre() {
        return storeImgPre;
    }
    public String getPhoneNumber() {
        return phoneNumber;
    }
    public Double getLatitude() {
        return latitude;
    }
    public Double getLongitude() {
        return longitude;
    }
    public Double getMilesAway() {
        return milesAway;
    }
    public String getGoogleRateLink() {
        return googleRateLink;
    }



    public void setStaff(ArrayList<String> staff) {
        this.staff = staff;
    }
    public void setManagerName(String managerName) {
        this.managerName = managerName;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setStoreImg(String storeImg) {
        this.storeImg = storeImg;
    }
    public void setBio(String bio) {
        this.bio = bio;
    }
    public void setStoreImgPre(String storeImgPre) {
        this.storeImgPre = storeImgPre;
    }
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }
    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }
    public void setMilesAway(Double milesAway) {
        this.milesAway = milesAway;
    }
    public void setGoogleRateLink(String googleRateLink) {
        this.googleRateLink = googleRateLink;
    }
}
