package com.example.javaserver.entities;

import javax.persistence.*;

@Entity
@Table(name="Skills")
public class Skill {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name="skillID")
    private Integer skillID;

    @Column
    private Integer rank;

    @Column
    private String value;

    @ManyToOne
    @JoinColumn(name = "offerID")
    private Offer offer;

    public Integer getSkillID() {
        return skillID;
    }

    public void setSkillID(Integer skillID) {
        this.skillID = skillID;
    }

    public Integer getRank() {
        return rank;
    }

    public void setRank(Integer rank) {
        this.rank = rank;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public void setOffer(Offer offer) {
        this.offer = offer;
    }

    public Offer getOffer() {
        return offer;
    }
}
