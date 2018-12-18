package com.example.javaserver.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="Offers")
public class Offer {

    @Id
    @Column(name="offerID")
    private String offerID;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "offer")
    private List<Skill> musts;


    public String getOfferID() {
        return offerID;
    }

    public void setOfferID(String offerID) {
        this.offerID = offerID;
    }

    public List<Skill> getMusts() {
        return musts;
    }

    public void setMusts(List<Skill> musts) {
        this.musts = musts;
    }
}
