package com.example.javaserver.entities;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name="Skills")
public class Skill {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="skillID")
    private Integer skillID;

    @Column
    private Integer rank;

    @Column
    private String value;

    @ManyToMany(mappedBy = "musts")
    Set<Offer> musts;

    @ManyToMany(mappedBy = "nices")
    Set<Offer> nices;

    @ManyToMany(mappedBy = "nices")
    Set<Offer> langs;

    public Skill() {
    }

    public Integer getRank() {
        return rank;
    }

    public void setRank(Integer rank) {
        this.rank = rank;
    }

    public Integer getSkillID() {
        return skillID;
    }

    public void setSkillID(Integer skillID) {
        this.skillID = skillID;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Set<Offer> getMusts() {
        return musts;
    }

    public void setMusts(Set<Offer> musts) {
        this.musts = musts;
    }

    public Set<Offer> getNices() {
        return nices;
    }

    public void setNices(Set<Offer> nices) {
        this.nices = nices;
    }

    public Set<Offer> getLangs() {
        return langs;
    }

    public void setLangs(Set<Offer> langs) {
        this.langs = langs;
    }
}
