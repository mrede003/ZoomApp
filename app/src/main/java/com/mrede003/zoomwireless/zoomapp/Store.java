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

    public Store()
    {
        staff=new ArrayList<String>();
        managerName="";
        email="";
        address="";
        name="";
        storeImg="";
        bio="";
        storeImgPre="";
        phoneNumber="";
    }
    public Store(String address, String name, String managerName, String email, ArrayList<String>  staff,
                 String storeImg, String bio, String storeImgPre, String phoneNumber)
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

}
