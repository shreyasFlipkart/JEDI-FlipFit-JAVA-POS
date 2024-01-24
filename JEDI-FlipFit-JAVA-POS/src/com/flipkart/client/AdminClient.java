package com.flipkart.client;

import com.flipkart.bean.Admin;
import com.flipkart.business.AdminService;
import com.flipkart.business.AdminServiceInterface;
import com.flipkart.business.GymOwnerService;
import com.flipkart.business.GymOwnerServiceInterface;


public class AdminClient {

    private static Admin admin = new Admin();
    private static AdminServiceInterface adminService = new AdminService();
    private static GymOwnerServiceInterface gymOwnerService = new GymOwnerService();

    public boolean isUserValid(String userName, String password) {
        if (userName.equals(admin.getUserName()) && password.equals(admin.getPassword())) {
            return true;
        }
        return false;
    }

    public boolean adminLogin(String userName, String password) {
        if (isUserValid(userName, password)) {
            System.out.println("Successfully logged in");
            System.out.println("Admin MENU");
        }
        else{
            new Exception("Admin Login Failed");
            return false;
        }
        return true;
    }


}

