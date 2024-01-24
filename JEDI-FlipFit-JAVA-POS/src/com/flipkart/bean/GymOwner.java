package com.flipkart.bean;

import java.util.List;

public class GymOwner extends User {
    //properties
    private String panNumber;
    private List<String> gymCentreIDs;
    private String cardDetails;
    private int isApproved;

    public GymOwner(String userID, String userName, String email, String password, String panNumber, String cardDetails) {
        super(userID, userName, email, password, Role.GYMOWNER);
        this.panNumber = panNumber;
        this.cardDetails = cardDetails;
        this.isApproved = 0;
    }

    public GymOwner(String userId, String userName, String email, String password, int approved) {
        super(userId, userName, email, password, Role.GYMOWNER);
        this.isApproved = approved;
    }

    public String getPanNumber() {
        return panNumber;
    }

    public void setPanNumber(String panNumber) {
        this.panNumber = panNumber;
    }

    public List<String> getGymCentreIDs() {
        return gymCentreIDs;
    }

    public void setGymCentreIDs(List<String> gymCentreIDs) {
        this.gymCentreIDs = gymCentreIDs;
    }

    public String getCardDetails() {
        return cardDetails;
    }

    public void setCardDetails(String cardDetails) {
        this.cardDetails = cardDetails;
    }

    public int getIsApproved() {
        return isApproved;
    }

    public void setIsApproved(int isApproved) {
        this.isApproved = isApproved;
    }
}
