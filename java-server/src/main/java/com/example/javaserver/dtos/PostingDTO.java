package com.example.javaserver.dtos;

import com.example.javaserver.entities.Skill;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Date;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PostingDTO {
    private String id;
    private Date posted;
    private String category;
    private String city;
    private CompanyDTO company;
    private List<Skill> musts;
    private List<Skill> nices;
    private List<Skill> langs;
    private EssentialsDTO essentials;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getPosted() {
        return posted;
    }

    public void setPosted(Date posted) {
        this.posted = posted;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public CompanyDTO getCompany() {
        return company;
    }

    public void setCompany(CompanyDTO company) {
        this.company = company;
    }

    public List<Skill> getMusts() {
        return musts;
    }

    public void setMusts(List<Skill> musts) {
        this.musts = musts;
    }

    public List<Skill> getNices() {
        return nices;
    }

    public void setNices(List<Skill> nices) {
        this.nices = nices;
    }

    public List<Skill> getLangs() {
        return langs;
    }

    public void setLangs(List<Skill> langs) {
        this.langs = langs;
    }

    public EssentialsDTO getEssentials() {
        return essentials;
    }

    public void setEssentials(EssentialsDTO essentials) {
        this.essentials = essentials;
    }
}
