package com.mrede003.zoomwireless.zoomapp;

/**
 * Created by mrede003 on 2/20/17.
 */

public class PromoObj {
    private String imgName;
    private String name;
    private String description;
    private String expDate;
    private String previewImg;

    public PromoObj(String name, String imgName, String expDate, String description, String previewImg) {
        this.imgName = imgName;
        this.name = name;
        this.description = description;
        this.expDate = expDate;
        this.previewImg=previewImg;
    }

    public PromoObj() {
        imgName = "";
        name = "";
        description = "";
        expDate = "";
        previewImg="";
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

    public void setPreviewImg(String previewImg) {
        this.previewImg = previewImg;
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
    public String getPreviewImg() {
        return previewImg;
    }
}

