package com.example.spacex.model;

import java.io.Serializable;

public class RocketsMassModel implements Serializable {
    private long kg, lb;

    public long getKg() {
        return kg;
    }

    public void setKg(long kg) {
        this.kg = kg;
    }

    public long getLb() {
        return lb;
    }

    public void setLb(long lb) {
        this.lb = lb;
    }
}
