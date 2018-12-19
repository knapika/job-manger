package com.example.javaserver.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name="Offers")
public class Offer {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="offerID")
    private Integer offerID;

    @Column
    private String postingID;

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
}
