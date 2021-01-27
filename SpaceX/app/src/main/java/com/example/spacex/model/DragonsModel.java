package com.example.spacex.model;

import java.io.Serializable;
import java.util.List;

public class DragonsModel implements Serializable {

    private String id, name, type, first_flight, wikipedia, description;
    private List<String> flickr_images;
    private int dry_mass_kg, dry_mass_lb;
    private boolean active;
    private DragonsHelpModel height_w_trunk, diameter;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getFirst_flight() {
        return first_flight;
    }

    public void setFirst_flight(String first_flight) {
        this.first_flight = first_flight;
    }

    public String getWikipedia() {
        return wikipedia;
    }

    public void setWikipedia(String wikipedia) {
        this.wikipedia = wikipedia;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<String> getFlickr_images() {
        return flickr_images;
    }

    public void setFlickr_images(List<String> flickr_images) {
        this.flickr_images = flickr_images;
    }

    public int getDry_mass_kg() {
        return dry_mass_kg;
    }

    public void setDry_mass_kg(int dry_mass_kg) {
        this.dry_mass_kg = dry_mass_kg;
    }

    public int getDry_mass_lb() {
        return dry_mass_lb;
    }

    public void setDry_mass_lb(int dry_mass_lb) {
        this.dry_mass_lb = dry_mass_lb;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public DragonsHelpModel getHeight_w_trunk() {
        return height_w_trunk;
    }

    public void setHeight_w_trunk(DragonsHelpModel height_w_trunk) {
        this.height_w_trunk = height_w_trunk;
    }

    public DragonsHelpModel getDiameter() {
        return diameter;
    }

    public void setDiameter(DragonsHelpModel diameter) {
        this.diameter = diameter;
    }
}
