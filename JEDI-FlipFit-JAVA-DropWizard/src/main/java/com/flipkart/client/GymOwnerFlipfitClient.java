package com.flipkart.client;
/**
 * The GymOwnerFlipfitClient class provides methods for interacting with the Gym Owner's functionalities
 * in the Flipfit application.
 * It includes methods for gym owner login, registration, main page navigation, and profile editing.
 * Additionally, it allows gym owners to perform actions related to gym centres and slots.
 *
 * @version 1.0
 * @author
 */

import com.flipkart.bean.GymCentre;
import com.flipkart.bean.GymOwner;
import com.flipkart.bean.Slot;
import com.flipkart.business.*;
import com.flipkart.exceptions.LoginFailedException;
import com.flipkart.utils.util;
import com.flipkart.validator.Validators;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;

import static com.flipkart.client.FlipfitApplication.scanner;
import static com.flipkart.constants.Constants.*;

public class GymOwnerFlipfitClient {


    //private List<GymOwner> gymOwnerList = gymOwnerDAO.getGymOwnerList();
    private GymOwnerFlipfitServiceInterface gymOwnerService = GymOwnerFlipfitImplService.getInstance();
    private SlotFlipfitServiceInterface slotService = new SlotFlipfitImplService();
    private GymCentreFlipfitServiceInterface gymCentreService = new GymCentreFlipfitImplService();
    /**
     * Attempts to log in a gym owner with the provided credentials.
     * If successful, it navigates to the main page for gym owners.
     *
     * @param userName The username of the gym owner.
     * @param password The password of the gym owner.
     * @return True if the login is successful, false otherwise.
     */

    public boolean gymOwnerLogin(String userName, String password) {
        if (gymOwnerService.loginGymOwner(userName,password)) {
            System.out.println(GREEN_COLOR+"Successfully logged in"+RESET_COLOR);
            gymOwnerClientMainPage(userName);
        } else {
            new LoginFailedException(RED_COLOR+"Gym Owner Login Failed"+RESET_COLOR);
            return false;
        }
        return true;
    }
    /**
     * Registers a new gym owner by taking input for username, password, email, PAN number, and card number.
     * Validates input for email, PAN, and card number before registering the gym owner.
     */

    public void register() {
        Validators validate = new Validators();
        String email = "";
        String panNumber = "";
        String cardNumber = "";
        System.out.println("Enter your UserName");
        String userName = scanner.next();

        System.out.println("Enter your Password");
        String password = scanner.next();

        System.out.println("Re enter your password for confirmation");
        String confirmPassword = scanner.next();

        while(!password.equals(confirmPassword)){
            System.out.println(RED_COLOR + "Passwords doesn't match, please make sure the password matches in both the fields" + RESET_COLOR);
            System.out.println("Enter your Password");
            password = scanner.next();
            System.out.println("Re enter your password for confirmation");
            confirmPassword = scanner.next();
        }

        while(true){
            System.out.println("Enter your Email");
            email = scanner.next();
            if (!validate.isEmailValid(email)){
                System.out.println(RED_COLOR+"Please enter a valid email"+RESET_COLOR);
            }else break;
        }

        while(true){
            System.out.println("Enter your PAN Number");
            panNumber = scanner.next();
            if (!validate.isPanValid(panNumber)){
                System.out.println(RED_COLOR+"Please enter a valid PAN Number"+RESET_COLOR);
            }else break;
        }


        while(true){
            System.out.println("Enter your Card Number");
            cardNumber = scanner.next();
            if (!validate.isCardValid(cardNumber)){
                System.out.println(RED_COLOR+"Please enter a valid Card Number"+RESET_COLOR);
            }else break;
        }

        gymOwnerService.registerGymOwner(util.generateNewId(),userName,password,email,panNumber,cardNumber);
//        gymOwnerClientMainPage(userName);
//        mainPage();
    }
    /**
     * Registers a gym owner with the provided details.
     * This method is used for manual registration and bypasses user input validation.
     *
     * @param userid The user ID of the gym owner.
     * @param userName The username of the gym owner.
     * @param password The password of the gym owner.
     * @param email The email of the gym owner.
     * @param panNumber The PAN number of the gym owner.
     * @param cardNumber The card number of the gym owner.
     */
    public void registerGymOwnerManually(String userid, String userName, String password, String email, String panNumber, String cardNumber){
        gymOwnerService.registerGymOwner(userid,userName,password,email,panNumber,cardNumber);
    }
    /**
     * Navigates to the main page for gym owners, providing options to perform various actions.
     * Actions include viewing and editing profiles, managing gym centres, and handling slots.
     *
     * @param gymOwnerId The user ID of the logged-in gym owner.
     */
    public void gymOwnerClientMainPage(String gymOwnerId) {
//        LocalDateTime currentTime = LocalDateTime.now();
        LocalDate currentDate = LocalDate.now();
        LocalTime currentTime = LocalTime.now();
        DateTimeFormatter myFormat = DateTimeFormatter.ofPattern("HH:mm:ss");
        String formattedTime = currentTime.format(myFormat);
        Validators validate = new Validators();
        System.out.println(YELLOW_COLOR+"WELCOME "+gymOwnerId+" AS GYMOWNER!!\nWhat you what to do\nLogin TIME: "+currentDate + " " + currentDate.getDayOfWeek() + " " + formattedTime+RESET_COLOR);

        boolean isContinue = true;
        int choice  = 1;

        while(isContinue){
            System.out.println(CYAN_COLOR+"Enter your choice (1, 2, 3, 4, 5, 6, 7, 8, 9 ): \n"+RESET_COLOR);
            System.out.println(BLUE_COLOR+"" +
                    "0: View Profile\n" +
                    "1: Edit Profile\n" +
                    "2. View all Gym Centres\n" +
                    "3. Sending Gym Owner Approval Request\n" +
                    "4. Add a new Gym Center\n" +
                    "5. Send a Gym Centre Approval Request to Admin\n" +
                    "6. Add Slots to a Gym Centre\n" +
                    "7: View All Slots by Centre\n" +
                    "8: Delete Slot\n" +
                    "9: Exit"+RESET_COLOR
            );

            GymOwner g = gymOwnerService.viewGymOwnerProfile(gymOwnerId);
            String curId = g.getUserID();
            if(g.getIsApproved()==0){
                System.out.println(RED_COLOR+"You are currently not approved. Please press 3 to send a approval request."+RESET_COLOR);
            }

            if(g.getIsApproved()==2){
                System.out.println(YELLOW_COLOR+"Your approval request is under process."+RESET_COLOR);
            }
            try{
                choice = scanner.nextInt();
                switch (choice) {
                    /* Take input from user for all service parameters ( Make the menu ) */
                    case 0:
                        GymOwner owner = gymOwnerService.viewGymOwnerProfile(gymOwnerId);
                        if (owner.getUserID().equals("none")) System.out.println("User not found");
                        System.out.println("Profile: \nUsername: " + owner.getUserName() + "\nPAN Card: " + owner.getPanNumber() + "\nEmail: " + owner.getEmail());
                        break;
                    case 1:
                        //edit profile
                        editGymOwnerProfile(gymOwnerId);
                        return ;
                    case 2:
                        List<GymCentre> allGymCentres = gymCentreService.getAllCentresByOwmerId(curId);
                        util.printGymCentres(allGymCentres);
                        break;
                    case 3:
                        gymOwnerService.requestGymOwnerApproval(gymOwnerId);
                        break;

                    case 4:

                        if(gymOwnerService.viewGymOwnerProfile(gymOwnerId).getIsApproved()==0){
                            System.out.println(RED_COLOR + "Please get yourself approved first!!!" + RESET_COLOR);
                            break;
                        }

                        if(gymOwnerService.viewGymOwnerProfile(gymOwnerId).getIsApproved()==2){
                            System.out.println(YELLOW_COLOR + "Please wait for admin to approve you!" + RESET_COLOR);
                            break;
                        }

                        System.out.println("Enter gym centre id: ");
                        String gymId = scanner.next();

                        System.out.println("Enter Gym Centre name: ");
                        String gymCentreName = scanner.next();


                        boolean isValidGst = false;
                        String gstin = "";
                        while(!isValidGst){
                            System.out.println("Enter Gym Centre GSTIN: ");
                            gstin = scanner.next();
                            if(!validate.isGstValid(gstin)){
                                System.out.println(RED_COLOR+"Please enter valid GST Number"+RESET_COLOR);
                            }
                            else isValidGst = true;
                        }


                        String city = "Bangalore";
                        boolean isValidGymCentre = false;
                        while(!isValidGymCentre){

                            System.out.println(ORANGE_COLOR+"Enter Gym Centre Location:\n1. North Bangalore\n2. South Bangalore\n3. West Bangalore \n4. East Bangalore \n"+RESET_COLOR);
                            System.out.println(CYAN_COLOR+"Enter your choice (1, 2, 3, 4 ): \n"+RESET_COLOR);
                            isValidGymCentre = true;
                            int gymLocationChoice = 1;
                            try
                            {
                                gymLocationChoice = scanner.nextInt();
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
                                    default:
                                        isValidGymCentre = false;
                                        System.out.println(INVALID_CHOICE_ERROR);
                                        break;
                                }
                            }
                            catch (InputMismatchException e) {
                                isValidGymCentre = false;
                                System.out.println(RED_COLOR+"Invalid input, please enter a valid numerical value."+RESET_COLOR);
                                scanner.nextLine(); // Clear the buffer
                            }


                        }

                        boolean isValidCapacity = false;
                        int capacity = 0;
                        while(!isValidCapacity){
                            System.out.println("Enter Gym Centre capacity: ");
                            try{
                                capacity = scanner.nextInt();
                                isValidCapacity = true;
                            }
                            catch (InputMismatchException e) {
                                System.out.println(RED_COLOR+"Invalid input, please enter a valid numerical value."+RESET_COLOR);
                                scanner.nextLine(); // Clear the buffer
                            }
                        }
                        boolean isValidPrice = false;
                        int price = 0;
                        while(!isValidPrice){
                            System.out.println("Enter price: ");
                            try{
                                price = scanner.nextInt();
                                isValidPrice = true;
                            }
                            catch (InputMismatchException e) {
                                System.out.println(RED_COLOR+"Invalid input, please enter a valid numerical value."+RESET_COLOR);
                                scanner.nextLine(); // Clear the buffer
                            }
                        }



                        gymCentreService.addCenter(
                                new GymCentre(
                                        gymId,
                                        curId,
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

                        if(gymOwnerService.viewGymOwnerProfile(gymOwnerId).getIsApproved()==0){
                            System.out.println(RED_COLOR + "Please get yourself approved first!!!" + RESET_COLOR);
                            break;
                        }

                        if(gymOwnerService.viewGymOwnerProfile(gymOwnerId).getIsApproved()==2){
                            System.out.println(YELLOW_COLOR + "Please wait for admin to approve you!" + RESET_COLOR);
                            break;
                        }

                        boolean isAdding = true;
                        String centreId = null;

                        List<Slot> newSlotList = new ArrayList<>();
                        while (isAdding) {
                            System.out.println("Enter new slot ID: ");
                            String slotId = scanner.next();

                            System.out.println("Enter Gym Centre ID: ");
                            centreId = scanner.next();

                            if(gymCentreService.getGymCentreById(centreId).getIsApproved()==0){
                                System.out.println(RED_COLOR + "Please get your center approved first!!!" + RESET_COLOR);
                                isAdding=false;
                                break;
                            }

                            if(gymCentreService.getGymCentreById(centreId).getIsApproved()==2){
                                System.out.println(YELLOW_COLOR + "Please wait for admin to approve your gym!" + RESET_COLOR);
                                isAdding=false;
                                break;
                            }

                            System.out.println("Enter time in 24h format (hh:mm:ss) : ");
                            String time = scanner.next();

                            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
                            LocalTime localTime = LocalTime.parse(time, formatter);

                            newSlotList.add(new Slot(
                                    slotId,
                                    centreId,
                                    localTime
                            ));

                            System.out.println(YELLOW_COLOR+"Do you want to enter more slots (yes/no)?: "+RESET_COLOR);
                            String addChoice = scanner.next();
                            addChoice = addChoice.toLowerCase();

                            if (addChoice.equals("n") || addChoice.equals("no")) {
                                isAdding = false;
                            }
                        }

                        if(centreId!=null)slotService.addSlotsForGym(centreId, newSlotList);
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
                        if (flag) System.out.println(GREEN_COLOR+"Successfully Deleted"+RESET_COLOR);
                        else System.out.println(RED_COLOR+"Slot not found"+RESET_COLOR);

                        break;

                    case 9:
                        System.out.println(YELLOW_COLOR+"Do you wish to continue? (yes/no)"+RESET_COLOR);
                        String ans = scanner.next();
                        if(ans.equals("n") || ans.equals("no")) isContinue = false;
                        break;

                    default:
                        System.out.println(INVALID_CHOICE_ERROR);
                        break;
                }
            }
            catch (InputMismatchException e) {
                System.out.println(RED_COLOR+"Invalid input, please enter a valid numerical value."+RESET_COLOR);
                scanner.nextLine(); // Clear the buffer
            }






        }
    }
    /**
     * Edits the profile of the logged-in gym owner, allowing changes to username, email, or card details.
     *
     * @param gymOwnerId The user ID of the gym owner whose profile is being edited.
     */
    public void editGymOwnerProfile(String gymOwnerId){
        Validators validate = new Validators();

        System.out.println(YELLOW_COLOR+"WELCOME TO EDIT PROFILE");
        System.out.println(YELLOW_COLOR+"Select what you want to edit");
        int choice = 1;
        boolean validChoice = false;
        while(!validChoice){
            System.out.println(ORANGE_COLOR+"1. Edit user name\n2. Edit email\n3. Edit card details\n4. Go Back"+RESET_COLOR);
            System.out.println(CYAN_COLOR+"Enter your choice (1, 2, 3, 4 ): \n"+RESET_COLOR);
            try{
                choice = scanner.nextInt();
                GymOwner gymOwner = gymOwnerService.viewGymOwnerProfile(gymOwnerId);
                boolean status = false;
                if(choice>=1 && choice<=4)validChoice = true;
                switch(choice){
                    case 1:
                        System.out.println("Enter new user name: ");
                        String name = scanner.next();
                        status = gymOwnerService.editProfile(gymOwner.getUserID(), name, gymOwner.getEmail(), gymOwner.getCardDetails());
                        break;
                    case 2:
                        String email = "";
                        while(true){
                            System.out.println("Enter your Email");
                            email = scanner.next();
                            if (!validate.isEmailValid(email)){
                                System.out.println("Please enter a valid email");
                            }else break;
                        }
                        status = gymOwnerService.editProfile(gymOwner.getUserID(), gymOwner.getUserName(), email, gymOwner.getCardDetails());
                        break;
                    case 3:
                        String cardNumber = "";
                        while(true){
                            System.out.println("Enter your Card Number");
                            cardNumber = scanner.next();
                            if (!validate.isCardValid(cardNumber)){
                                System.out.println("Please enter a valid Card Number");
                            }else break;
                        }
                        status = gymOwnerService.editProfile(gymOwner.getUserID(), gymOwner.getUserName(), gymOwner.getEmail(), cardNumber);
                        break;
                    case 4:
                        gymOwnerClientMainPage(gymOwner.getUserName());
                        return;
                    default:
                        System.out.println(INVALID_CHOICE_ERROR);
                        continue;

                }

                if(status){
                    System.out.println(GREEN_COLOR+"Successfully edited gym owner details");
                }else{
                    System.out.println(RED_COLOR+"Couldn't edit customer details");
                }
                editGymOwnerProfile(gymOwner.getUserName());
            }
            catch (InputMismatchException e) {
                System.out.println(RED_COLOR+"Invalid input, please enter a valid numerical value."+RESET_COLOR);
                scanner.nextLine(); // Clear the buffer
            }

        }



    }
    /**
     * Validates the provided credentials by checking the gym owner's login status.
     *
     * @param userName The username of the gym owner.
     * @param password The password of the gym owner.
     * @return True if the credentials are valid, false otherwise.
     */
    public boolean validateCredentials(String userName,String password){
        if (gymOwnerService.loginGymOwner(userName, password)) return true;
        else return false;
    }
    /**
     * Updates the password for the gym owner with the provided user ID.
     *
     * @param customerId The user ID of the gym owner.
     * @param newPassword The new password to be set.
     */
    public void updatePassword(String customerId,String newPassword){
        gymOwnerService.updatePassword(customerId,newPassword);
    }

}
