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
    private String emailPort;
    private String smtpAuth;
    private String starttls;
    private String emailHost;
    private String fromEmail;
    private String fromPassword;
    private String facebook_url;
    private String facebook_page_id;
    private String twitter_username;

    public String getFacebook_url() {
        return facebook_url;
    }

    public void setFacebook_url(String facebook_url) {
        this.facebook_url = facebook_url;
    }

    public String getFacebook_page_id() {
        return facebook_page_id;
    }

    public void setFacebook_page_id(String facebook_page_id) {
        this.facebook_page_id = facebook_page_id;
    }

    public String getTwitter_username() {
        return twitter_username;
    }

    public void setTwitter_username(String twitter_username) {
        this.twitter_username = twitter_username;
    }
    public String getEmailPort() {
        return emailPort;
    }

    public void setEmailPort(String emailPort) {
        this.emailPort = emailPort;
    }

    public String getSmtpAuth() {
        return smtpAuth;
    }

    public void setSmtpAuth(String smtpAuth) {
        this.smtpAuth = smtpAuth;
    }

    public String getStarttls() {
        return starttls;
    }

    public void setStarttls(String starttls) {
        this.starttls = starttls;
    }

    public String getEmailHost() {
        return emailHost;
    }

    public void setEmailHost(String emailHost) {
        this.emailHost = emailHost;
    }

    public String getFromEmail() {
        return fromEmail;
    }

    public void setFromEmail(String fromEmail) {
        this.fromEmail = fromEmail;
    }

    public String getFromPassword() {
        return fromPassword;
    }

    public void setFromPassword(String fromPassword) {
        this.fromPassword = fromPassword;
    }

    public Company()
    {
        apptEmails=new ArrayList<>();
        monThursO="null";
        monThursC="null";
        friO="null";
        friC="null";
        satO="null";
        satC="null";
        sunO="null";
        sunC="null";
        emailPort="587";
        smtpAuth="true";
        starttls="true";
        emailHost="smtp.gmail.com";
        fromEmail="null";
        fromPassword="null";
        facebook_page_id="null";
        facebook_url="null";
        twitter_username="null";

    }
    public Company(String monThursO, String monThursC, String friO, String friC, String satO, String satC,
                   String sunO, String sunC, ArrayList<String> apptEmails, String emailPort, String smtpAuth,
                        String starttls, String emailHost, String fromEmail, String fromPassword, String facebook_page_id,
                            String facebook_url, String twitter_username)
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
        this.emailPort=emailPort;
        this.smtpAuth=smtpAuth;
        this.starttls=starttls;
        this.emailHost=emailHost;
        this.fromEmail=fromEmail;
        this.fromPassword=fromPassword;
        this.facebook_url=facebook_url;
        this.facebook_page_id=facebook_page_id;
        this.twitter_username=twitter_username;
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
