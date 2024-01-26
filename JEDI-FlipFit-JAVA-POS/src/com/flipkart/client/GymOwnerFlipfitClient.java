package com.flipkart.client;

import com.flipkart.bean.GymCentre;
import com.flipkart.bean.GymOwner;
import com.flipkart.bean.Slot;
import com.flipkart.dao.GymOwnerDAO;
import com.flipkart.business.*;
import com.flipkart.exceptions.LoginFailedException;
import com.flipkart.utils.util;

import java.time.LocalDateTime;
import java.time.LocalTime;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static com.flipkart.client.FlipfitApplication.mainPage;
import static com.flipkart.client.FlipfitApplication.scanner;
import static com.flipkart.constants.Constants.*;

public class GymOwnerFlipfitClient {

    GymOwnerDAO gymOwnerDAO = new GymOwnerDAO();
    //private List<GymOwner> gymOwnerList = gymOwnerDAO.getGymOwnerList();
    private GymOwnerFlipfitServiceInterface gymOwnerService = new GymOwnerFlipfitImplService();
    private SlotFlipfitServiceInterface slotService = new SlotFlipfitImplService();
    private GymCentreFlipfitServiceInterface gymCentreService = new GymCentreFlipfitImplService();

    public boolean gymOwnerLogin(String userName, String password) {
        if (gymOwnerService.loginGymOwner(userName,password)) {
            System.out.println("Successfully logged in");
            gymOwnerClientMainPage(userName);
        } else {
            new LoginFailedException("Gym Owner Login Failed");
            return false;
        }
        return true;
    }

    public void register() {
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
//        gymOwnerClientMainPage(userName);
        mainPage();
    }

    public void registerGymOwnerManually(String userid, String userName, String password, String email, String panNumber, String cardNumber){
        gymOwnerService.registerGymOwner(userid,userName,password,email,panNumber,cardNumber);
    }

    public void gymOwnerClientMainPage(String gymOwnerId) {
        LocalDateTime currentTime = LocalDateTime.now();
        DateTimeFormatter myFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String formattedDate = currentTime.format(myFormat);
        System.out.println(YELLOW_COLOR+"WELCOME "+gymOwnerId+" !!\nWhat you what to do\nLogin TIME: "+currentTime+RESET_COLOR);

        boolean isContinue = true;

        while(isContinue){
            System.out.println("" +
                    "0: View Profile\n" +
                    "1: Edit Profile\n" +
                    "2. View all Gym Centres\n" +
                    "3. Sending Gym Owner Approval Request\n" +
                    "4. Add a new Gym Center\n" +
                    "5. Send a Gym Centre Approval Request to Admin\n" +
                    "6. Add Slots to a Gym Centre\n" +
                    "7: View All Slots by Centre\n" +
                    "8: Delete Slot\n" +
                    "9: Exit"
            );

            GymOwner g = gymOwnerService.viewGymOwnerProfile(gymOwnerId);
            if(g.getIsApproved()==0){
                System.out.println("You are currently not approved. Please press 3 to send a approval request.");
            }

            if(g.getIsApproved()==2){
                System.out.println("Your approval request is under process.");
            }

            int choice = scanner.nextInt();


            switch (choice) {
                /* Take input from user for all service parameters ( Make the menu ) */
                case 0:
                    GymOwner owner = gymOwnerService.viewGymOwnerProfile(gymOwnerId);
                    if (owner.getUserID().equals("none")) System.out.println("User not found");
                    System.out.println("Profile: \nUsername: " + owner.getUserName() + "\nPAN Card: " + owner.getPanNumber() + "\nCard Number: " + owner.getCardDetails());
                    break;
                case 1:
                    //edit profile
                    editGymOwnerProfile(gymOwnerId);
                    break;
                case 2:
                    List<GymCentre> allGymCentres = gymCentreService.getAllCentresByOwmerId(gymOwnerId);
                    util.printGymCentres(allGymCentres);
                    break;
                case 3:
                    gymOwnerService.requestGymOwnerApproval(gymOwnerId);
                    break;

                case 4:

                    System.out.println("Enter gym centre id: ");
                    String gymId = scanner.next();

                    System.out.println("Enter Gym Centre name: ");
                    String gymCentreName = scanner.next();

                    System.out.println("Enter Gym Centre GSTIN: ");
                    String gstin = scanner.next();

                    System.out.println("Enter Gym Centre Location:\n1. North Bangalore\n2. South Bangalore\n3. West Bangalore \n4. East Bangalore \n");
                    int gymLocationChoice = scanner.nextInt();
                    String city = "Bangalore";
                    switch (gymLocationChoice) {
                        case 1:
                            city = "North Bangalore";
                            break;
                        case 2:
                            city = "South Bangalore";
                            break;
                        case 3:
                            city = "West Bangalore";
                            break;
                        case 4:
                            city = "East Bangalore";
                            break;
                    }

                    System.out.println("Enter Gym Centre capacity: ");
                    int capacity = scanner.nextInt();

                    System.out.println("Enter price: ");
                    int price = scanner.nextInt();

                    gymCentreService.addCenter(
                            new GymCentre(
                                    gymId,
                                    gymOwnerId,
                                    gymCentreName,
                                    gstin,
                                    city,
                                    capacity,
                                    price
                            )
                    );
                    break;

                case 5:

                    System.out.println("Enter Gym Centre Id: ");
                    String gymCentreId = scanner.next();

                    gymCentreService.requestGymCentreApproval(gymCentreId);
                    break;

                case 6:

                    boolean isAdding = true;
                    String centreId = null;

                    List<Slot> newSlotList = new ArrayList<>();
                    while (isAdding) {
                        System.out.println("Enter new slot ID: ");
                        String slotId = scanner.next();

                        System.out.println("Enter Gym Centre ID: ");
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

                        System.out.println("Do you want to enter more slots (yes/no)?: ");
                        String addChoice = scanner.next();
                        addChoice = addChoice.toLowerCase();

                        if (addChoice.equals("n") || addChoice.equals("no")) {
                            isAdding = false;
                        }
                    }

                    slotService.addSlotsForGym(centreId, newSlotList);
                    break;
                case 7:
                    System.out.println("Enter Gym Centre ID: ");
                    String id = scanner.next();
                    List<Slot> slotList = slotService.getAllSlotsByCentre(id);
                    for (Slot slot : slotList) {
                        System.out.println("Slot ID: " + slot.getSlotId() + ", Centre ID: " + slot.getCentreID() + ", Time: " + slot.getTime());
                    }
                    break;

                case 8:
                    System.out.println("Enter Slot ID: ");
                    String slotIdToDelete = scanner.next();
                    boolean flag = slotService.deleteSlotById(slotIdToDelete);
                    if (flag) System.out.println("Successfully Deleted");
                    else System.out.println("Slot not found");

                    break;

                case 9:
                    System.out.println("Do you wish to continue? (yes/no)");
                    String ans = scanner.next();
                    if(ans.equals("n") || ans.equals("no")) isContinue = false;
                    break;

                default:
                    System.out.println(INVALID_CHOICE_ERROR);
                    break;
            }

        }
    }

    public void editGymOwnerProfile(String gymOwnerId){

        System.out.println(YELLOW_COLOR+"WELCOME TO EDIT PROFILE");
        System.out.println(YELLOW_COLOR+"Select what you want to edit");
        System.out.println("1. Edit user name\n2. Edit email\n3. Edit card details\n4. Go Back");
        int choice = scanner.nextInt();

        GymOwner gymOwner = gymOwnerService.viewGymOwnerProfile(gymOwnerId);
        boolean status = false;
        switch(choice){
            case 1:
                System.out.println("Enter new user name: ");
                String name = scanner.next();
                status = gymOwnerService.editProfile(gymOwner.getUserID(), name, null, null);
                break;
            case 2:
                System.out.println("Enter new email: ");
                String email = scanner.next();
                status = gymOwnerService.editProfile(gymOwner.getUserID(), null, email, null);
                break;
            case 3:
                System.out.println("Enter new card number: ");
                String cardNumber = scanner.next();
                status = gymOwnerService.editProfile(gymOwner.getUserID(), null, null, cardNumber);
                break;
            case 4:
                gymOwnerClientMainPage(gymOwner.getUserID());
                return;
            default:
                System.out.println(INVALID_CHOICE_ERROR);
                break;
        }

        if(status){
            System.out.println(GREEN_COLOR+"Successfully edited gym owner details");
        }else{
            System.out.println(RED_COLOR+"Couldn't edit customer details");
        }

        editGymOwnerProfile(gymOwner.getUserID());
    }

    public boolean validateCredentials(String userName,String password){
        if (gymOwnerService.loginGymOwner(userName, password)) return true;
        else return false;
    }


}
