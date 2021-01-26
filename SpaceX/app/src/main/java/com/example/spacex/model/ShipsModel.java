package com.example.spacex.model;

import java.util.List;

public class ShipsModel {

    private String ship_id, ship_name, ship_type, home_port, image, url;
    private long weight_kg, year_built;
    private boolean active;
    private List<ShipsMissionsModel> missions;

    public String getShip_id() {
        return ship_id;
    }

    public void setShip_id(String ship_id) {
        this.ship_id = ship_id;
    }

    public String getShip_name() {
        return ship_name;
    }

    public void setShip_name(String ship_name) {
        this.ship_name = ship_name;
    }

    public String getShip_type() {
        return ship_type;
    }

    public void setShip_type(String ship_type) {
        this.ship_type = ship_type;
    }

    public String getHome_port() {
        return home_port;
    }

    public void setHome_port(String home_port) {
        this.home_port = home_port;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public long getWeight_kg() {
        return weight_kg;
    }

    public void setWeight_kg(long weight_kg) {
        this.weight_kg = weight_kg;
    }

    public long getYear_built() {
        return year_built;
    }

    public void setYear_built(long year_built) {
        this.year_built = year_built;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public List<ShipsMissionsModel> getMissions() {
        return missions;
    }

    public void setMissions(List<ShipsMissionsModel> missions) {
        this.missions = missions;
    }
}
