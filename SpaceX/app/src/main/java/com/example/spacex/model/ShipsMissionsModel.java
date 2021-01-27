package com.example.spacex.model;

import java.io.Serializable;

public class ShipsMissionsModel implements Serializable {
    private String name;
    private long flight;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getFlight() {
        return flight;
    }

    public void setFlight(long flight) {
        this.flight = flight;
    }
}
