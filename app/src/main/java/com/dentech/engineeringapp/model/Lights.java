package com.dentech.engineeringapp.model;

public class Lights {
    private String rating, loads, hours;

    public Lights(String rating, String loads, String hours) {
        this.rating = rating;
        this.loads = loads;
        this.hours = hours;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getLoads() {
        return loads;
    }

    public void setLoads(String loads) {
        this.loads = loads;
    }

    public String getHours() {
        return hours;
    }

    public void setHours(String hours) {
        this.hours = hours;
    }
}
