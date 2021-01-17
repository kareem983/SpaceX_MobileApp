package com.example.spacex;

public class LaunchesModel {
    private String mission_name;
    private SubLaunchesModel links;

    public String getMission_name() {
        return mission_name;
    }

    public void setMission_name(String mission_name) {
        this.mission_name = mission_name;
    }

    public SubLaunchesModel getLinks() {
        return links;
    }

    public void setLinks(SubLaunchesModel links) {
        this.links = links;
    }
}
