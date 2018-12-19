package com.example.javaserver.entities;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name="Companies")
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="companyID")
    private Integer companyID;

    @Column
    private String name;

    @Column(unique = true)
    private String url;

    @Column
    private String postalCode;

    @Column
    private String locationStreet;
    @Column
    private String locationCity;

    @Column
    private String locationCountry;

    @OneToMany()
    private Set<Offer> offersID;

    public Integer getCompanyID() {
        return companyID;
    }

    public void setCompanyID(Integer companyID) {
        this.companyID = companyID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getLocationStreet() {
        return locationStreet;
    }

    public void setLocationStreet(String locationStreet) {
        this.locationStreet = locationStreet;
    }

    public String getLocationCity() {
        return locationCity;
    }

    public void setLocationCity(String locationCity) {
        this.locationCity = locationCity;
    }

    public String getLocationCountry() {
        return locationCountry;
    }

    public void setLocationCountry(String locationCountry) {
        this.locationCountry = locationCountry;
    }

    public Set<Offer> getOffersID() {
        return offersID;
    }

    public void setOffersID(Set<Offer> offersID) {
        this.offersID = offersID;
    }
}
