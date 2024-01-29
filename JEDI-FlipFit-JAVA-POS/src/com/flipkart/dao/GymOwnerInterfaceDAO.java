package com.flipkart.dao;

import com.flipkart.bean.GymOwner;

import java.util.List;
/**
 * This interface defines the data access operations related to Gym Owner functionality.
 * It includes methods for retrieving gym owner lists, registering new gym owners,
 * managing approval status, and handling approval requests.
 */
public interface GymOwnerInterfaceDAO {
    /**
     * Updates the customer password.
     *
     * @param gymownerName The name of the Gym Owner
     * @param newPassword The new password entered by the user
     * @return True if the operation is successful, false otherwise
     *
     */
    boolean updatePassword(String gymownerName, String newPassword);
    /**
     * Retrieves a list of all registered gym owners.
     *
     * @return List of gym owners
     */
    List<GymOwner> getGymOwnerList();
    void setGymOwnerList(List<GymOwner> gymOwnerList);
    /**
     * Registers a new gym owner.
     *
     * @param gymOwner The GymOwner object representing the details of the new gym owner
     */
    void registerGymOwner(GymOwner gymOwner);
    /**
     * Retrieves a list of pending gym owners awaiting approval.
     *
     * @return List of pending gym owners
     */
    List<GymOwner> getPendingGymOwnerList();
    /**
     * Retrieves a list of pending gym owners awaiting approval.
     * @param gymOwnerId Id of gym owner
     *
     */
    void sendOwnerApprovalRequest(String gymOwnerId);
    void setPendingGymOwnerList();
    /**
     * Validates a gym owner based on the provided owner ID and approval status.
     *
     * @param gymOwnerId The ID of the gym owner to be validated
     * @param isApproved  The approval status (1 for approved, 0 for not approved)
     */
    void validateGymOwner(String gymOwnerId, int isApproved);
    /**
     * Edits a Gym Owner with the provided details.
     *
     * @param gymOwnerId    The Id of Gym Owner
     * @param username     The username chosen by the customer
     * @param email        The email address of the customer
     * @param cardNumber   The card number associated with the customer
     * @return boolean
     */
    public boolean editGymOwner(String gymOwnerId, String username, String email, String cardNumber);
}
