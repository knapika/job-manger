package com.example.javaserver.entities;

import com.example.javaserver.dtos.EquipmentDTO;
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

    @Column
    private String category;

    @Column
    private String title;

    @Column
    private String level;

    @Column
    private String technology;

    @Column
    private String employmentType;

    private boolean isFavorite;

    @OneToOne(mappedBy = "offer", cascade = CascadeType.PERSIST,
            fetch = FetchType.LAZY, optional = false)
    private Equipment equipment;

    @ManyToOne()
    @JoinColumn(name="companyID")
    private Company company;

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

    public Equipment getEquipment() {
        return equipment;
    }

    public void setEquipment(Equipment equipment) {
        this.equipment = equipment;
    }

    public String getTechnology() {
        return technology;
    }

    public void setTechnology(String technology) {
        this.technology = technology;
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

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getEmploymentType() {
        return employmentType;
    }

    public void setEmploymentType(String employmentType) {
        this.employmentType = employmentType;
    }

    public boolean getIsFavorite() {
        return isFavorite;
    }

    public void setIsFavorite(boolean favorite) {
        isFavorite = favorite;
    }
}
