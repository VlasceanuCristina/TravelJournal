package com.example.elenavlasceanu.traveljournal;

public class Destination {
    private String mName;
    private String mLocation;
    private String mPicture;

    public String getLocation() {
        return mLocation;
    }

    public void setLocation(String location) {
        mLocation= location;
    }
    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getPicture() {
        return mPicture;
    }

    public void setPicture(String picture) {
        mPicture = picture;
    }

    public Destination(String name , String location, String picture) {
        mLocation = location;
        mName = name;
        mPicture = picture;
    }
}


