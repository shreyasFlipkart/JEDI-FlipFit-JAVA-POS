package com.flipkart.business;

import com.flipkart.bean.*;

import java.sql.Date;
import java.util.List;


/**
 * Interface defining the contract for Flipfit services related to customer operations.
 */
public interface CustomerFlipfitServiceInterface {

    /**
     * Retrieves details of all gym centers in a given city.
     * @param city
     */
    List<GymCentre> getAllGymCenterDetailsByCity(String city);

    /**
     * Retrieves available slots for a specific gym center on a given date.
     * @param date
     * @param centreID
     */
    List<Slot> getAvailableSlots(String centreID, Date date);

    /**
     * Retrieves bookings made by a specific customer.
     * @param customerId
     */
    List<Booking> getCustomerBookings(String customerId);

    /**
     * Books a slot for a user on a specified date and gym center.
     * @param date
     * @param slotId
     * @param centreId
     * @param userID
     */
    boolean bookSlot(String userID, Date date, String slotId, String centreId);

    /**
     * Cancels a booking based on the booking ID.
     * @param bookingID
     */
    void cancelBookingbyID(String bookingID);

    /**
     * Registers a new customer with the provided details.
     *
     * @param email       email of the customer
     * @param cardNumber  cardnumber of the customer
     * @param password    password of te customer
     * @param phoneNumber phonenumber of the customer
     * @param userName    username of the customer
     * @return
     */
    Customer registerCustomer(String userName, String password, String email, String phoneNumber, String cardNumber);

    /**
     * Views the profile of a customer based on the username.
     * @param userName username of the customer
     */
    Customer viewMyProfile(String userName);

    /**
     * Validates user credentials for login.
     * @param userName username of the customer
     * @param password password of the customer
     */
    boolean isUserValid(String userName, String password);

    /**
     * Edits the profile of a customer with the given details.
     * @param phoneNumber phone number of the customer
     * @param cardNumber cardnumber of the customer
     * @param email email of the customer
     * @param customerId customerid of the customer
     * @param username username of the customer
     */
    boolean editProfile(String customerId, String username, String email, String phoneNumber, String cardNumber);

    /**
     * Retrieves the plan subscribed by a customer.
     * @param userName username of the customer
     */
    List<UserPlan> getCustomerPlan(String userName);

    /**
     * Updates the password for a customer.
     * @param customerId id of the customer
     * @param newPassword newpassword of the customer
     */
    boolean updatePassword(String customerId, String newPassword);

    /**
     * Retrieves gym centers sorted by cities.
     */
    List<GymCentre> getCentersSortedByCities();
}