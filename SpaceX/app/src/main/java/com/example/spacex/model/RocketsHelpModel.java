package com.example.spacex.model;

import java.io.Serializable;

public class RocketsHelpModel implements Serializable {
    private double meters, feet;

    public double getMeters() {
        return meters;
    }

    public void setMeters(double meters) {
        this.meters = meters;
    }

    public double getFeet() {
        return feet;
    }

    public void setFeet(double feet) {
        this.feet = feet;
    }
}
