package com.flipkart.dao;

import com.flipkart.bean.Customer;
import com.flipkart.exceptions.RegistrationFailedException;
import com.flipkart.exceptions.UserInvalidException;

public interface CustomerInterfaceDAO {
    void registerCustomer(String userName, String password, String email, String phoneNumber, String cardNumber) throws RegistrationFailedException;
    boolean isUserValid(String userName, String password) throws UserInvalidException;
    Customer getCustomerById(String userName);
    boolean editCustomer(String customerId, String username, String email, String phoneNumber, String cardNumber);
    boolean updatePassword(String customerId,String newPassword);
}