package com.example.javaserver.entities;

import javax.persistence.*;

@Entity
@Table(name="Favorites")
public class FavoriteOffer {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long favoriteID;

    @Column
    private Integer userID;

    @Column
    private Integer offerID;

    public FavoriteOffer() {
    }

    public FavoriteOffer(Integer userID, Integer offerID) {
        this.userID = userID;
        this.offerID = offerID;
    }

    public Long getFavoriteID() {
        return favoriteID;
    }

    public void setFavoriteID(Long favoriteID) {
        this.favoriteID = favoriteID;
    }

    public Integer getUserID() {
        return userID;
    }

    public void setUserID(Integer userID) {
        this.userID = userID;
    }

    public Integer getOfferID() {
        return offerID;
    }

    public void setOfferID(Integer offerID) {
        this.offerID = offerID;
    }
}
