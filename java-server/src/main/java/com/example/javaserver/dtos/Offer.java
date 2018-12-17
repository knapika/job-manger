package com.example.javaserver.dtos;

import java.util.List;

public class Offer {
    private final String id;
    private final String category;
    private final String city;
    private final String street;
    private final String companyName;
    private final String companyUrl;
    private final String salaryFrom;
    private final String salaryTo;
    private final List<Skill> musts;
    private final List<Skill> nices;
    private final List<Skill> langs;


    public Offer(String id, String category, String city, String street, String companyName, String companyUrl,
                 String salaryFrom, String salaryTo, List<Skill> musts, List<Skill> nices, List<Skill> langs) {
        this.id = id;
        this.category = category;
        this.city = city;
        this.street = street;
        this.companyName = companyName;
        this.companyUrl = companyUrl;
        this.salaryFrom = salaryFrom;
        this.salaryTo = salaryTo;
        this.musts = musts;
        this.nices = nices;
        this.langs = langs;
    }

    public String getId() {
        return id;
    }

    public String getCategory() {
        return category;
    }

    public String getCity() {
        return city;
    }

    public String getStreet() {
        return street;
    }

    public String getCompanyName() {
        return companyName;
    }

    public String getCompanyUrl() {
        return companyUrl;
    }

    public String getSalaryFrom() {
        return salaryFrom;
    }

    public String getSalaryTo() {
        return salaryTo;
    }

    public List<Skill> getMusts() {
        return musts;
    }

    public List<Skill> getNices() {
        return nices;
    }

    public List<Skill> getLangs() {
        return langs;
    }
}
