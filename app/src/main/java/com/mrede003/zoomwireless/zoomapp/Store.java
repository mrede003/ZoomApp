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

    public Store()
    {
        staff=new ArrayList<String>();
        managerName="";
        email="";
        address="";
        name="";
    }
    public Store(String address, String name, String managerName, String email, ArrayList<String>  staff)
    {
        this.address=address;
        this.name=name;
        this.staff=staff;
        this.managerName=managerName;
        this.email=email;
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

}
