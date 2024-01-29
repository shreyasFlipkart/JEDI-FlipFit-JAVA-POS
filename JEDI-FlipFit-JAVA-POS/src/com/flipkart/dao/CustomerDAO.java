package com.flipkart.dao;

import com.flipkart.bean.Customer;
import com.flipkart.exceptions.RegistrationFailedException;
import com.flipkart.exceptions.UserInvalidException;
import com.flipkart.utils.DBConnection;
import com.flipkart.utils.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static com.flipkart.constants.SQLConstants.*;
import static com.flipkart.constants.Constants.*;

public class CustomerDAO implements CustomerInterfaceDAO {
    private static CustomerDAO instance;

    public static CustomerDAO getInstance() {
        if (instance == null) {
            instance = new CustomerDAO();
        }
        return instance;
    }

    public void registerCustomer(String userName, String password, String email, String phoneNumber, String cardNumber) throws RegistrationFailedException {
        try {
            Connection conn = DBConnection.connect();
            PreparedStatement stmt = conn.prepareStatement(ADD_NEW_CUSTOMER);
            stmt.setString(1, util.generateNewId());
            stmt.setString(2, userName);
            stmt.setString(3, password);
            stmt.setString(4, email);
            stmt.setString(5, phoneNumber);
            stmt.setString(6, cardNumber);

            stmt.executeUpdate();
            stmt.close();

        } catch (SQLException exp) {
            exp.printStackTrace();
            throw new RegistrationFailedException(RED_COLOR+"Failed to register the user. Try again."+RESET_COLOR);
        } catch (Exception e) {
            System.out.println(RED_COLOR+"Oops! An error occurred. Try again later."+RESET_COLOR);
        }
    }

    public boolean isUserValid(String userName, String password) throws UserInvalidException {
        try {
            Connection conn = DBConnection.connect();
            PreparedStatement stmt = conn.prepareStatement(CUSTOMER_LOGIN_QUERY);
            stmt.setString(1, userName);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                stmt.close();
                return true;
            }
            stmt.close();
        } catch (SQLException exp) {
            throw new UserInvalidException(RED_COLOR+"User is Invalid. Try again."+RESET_COLOR);
        } catch (Exception exp) {
            System.out.println(RED_COLOR+"Oops! An error occurred. Try again later."+RESET_COLOR);
        }
        return false;
    }

    public Customer getCustomerById(String userName) {
        Customer customer = new Customer();
        try {
            Connection conn = DBConnection.connect();
            PreparedStatement stmt = conn.prepareStatement(GET_CUSTOMER_BY_ID);
            stmt.setString(1, userName);
            ResultSet rs = stmt.executeQuery();
            rs.next();
            customer.setEmail(rs.getString("email"));
            customer.setUserID(rs.getString("Id"));
            customer.setPassword(rs.getString("password"));
            customer.setUserName(rs.getString("name"));
            customer.setCustomerPhone(rs.getString("phone"));
            customer.setCardDetails(rs.getString("cardDetails"));

            stmt.close();
        } catch (SQLException exp) {
            exp.printStackTrace();
        } catch (Exception exp) {
            exp.printStackTrace();
        }

        return customer;
    }

    public boolean editCustomer(String customerId, String username, String email, String phoneNumber, String cardNumber) {
        try {
            Connection conn = DBConnection.connect();
            PreparedStatement stmt = conn.prepareStatement(EDIT_CUSTOMER_QUERY);
            stmt.setString(1, username);
            stmt.setString(2, email);
            stmt.setString(3, phoneNumber);
            stmt.setString(4, cardNumber);
            stmt.setString(5, customerId);

            int rowsAffected = stmt.executeUpdate();
            stmt.close();

            return rowsAffected > 0;
        } catch (SQLException exp) {
            exp.printStackTrace();
        } catch (Exception exp) {
            exp.printStackTrace();
        }

        return false;
    }

    public boolean updatePassword(String customerName, String newPassword) {
        try {
            Connection conn = DBConnection.connect();
            PreparedStatement stmt = conn.prepareStatement(UPDATE_CUSTOMER_PASSWORD_QUERY);
            stmt.setString(1, newPassword);
            stmt.setString(2, customerName);

            int rowsAffected = stmt.executeUpdate();
            stmt.close();

            return rowsAffected > 0;
        } catch (SQLException exp) {
            exp.printStackTrace();
        } catch (Exception exp) {
            exp.printStackTrace();
        }

        return false;
    }

    private String generateUniqueCustomerId(String userName) {
        // Implement logic to generate a unique customer ID based on your requirements
        return userName + System.currentTimeMillis();
    }
}
