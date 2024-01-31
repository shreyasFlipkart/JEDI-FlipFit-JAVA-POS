package com.flipkart.dao;

import com.flipkart.bean.Customer;
import com.flipkart.exceptions.RegistrationFailedException;
import com.flipkart.exceptions.UserInvalidException;
/**
 * This interface defines the data access operations related to customer functionality.
 * It includes methods for customer registration, validating user credentials, and retrieving
 * customer details by user ID.
 */
public interface CustomerInterfaceDAO {
    /**
     * Registers a new customer with the provided details.
     *
     * @param userName     The username chosen by the customer
     * @param password     The password chosen by the customer
     * @param email        The email address of the customer
     * @param phoneNumber  The phone number of the customer
     * @param cardNumber   The card number associated with the customer
     * @throws RegistrationFailedException If the registration process fails
     */
    void registerCustomer(String userName, String password, String email, String phoneNumber, String cardNumber) throws RegistrationFailedException;
    /**
     * Validates the user's credentials (username and password).
     *
     * @param userName The username entered by the user
     * @param password The password entered by the user
     * @return True if the user is valid, false otherwise
     * @throws UserInvalidException If the user is not valid
     */
    boolean isUserValid(String userName, String password) throws UserInvalidException;
    /**
     * Retrieves customer details based on the provided username.
     *
     * @param userName The username of the customer
     * @return Customer object representing the customer details
     */
    Customer getCustomerById(String userName);
    /**
     * Edits a customer with the provided details.
     *
     * @param customerId    The Id of customer
     * @param username     The username chosen by the customer
     * @param email        The email address of the customer
     * @param phoneNumber  The phone number of the customer
     * @param cardNumber   The card number associated with the customer
     * @return boolean
     */
    boolean editCustomer(String customerId, String username, String email, String phoneNumber, String cardNumber);
    /**
     * Updates the customer password.
     *
     * @param customerId The customerId of the user
     * @param newPassword The new password entered by the user
     * @return True if the operation is successful, false otherwise
     *
     */
    boolean updatePassword(String customerId, String newPassword);
}