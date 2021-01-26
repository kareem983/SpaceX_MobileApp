package com.example.spacex.model;

import java.util.List;

public class RocketsModel {
    private int id;
    private long cost_per_launch;
    private boolean active;
    private String first_flight, country, company, wikipedia, rocket_name, description;
    private RocketsHelpModel height, diameter;
    private RocketsMassModel mass;
    private RocketsEngineModel engines;
    private List<String> flickr_images;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getCost_per_launch() {
        return cost_per_launch;
    }

    public void setCost_per_launch(long cost_per_launch) {
        this.cost_per_launch = cost_per_launch;
    }

    public String getFirst_flight() {
        return first_flight;
    }

    public void setFirst_flight(String first_flight) {
        this.first_flight = first_flight;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public RocketsHelpModel getHeight() {
        return height;
    }

    public void setHeight(RocketsHelpModel height) {
        this.height = height;
    }

    public RocketsHelpModel getDiameter() {
        return diameter;
    }

    public void setDiameter(RocketsHelpModel diameter) {
        this.diameter = diameter;
    }

    public RocketsMassModel getMass() {
        return mass;
    }

    public void setMass(RocketsMassModel mass) {
        this.mass = mass;
    }

    public RocketsEngineModel getEngines() {
        return engines;
    }

    public void setEngines(RocketsEngineModel engines) {
        this.engines = engines;
    }

    public List<String> getFlickr_images() {
        return flickr_images;
    }

    public void setFlickr_images(List<String> flickr_images) {
        this.flickr_images = flickr_images;
    }

    public String getWikipedia() {
        return wikipedia;
    }

    public void setWikipedia(String wikipedia) {
        this.wikipedia = wikipedia;
    }

    public boolean getActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getRocket_name() {
        return rocket_name;
    }

    public void setRocket_name(String rocket_name) {
        this.rocket_name = rocket_name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
