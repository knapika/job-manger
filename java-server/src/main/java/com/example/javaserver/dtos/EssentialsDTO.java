package com.example.javaserver.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class EssentialsDTO {
    private String companySize;
    private String employmentType;
    private String locationCity;
    private LocationCountryDTO locationCountry;
    private String locationStreet;
    private String postalCode;
    private String salaryCurrency;
    private String salaryDuration;
    private String salaryFrom;
    private String salaryTo;

    public String getCompanySize() {
        return companySize;
    }

    public void setCompanySize(String companySize) {
        this.companySize = companySize;
    }

    public String getEmploymentType() {
        return employmentType;
    }

    public void setEmploymentType(String employmentType) {
        this.employmentType = employmentType;
    }

    public String getLocationCity() {
        return locationCity;
    }

    public void setLocationCity(String locationCity) {
        this.locationCity = locationCity;
    }

    public LocationCountryDTO getLocationCountry() {
        return locationCountry;
    }

    public void setLocationCountry(LocationCountryDTO locationCountry) {
        this.locationCountry = locationCountry;
    }

    public String getLocationStreet() {
        return locationStreet;
    }

    public void setLocationStreet(String locationStreet) {
        this.locationStreet = locationStreet;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getSalaryCurrency() {
        return salaryCurrency;
    }

    public void setSalaryCurrency(String salaryCurrency) {
        this.salaryCurrency = salaryCurrency;
    }

    public String getSalaryDuration() {
        return salaryDuration;
    }

    public void setSalaryDuration(String salaryDuration) {
        this.salaryDuration = salaryDuration;
    }

    public String getSalaryFrom() {
        return salaryFrom;
    }

    public void setSalaryFrom(String salaryFrom) {
        this.salaryFrom = salaryFrom;
    }

    public String getSalaryTo() {
        return salaryTo;
    }

    public void setSalaryTo(String salaryTo) {
        this.salaryTo = salaryTo;
    }
}
