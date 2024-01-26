package com.flipkart.bean;

import java.util.List;

public class GymOwner extends User {
    //properties
    private String panNumber;
    private List<String> gymCentreIDs;
    private String cardDetails;
    private int isApproved;

    public GymOwner() {
        super();
        this.panNumber = panNumber;
        this.cardDetails = cardDetails;
        this.isApproved = 0;
    }

    public GymOwner(String userId, String userName, String email, String password, int approved, String panNumber,String cardNumber) {
        super(userId, userName, email, password, Role.GYMOWNER);
        this.panNumber=panNumber;
        this.cardDetails=cardNumber;
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

    public void setPhoneNumber(String phoneNumber) {
    }

    public String getPhoneNumber() {
        return "9899344949";
    }
}
