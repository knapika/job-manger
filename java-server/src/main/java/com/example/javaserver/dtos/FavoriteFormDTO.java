package com.example.javaserver.dtos;

public class FavoriteFormDTO {
    private Integer userID;
    private Integer offerID;

    public FavoriteFormDTO() {
    }

    public FavoriteFormDTO(Integer userID, Integer offerID) {
        this.userID = userID;
        this.offerID = offerID;
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
