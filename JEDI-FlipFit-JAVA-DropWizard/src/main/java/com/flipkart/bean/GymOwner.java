package com.flipkart.bean;

import java.util.List;
/**
 * Represents a gym owner in the system, extending the User class.
 */
public class GymOwner extends User {
    //properties
    /**
     * The PAN (Permanent Account Number) of the gym owner.
     */
    private String panNumber;
    /**
     * The list of gym centre IDs associated with the gym owner.
     */
    private List<String> gymCentreIDs;
    /**
     * The card details of the gym owner.
     */
    private String cardDetails;
    /**
     * The approval status of the gym owner.
     */
    private int isApproved;

    public GymOwner() {
        super();
        this.panNumber = panNumber;
        this.cardDetails = cardDetails;
        this.isApproved = 0;
    }
    /**
     * Constructs a new gym owner with the given details.
     *
     * @param userId         The unique identifier for the gym owner.
     * @param userName     The username of the gym owner.
     * @param email        The email address of the gym owner.
     * @param password     The password of the gym owner.
     * @param panNumber    The PAN (Permanent Account Number) of the gym owner.
     * @param cardNumber  The card details of the gym owner.
     * @param approved     The approval status of the gym owner.
     */

    public GymOwner(String userId, String userName, String email, String password, int approved, String panNumber,String cardNumber) {
        super(userId, userName, email, password, Role.GYMOWNER);
        this.panNumber=panNumber;
        this.cardDetails=cardNumber;
        this.isApproved = approved;
    }
    /**
     * Gets the PAN (Permanent Account Number) of the gym owner.
     *
     * @return The PAN number.
     */
    public String getPanNumber() {
        return panNumber;
    }
    /**
     * Sets the PAN (Permanent Account Number) of the gym owner.
     *
     * @param panNumber The PAN number.
     */
    public void setPanNumber(String panNumber) {
        this.panNumber = panNumber;
    }
    /**
     * Gets the list of gym centre IDs associated with the gym owner.
     *
     * @return The list of gym centre IDs.
     */
    public List<String> getGymCentreIDs() {
        return gymCentreIDs;
    }
    /**
     * Sets the list of gym centre IDs associated with the gym owner.
     *
     * @param gymCentreIDs The list of gym centre IDs.
     */
    public void setGymCentreIDs(List<String> gymCentreIDs) {
        this.gymCentreIDs = gymCentreIDs;
    }
    /**
     * Gets the card details of the gym owner.
     *
     * @return The card details.
     */
    public String getCardDetails() {
        return cardDetails;
    }
    /**
     * Sets the card details of the gym owner.
     *
     * @param cardDetails The card details.
     */
    public void setCardDetails(String cardDetails) {
        this.cardDetails = cardDetails;
    }
    /**
     * Gets the approval status of the gym owner.
     *
     * @return The approval status.
     */
    public int getIsApproved() {
        return isApproved;
    }
    /**
     * Sets the approval status of the gym owner.
     *
     * @param isApproved The approval status.
     */
    public void setIsApproved(int isApproved) {
        this.isApproved = isApproved;
    }
}
