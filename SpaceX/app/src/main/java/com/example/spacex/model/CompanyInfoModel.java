package com.example.spacex.model;

public class CompanyInfoModel {
    private String name, founder, ceo, cto, coo, cto_propulsion, summary;
    private int founded, employees, vehicles, launch_sites, test_sites;
    private long valuation;
    private CompanyHeadquartersModel headquarters;
    private CompanyLinksModel links;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFounder() {
        return founder;
    }

    public void setFounder(String founder) {
        this.founder = founder;
    }

    public int getFounded() {
        return founded;
    }

    public void setFounded(int founded) {
        this.founded = founded;
    }

    public int getEmployees() {
        return employees;
    }

    public void setEmployees(int employees) {
        this.employees = employees;
    }

    public long getValuation() {
        return valuation;
    }

    public void setValuation(long valuation) {
        this.valuation = valuation;
    }

    public CompanyHeadquartersModel getHeadquarters() {
        return headquarters;
    }

    public void setHeadquarters(CompanyHeadquartersModel headquarters) {
        this.headquarters = headquarters;
    }

    public CompanyLinksModel getLinks() {
        return links;
    }

    public void setLinks(CompanyLinksModel links) {
        this.links = links;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public int getVehicles() {
        return vehicles;
    }

    public void setVehicles(int vehicles) {
        this.vehicles = vehicles;
    }

    public int getLaunch_sites() {
        return launch_sites;
    }

    public void setLaunch_sites(int launch_sites) {
        this.launch_sites = launch_sites;
    }

    public int getTest_sites() {
        return test_sites;
    }

    public void setTest_sites(int test_sites) {
        this.test_sites = test_sites;
    }

    public String getCeo() {
        return ceo;
    }

    public void setCeo(String ceo) {
        this.ceo = ceo;
    }

    public String getCto() {
        return cto;
    }

    public void setCto(String cto) {
        this.cto = cto;
    }

    public String getCoo() {
        return coo;
    }

    public void setCoo(String coo) {
        this.coo = coo;
    }

    public String getCto_propulsion() {
        return cto_propulsion;
    }

    public void setCto_propulsion(String cto_propulsion) {
        this.cto_propulsion = cto_propulsion;
    }
}
