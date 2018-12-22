package com.example.javaserver.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Set;

@Entity
@Table(name="Offers")
public class Offer implements Serializable {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="offerID")
    private Integer offerID;

    @Column
    private String postingID;

    @Column
    private Integer salaryFrom;

    @Column
    private Integer salaryTo;

    @Column
    private String salaryCurrency;

    @Column
    private String salaryDuration;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="companyID")
    private Company companyID;

    @ManyToMany(cascade=CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name="musts",
            joinColumns = { @JoinColumn(name = "offerID") },
            inverseJoinColumns = { @JoinColumn(name = "skillID") })
    private Set<Skill> musts;

    @ManyToMany(cascade=CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name="nices",
            joinColumns = { @JoinColumn(name = "offerID") },
            inverseJoinColumns = { @JoinColumn(name = "skillID") })
    private Set<Skill> nices;

    @ManyToMany(cascade=CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name="langs",
            joinColumns = { @JoinColumn(name = "offerID") },
            inverseJoinColumns = { @JoinColumn(name = "skillID") })
    private Set<Skill> langs;


    public Offer() {
    }

    public Integer getOfferID() {
        return offerID;
    }

    public void setOfferID(Integer offerID) {
        this.offerID = offerID;
    }

    public String getPostingID() {
        return postingID;
    }

    public void setPostingID(String postingID) {
        this.postingID = postingID;
    }

    public Set<Skill> getMusts() {
        return musts;
    }

    public void setMusts(Set<Skill> musts) {
        this.musts = musts;
    }

    public Set<Skill> getNices() {
        return nices;
    }

    public void setNices(Set<Skill> nices) {
        this.nices = nices;
    }

    public Set<Skill> getLangs() {
        return langs;
    }

    public void setLangs(Set<Skill> langs) {
        this.langs = langs;
    }

    public Company getCompanyID() {
        return companyID;
    }

    public void setCompanyID(Company companyID) {
        this.companyID = companyID;
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
}
