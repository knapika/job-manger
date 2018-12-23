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
    private Integer salaryFrom;
    private Integer salaryTo;
    private String employmentTypeDesc;


    public String getEmploymentTypeDesc() {
        return employmentTypeDesc;
    }

    public void setEmploymentTypeDesc(String employmentTypeDesc) {
        this.employmentTypeDesc = employmentTypeDesc;
    }

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

    public Integer getSalaryFrom() {
        return salaryFrom;
    }

    public void setSalaryFrom(Integer salaryFrom) {
        this.salaryFrom = salaryFrom;
    }

    public Integer getSalaryTo() {
        return salaryTo;
    }

    public void setSalaryTo(Integer salaryTo) {
        this.salaryTo = salaryTo;
    }
}
