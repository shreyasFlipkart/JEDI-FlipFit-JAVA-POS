package com.flipkart.bean;

public class User {
    // Properties
    private String userID;
    private String userName;
    private String email;
    private String password;
    private Role role;

    public User() {

    }

    public User(String userID, String userName, String email, String password, Role role) {
        this.userID = userID;
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "userID='" + userID + '\'' +
                ", userName='" + userName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", role=" + role;
    }
}
