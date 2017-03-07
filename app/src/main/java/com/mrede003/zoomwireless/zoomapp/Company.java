package com.mrede003.zoomwireless.zoomapp;

import java.util.ArrayList;

/**
 * Created by mrede003 on 3/6/17.
 */

public class Company {

    private ArrayList<String> apptEmails;
    private String monThursO;
    private String monThursC;
    private String friO;
    private String friC;
    private String satO;
    private String satC;
    private String sunO;
    private String sunC;

    public Company()
    {
        apptEmails=new ArrayList<>();
        monThursO="";
        monThursC="";
        friO="";
        friC="";
        satO="";
        satC="";
        sunO="";
        sunC="";
    }
    public Company(String monThursO, String monThursC, String friO, String friC, String satO, String satC,
                   String sunO, String sunC, ArrayList<String> apptEmails)
    {
        this.monThursO=monThursO;
        this.monThursC=monThursC;
        this.friO=friO;
        this.friC=friC;
        this.satO=satO;
        this.satC=satC;
        this.sunO=sunO;
        this.sunC=sunC;
        this.apptEmails=apptEmails;
    }
    public ArrayList<String> getApptEmails() {
        return apptEmails;
    }

    public void setApptEmails(ArrayList<String> apptEmails) {
        this.apptEmails = apptEmails;
    }

    public String getMonThursO() {
        return monThursO;
    }

    public void setMonThursO(String monThursO) {
        this.monThursO = monThursO;
    }

    public String getMonThursC() {
        return monThursC;
    }

    public void setMonThursC(String monThursC) {
        this.monThursC = monThursC;
    }

    public String getFriO() {
        return friO;
    }

    public void setFriO(String friO) {
        this.friO = friO;
    }

    public String getFriC() {
        return friC;
    }

    public void setFriC(String friC) {
        this.friC = friC;
    }

    public String getSatO() {
        return satO;
    }

    public void setSatO(String satO) {
        this.satO = satO;
    }

    public String getSatC() {
        return satC;
    }

    public void setSatC(String satC) {
        this.satC = satC;
    }

    public String getSunO() {
        return sunO;
    }

    public void setSunO(String sunO) {
        this.sunO = sunO;
    }

    public String getSunC() {
        return sunC;
    }

    public void setSunC(String sunC) {
        this.sunC = sunC;
    }

}
