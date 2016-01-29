package com.matchandfind.model;

import com.google.gson.annotations.Expose;

public class Person {

    @Expose
    private int id;
    @Expose
    private String location;
    @Expose
    private String photo;
    @Expose
    private String status;

    private float lat;

    private float lon;

    public Person() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public float getLat() {
        return lat == 0f ? Float.valueOf(location.split(",")[0]) : lat;
    }

    public float getLon() {
        return lon == 0f ? Float.valueOf(location.split(",")[1]) : lon;
    }

    public void setLat(float lat) {
        this.lat = lat;
    }

    public void setLon(float lon) {
        this.lon = lon;
    }
}
