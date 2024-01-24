package com.flipkart.client;

import com.flipkart.business.*;

import java.util.Scanner;


public class GymOwnerClient {

    //private List<GymOwner> gymOwnerList = gymOwnerDAO.getGymOwnerList();
    private GymOwnerServiceInterface gymOwnerService = new GymOwnerService();
    private SlotServiceInterface slotService = new SlotService();
    private GymCentreServiceInterface gymCentreService = new GymCentreService();




    public boolean gymOwnerLogin(String userName, String password) {
        if (gymOwnerService.loginGymOwner(userName,password)) {
            System.out.println("Successfully logged in");
            System.out.println("GymOwner MENU");
        } else {
            new Exception("Gymowner Login Failed");
            return false;
        }
        return true;
    }

    public void register() {
        Scanner scanner=new Scanner(System.in);
        System.out.println("Enter your UserName");
        String userName = scanner.next();

        System.out.println("Enter your Passkey");
        String password = scanner.next();

        System.out.println("Enter your Email");
        String email = scanner.next();

        System.out.println("Enter your PAN Number");
        String panNumber = scanner.next();

        System.out.println("Enter your Card Number");
        String cardNumber = scanner.next();

        gymOwnerService.registerGymOwner(userName,userName,password,email,panNumber,cardNumber);
        System.out.println("GymOwner MENU");
    }







}