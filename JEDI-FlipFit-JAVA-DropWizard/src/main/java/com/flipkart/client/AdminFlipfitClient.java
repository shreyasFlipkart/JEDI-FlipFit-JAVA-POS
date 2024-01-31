package com.flipkart.client;

import com.flipkart.bean.Admin;
import com.flipkart.bean.GymCentre;
import com.flipkart.bean.GymOwner;
import com.flipkart.business.*;
import com.flipkart.utils.util;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.InputMismatchException;
import java.util.List;

import static com.flipkart.client.FlipfitApplication.scanner;
import static com.flipkart.constants.Constants.*;

public class AdminFlipfitClient {

    private static Admin admin = new Admin();
    private static AdminFlipfitServiceInterface adminService = new AdminFlipfitImplService();
    private static GymOwnerFlipfitServiceInterface gymOwnerService = new GymOwnerFlipfitImplService();
    private static GymCentreFlipfitServiceInterface gymCenterService = new GymCentreFlipfitImplService();

    /**
     *  Method to verify admin login details
     * @param userName username of the admin
     * @param password password of the admin
     * @return boolean
     */
    public boolean isUserValid(String userName, String password) {
        if (userName.equals(admin.getUserName()) && password.equals(admin.getPassword())) {
            return true;
        }
        return false;
    }

    /**
     * Method to authenticate admin using userName and password
     * @param userName
     * @param password
     * @return boolean
     */
    public boolean adminLogin(String userName, String password) {
        if (isUserValid(userName, password)) {
            System.out.println(Admin_login_success);
            adminClientMenu();
        }
        else{
            System.out.println(Admin_login_failed);
            return false;
        }
        return true;
    }

    /**
     * Method to approve or reject approval request from the gym owners
     */
    private void handleGymOwnerApprovalRequests(){
        // print the list with indexes from 1
        System.out.println("Admin Approval for a Gym Owner ----------");

        System.out.println("(Press 0 to exit)\nEnter the Id of Gym Owner:");
        String requestGymOwnerId = scanner.next();

        if(requestGymOwnerId.equals("0")) {return;}

        System.out.println("1. Approve the request\n2. Reject the request");
        int choice = scanner.nextInt();
        if(choice == 1){
            System.out.println(APPROVAL_GYM_OWNER_CONFIRMATION);
        } else if (choice == 2) {
            System.out.println(DISAPPROVAL_GYM_OWNER_CONFIRMATION);
        }

        adminService.approveGymOwner(requestGymOwnerId,choice);
    }

    /**
     * Method to approve or reject approval request of gym centers from the gym owners
     */
    private void handleGymCenterApprovalRequests(){
        // print the list with indexes from 1
        System.out.println("Press 0 to EXIT_MESSAGE or Choose the Id of the Gym Centre To Modify:");
        String requestGymCenterId = scanner.next();
        if (requestGymCenterId.equals("0")) return;
//            Now Admin will select a request and we will pop up with two
        boolean correctChoice = false;
        int choice = 1;
        while(!correctChoice){
            System.out.println(CYAN_COLOR+"Enter your choice (1, 2): \n"+RESET_COLOR);
            System.out.println("1. Approve the request\n2. Reject the request\n");
            try{
                choice = scanner.nextInt();
                if(choice == 1){
                    correctChoice = true;
                    System.out.println(APPROVAL_GYM_CENTRE_CONFIRMATION);
                    //also gym should be added in the respective gym owners table
                } else if (choice == 2) {
                    correctChoice = true;
                    System.out.println(DISAPPROVAL_GYM_CENTRE_CONFIRMATION);
                }
                else {
                    System.out.println(INVALID_CHOICE_ERROR);
                }
            }
            catch (Exception e) {
                System.out.println(RED_COLOR+"Invalid input, please enter a valid numerical value."+RESET_COLOR);
                scanner.nextLine(); // Clear the buffer
            }

        }

        adminService.approveGymCenter(requestGymCenterId,choice);
    }

    /**
     * Method to display the menu details of the admin
     */
    public void adminClientMenu(){
//        LocalDateTime currentTime = LocalDateTime.now();
        LocalDate currentDate = LocalDate.now();
        LocalTime currentTime = LocalTime.now();
        DateTimeFormatter myFormat = DateTimeFormatter.ofPattern("HH:mm:ss");
        String formattedTime = currentTime.format(myFormat);
        System.out.println(YELLOW_COLOR+"WELCOME ADMIN!!\nLogin Time: "+currentDate+ " " + currentDate.getDayOfWeek() + " " + formattedTime+RESET_COLOR);
        int pendingChoice = 1;
        while(true){
            System.out.println(CYAN_COLOR+"\nEnter your choice (1, 2, 3, 4, 5 ): \n"+RESET_COLOR);
            System.out.println(BLUE_COLOR+"1. View All Gym Owners\n2. View All Gym Centers\n3. View Pending GymOwner Approval Requests\n4. View Pending GymCenter's Approval Requests\n5. Exit"+RESET_COLOR);
            try{
                pendingChoice = scanner.nextInt();
                switch (pendingChoice) {
                    case 1:
                        List<GymOwner> allGymOwners =  gymOwnerService.viewAllGymOwners();
                        util.printOwnerList(allGymOwners);
                        break;
                    case 2:
                        List<GymCentre> allGymCentre = gymCenterService.viewAllGymCentres();
                        util.printGymCentreList(allGymCentre);
                        break;

                    case 3:
                        List<GymOwner> pendingGymOwners = adminService.viewPendingGymOwners();
                        util.printOwnerList(pendingGymOwners);
                        if(!pendingGymOwners.isEmpty()) handleGymOwnerApprovalRequests();
                        break;

                    case 4:
                        List<GymCentre> pendingGymCentres = adminService.viewPendingGymCentres();//get listGymCenterIds
                        util.printGymCentres(pendingGymCentres);
                        if(!pendingGymCentres.isEmpty()) handleGymCenterApprovalRequests();
                        break;
                    case 5:
                        return;
                    default:
                        System.out.println(INVALID_CHOICE_ERROR);
                        break;
                }
            } catch (InputMismatchException e) {
                System.out.println(RED_COLOR+"Invalid input, please enter a valid numerical value."+RESET_COLOR);
                scanner.nextLine(); // Clear the buffer
            }

        }
    }

}
