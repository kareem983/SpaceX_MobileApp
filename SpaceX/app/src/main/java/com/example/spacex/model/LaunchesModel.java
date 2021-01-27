package com.example.spacex.model;

import java.io.Serializable;

public class LaunchesModel implements Serializable {

    private int flight_number;
    private String mission_name, launch_year, details, tentative_max_precision;
    private boolean upcoming;
    private LaunchesRocketModel rocket;
    private LaunchesLinksModel links;
    

    public int getFlight_number() {
        return flight_number;
    }

    public void setFlight_number(int flight_number) {
        this.flight_number = flight_number;
    }

    public String getMission_name() {
        return mission_name;
    }

    public void setMission_name(String mission_name) {
        this.mission_name = mission_name;
    }

    public String getLaunch_year() {
        return launch_year;
    }

    public void setLaunch_year(String launch_year) {
        this.launch_year = launch_year;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public boolean isUpcoming() {
        return upcoming;
    }

    public void setUpcoming(boolean upcoming) {
        this.upcoming = upcoming;
    }

    public LaunchesRocketModel getRocket() {
        return rocket;
    }

    public void setRocket(LaunchesRocketModel rocket) {
        this.rocket = rocket;
    }

    public LaunchesLinksModel getLinks() {
        return links;
    }

    public void setLinks(LaunchesLinksModel links) {
        this.links = links;
    }

    public String getTentative_max_precision() {
        return tentative_max_precision;
    }

    public void setTentative_max_precision(String tentative_max_precision) {
        this.tentative_max_precision = tentative_max_precision;
    }
}
