package com.flipkart.bean;
/**
 * The User class represents a generic user in the Flipkart application.
 * It includes properties such as user ID, username, email, password, and role.
 * The class provides getter and setter methods for each property and a toString method
 * for generating a string representation of the user.
 *
 * @version 1.0
 * @author
 */
public class User {
    // Properties
    /** The unique identifier for the user. */
    public String userID;

    /** The username chosen by the user. */
    public String userName;

    /** The email address of the user. */
    public String email;

    /** The password associated with the user's account. */
    private String password;

    /** The role assigned to the user (e.g., Gym Owner, Admin, Customer). */
    public Role role;

    /**
     * Default constructor for the User class.
     * Initializes the properties with default values.
     */
    public User() {
        // Implementation details...
    }

    /**
     * Parameterized constructor for the User class.
     * Initializes the properties with the provided values.
     *
     * @param userID The unique identifier for the user.
     * @param userName The username chosen by the user.
     * @param email The email address of the user.
     * @param password The password associated with the user's account.
     * @param role The role assigned to the user.
     */

    public User(String userID, String userName, String email, String password, Role role) {
        this.userID = userID;
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    /**
     * Getter method to retrieve the user ID.
     *
     * @return The user ID.
     */
    public String getUserID() {
        // Implementation details...
        return userID;
    }

    /**
     * Setter method to set the user ID.
     *
     * @param userID The user ID to be set.
     */
    public void setUserID(String userID) {
        this.userID = userID;
    }
    /**
     * Getter method to retrieve the username.
     *
     * @return The username.
     */
    public String getUserName() {
        return userName;
    }

    /**
     * Setter method to set the username.
     *
     * @param userName The username to be set.
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }
    /**
     * Getter method to retrieve the email address.
     *
     * @return The email address.
     */
    public String getEmail() {
        return email;
    }
    /**
     * Setter method to set the email address.
     *
     * @param email The email address to be set.
     */
    public void setEmail(String email) {
        this.email = email;
    }
    /**
     * Getter method to retrieve the password.
     *
     * @return The password.
     */
    public String getPassword() {
        return password;
    }
    /**
     * Setter method to set the password.
     *
     * @param password The password to be set.
     */
    public void setPassword(String password) {
        this.password = password;
    }
    /**
     * Getter method to retrieve the role.
     *
     * @return The role assigned to the user.
     */
    public Role getRole() {
        return role;
    }
    /**
     * Setter method to set the role.
     *
     * @param role The role to be set.
     */
    public void setRole(Role role) {
        this.role = role;
    }
    /**
     * Generates a string representation of the user object.
     *
     * @return A string containing user information.
     */
    @Override
    public String toString() {
        return "userID='" + userID + '\'' +
                ", userName='" + userName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", role=" + role;
    }
}
