package com.tokproject.setrip.model;

public class ModelTourGuide {
    private String uid, uEmail, pTitle, pDescription, pAge, pLocation, pNumber, pImage;

    public ModelTourGuide() {

    }

    public ModelTourGuide(String uid, String uEmail, String pTitle, String pDescription, String pAge, String pLocation, String pNumber, String pImage) {
        this.uid = uid;
        this.uEmail = uEmail;
        this.pTitle = pTitle;
        this.pDescription = pDescription;
        this.pAge = pAge;
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

    public String getpAge() {
        return pAge;
    }

    public void setpAge(String pAge) {
        this.pAge = pAge;
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
