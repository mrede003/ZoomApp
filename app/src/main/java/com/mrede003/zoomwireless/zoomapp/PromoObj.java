package com.mrede003.zoomwireless.zoomapp;

/**
 * Created by mrede003 on 2/20/17.
 */

public class PromoObj {
    private String imgName;
    private String name;
    private String description;
    private String expDate;

    public PromoObj(String name, String imgName, String expDate, String description) {
        this.imgName = imgName;
        this.name = name;
        this.description = description;
        this.expDate = expDate;
    }

    public PromoObj() {
        imgName = "";
        name = "";
        description = "";
        expDate = "";
    }

    public void setImgName(String img) {
        imgName = img;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setName(String n) {
        name = n;
    }

    public void setExpDate(String exp) {
        expDate = exp;
    }

    public String getImgName()
    {
        return imgName;
    }
    public String getName()
    {
        return name;
    }
    public String getDescription()
    {
        return description;
    }
    public String getExpDate()
    {
        return expDate;
    }
}

