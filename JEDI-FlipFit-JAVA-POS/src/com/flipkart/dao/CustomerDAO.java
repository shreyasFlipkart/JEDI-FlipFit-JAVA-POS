package com.flipkart.dao;

import com.flipkart.bean.Customer;
import com.flipkart.exceptions.RegistrationFailedException;
import com.flipkart.exceptions.UserInvalidException;

import java.util.ArrayList;
import java.util.List;

public class CustomerDAO implements CustomerInterfaceDAO {
    private static CustomerDAO instance;
    public static CustomerDAO getInstance() {
        if (instance == null) {
            instance = new CustomerDAO();
        }
        return instance;
    }

    private static List<Customer> allCustomers = new ArrayList<>();

    public static void setAllCustomers(){
        allCustomers.add(new Customer("12341","krish","krish@gmail.com","12345","1234567890","123412341234"));

    }

    public void registerCustomer(String userName, String password, String email, String phoneNumber, String cardNumber) throws RegistrationFailedException {
        try {
            // Assuming you have a method to generate a unique customer ID
            String customerId = generateUniqueCustomerId(userName);

            // Create a Customer object
            Customer customer = new Customer(customerId, userName, email,password, phoneNumber, cardNumber);

            // Add the Customer object to the list
            allCustomers.add(customer);
        } catch (Exception e) {
            throw new RegistrationFailedException("Failed to register the user. Try again.");
        }
    }

    public boolean isUserValid(String userName, String password) throws UserInvalidException {
        for (Customer customer : allCustomers) {
            if (customer.getUserName().equals(userName) && customer.getPassword().equals(password)) {
                return true;
            }
        }
        throw new UserInvalidException("User is Invalid. Try again.");
    }

    public Customer getCustomerById(String userName) {
        for (Customer customer : allCustomers) {
            if (customer.getUserName().equals(userName)) {
                return customer;
            }
        }
        return null; // or throw an exception if not found
    }

    public boolean editCustomer(String customerId, String username, String email, String phoneNumber, String cardNumber){
        for(Customer customer : allCustomers) {
            if(customer.getUserID().equals(customerId) ) {
                if(username!=null)customer.setUserName(username);
                if(email!=null)customer.setEmail(email);
                if(phoneNumber!=null)customer.setCustomerPhone(phoneNumber);
                if(cardNumber!=null)customer.setCardDetails(cardNumber);
                return true;
            }
        }
        return false;
    }
    public boolean updatePassword(String customerName,String newPassword){
        for(Customer customer: allCustomers){
            if(customer.getUserName().equals(customerName)){
                customer.setPassword(newPassword);
                break;
            }
        }
        return true;
    }

    private String generateUniqueCustomerId(String userName) {
        // Implement logic to generate a unique customer ID based on your requirements
        return userName + System.currentTimeMillis();
    }
}