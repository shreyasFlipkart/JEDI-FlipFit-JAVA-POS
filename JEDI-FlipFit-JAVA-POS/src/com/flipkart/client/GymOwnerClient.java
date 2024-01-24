package com.flipkart.client;

import com.flipkart.bean.Slot;
import com.flipkart.business.*;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import com.flipkart.business.GymOwnerService;
import com.flipkart.business.GymOwnerServiceInterface;

public class GymOwnerClient {

    //private List<GymOwner> gymOwnerList = gymOwnerDAO.getGymOwnerList();
    private GymOwnerServiceInterface gymOwnerService = new GymOwnerService();


    public boolean gymOwnerLogin(String userName, String password) {
        if (gymOwnerService.loginGymOwner(userName,password)) {
            System.out.println("Successfully logged in");
            System.out.println("GymOwner MENU");
            gymOwnerClientMainPage(userName);
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

        gymOwnerClientMainPage(userName);
    }

    public void gymOwnerClientMainPage(String gymOwnerId) {

        LocalDateTime currentTime = LocalDateTime.now();
        DateTimeFormatter myFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String formattedDate = currentTime.format(myFormat);
        System.out.println("WELCOME " + gymOwnerId + " !!        " + formattedDate + "\nWhat do you want to do");

        while (true) {
            System.out.println("" +
                    "0. View all my Gym Centres\n" +
                    "1. Sending Gym Owner Approval Request\n" +
                    "2. Add a new Gym Center\n" +
                    "3. Send a Gym Centre Approval Request to Admin\n" +
                    "4. Add Slots to a Gym Centre\n" +
                    "5: Delete Slot\n"
            );
            Scanner scanner = new Scanner(System.in);
            int choice = scanner.nextInt();
            switch (choice) {
                /* Take input from user for all service parameters ( Make the menu ) */

                case 0:
                    System.out.println("Gym Centres: \n 1. FlipFit, North Bangalore \n 2. FlipFit, South Bangalore");
                    break;

                case 1:
                    System.out.println("Request for Profile Approval Sent Successfully");
                    break;

                case 2:

                    System.out.println("Enter gym centre id: ");
                    String gymId = scanner.next();

                    System.out.println("Enter Gym Centre name: ");
                    String gymCentreName = scanner.next();

                    System.out.println("Enter Gym Centre GSTIN: ");
                    String gstin = scanner.next();

                    System.out.println("Enter Gym Centre city: ");
                    String city = scanner.next();

                    System.out.println("Enter Gym Centre capacity: ");
                    int capacity = scanner.nextInt();

                    System.out.println("Enter price: ");
                    int price = scanner.nextInt();

                    System.out.println("Gym centre " + gymCentreName + " with ID " + gymId + " has been successfully added");
                    break;

                case 3:

                    System.out.println("Enter Gym Centre Id: ");
                    String gymCentreId = scanner.next();

                    System.out.println("Approval Request for Gym Centre with ID: " + gymCentreId + " has been successfully sent");
                    break;

                case 4:

                    boolean isAdding = true;
                    String centreId = null;

                    List<Slot> newSlotList = new ArrayList<>();
                    while (isAdding) {
                        System.out.println("Enter new slot id: ");
                        String slotId = scanner.next();

                        System.out.println("Enter Gym Centre Id: ");
                        centreId = scanner.next();

                        System.out.println("Enter time in 24h format (hh:mm:ss) : ");
                        String time = scanner.next();

                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
                        LocalTime localTime = LocalTime.parse(time, formatter);

                        newSlotList.add(new Slot(
                                slotId,
                                centreId,
                                localTime
                        ));

                        System.out.println("Do you want to enter more slots (y/n)?: ");
                        String addChoice = scanner.next();
                        addChoice = addChoice.toLowerCase();

                        if (addChoice.equals("n") || addChoice.equals("no")) {
                            isAdding = false;
                        }
                    }

                    System.out.println("Slots: ");
                    for(Slot slots : newSlotList){
                        System.out.println(slots + "\n");
                    }
                    System.out.println("added successfully");

                    break;

                case 5:
                    System.out.println("Enter Slot ID: ");
                    String slotId = scanner.next();

                    System.out.println("Slot with ID: " + slotId + " has been successfully deleted");

                    default:
                    System.out.println("\nPlease enter valid choice\n");
                    break;
            }
        }
    }




}