package com.tokproject.setrip.model;

public class ModelTokoApd {
    private String uid, uEmail, pTitle, pDescription, pPrice, pLocation, pNumber, pImage;

    public ModelTokoApd() {

    }

    public ModelTokoApd(String uid, String uEmail, String pTitle, String pDescription, String pPrice, String pLocation, String pNumber, String pImage) {
        this.uid = uid;
        this.uEmail = uEmail;
        this.pTitle = pTitle;
        this.pDescription = pDescription;
        this.pPrice = pPrice;
        this.pLocation = pLocation;
        this.pNumber = pNumber;
        this.pImage = pImage;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getuEmail() {
        return uEmail;
    }

    public void setuEmail(String uEmail) {
        this.uEmail = uEmail;
    }

    public String getpTitle() {
        return pTitle;
    }

    public void setpTitle(String pTitle) {
        this.pTitle = pTitle;
    }

    public String getpDescription() {
        return pDescription;
    }

    public void setpDescription(String pDescription) {
        this.pDescription = pDescription;
    }

    public String getpPrice() {
        return pPrice;
    }

    public void setpPrice(String pPrice) {
        this.pPrice = pPrice;
    }

    public String getpLocation() {
        return pLocation;
    }

    public void setpLocation(String pLocation) {
        this.pLocation = pLocation;
    }

    public String getpNumber() {
        return pNumber;
    }

    public void setpNumber(String pNumber) {
        this.pNumber = pNumber;
    }

    public String getpImage() {
        return pImage;
    }

    public void setpImage(String pImage) {
        this.pImage = pImage;
    }
}
