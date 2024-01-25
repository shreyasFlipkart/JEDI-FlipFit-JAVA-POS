package com.flipkart.client;

import com.flipkart.bean.Admin;
import com.flipkart.bean.GymCentre;
import com.flipkart.bean.GymOwner;
import com.flipkart.business.AdminFlipfitService;
import com.flipkart.business.AdminFlipfitServiceInterface;
import com.flipkart.business.GymOwnerFlipfitService;
import com.flipkart.business.GymOwnerFlipfitServiceInterface;
import com.flipkart.business.GymCentreFlipfitServiceInterface;
import com.flipkart.business.GymCentreFlipfitService;
import com.flipkart.utils.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static com.flipkart.client.FlipfitClient.scanner;
import static com.flipkart.constants.Constants.*;

public class AdminFlipfitClient {

    private static Admin admin = new Admin();
    private static AdminFlipfitServiceInterface adminService = new AdminFlipfitService();
    private static GymOwnerFlipfitServiceInterface gymOwnerService = new GymOwnerFlipfitService();
    private static GymCentreFlipfitServiceInterface gymCenterService = new GymCentreFlipfitService();

    public boolean isUserValid(String userName, String password) {
        if (userName.equals(admin.getUserName()) && password.equals(admin.getPassword())) {
            return true;
        }
        return false;
    }

    public boolean adminLogin(String userName, String password) {
        if (isUserValid(userName, password)) {
            System.out.println("Successfully logged in");
            adminClientMenu();
        }
        else{
            System.out.println("ADMIN login failed");
            return false;
        }
        return true;
    }

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
    private void handleGymCenterApprovalRequests(){
        // print the list with indexes from 1
        System.out.println("Press 0 to EXIT_MESSAGE or Choose the Gym Centre To Modify:");
        String requestGymCenterId = scanner.next();
        if (requestGymCenterId.equals("0")) return;
//            Now Admin will select an request and we will pop up with two
        System.out.println("1. Approve the request\n2. Reject the request\n");
        int choice = scanner.nextInt();
        if(choice == 1){
            System.out.println(APPROVAL_GYM_CENTRE_CONFIRMATION);
        } else if (choice == 2) {
            System.out.println(DISAPPROVAL_GYM_CENTRE_CONFIRMATION);
        }
        adminService.approveGymCenter(requestGymCenterId,choice);
    }

    public void adminClientMenu(){
        LocalDateTime currentTime = LocalDateTime.now();
        DateTimeFormatter myFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        System.out.println(YELLOW_COLOR+"WELCOME ADMIN!!\nLogin Time: "+currentTime+RESET_COLOR);
        while(true){
            System.out.println("1. View All Gym Owners\n2. View All Gym Centers\n3. View Pending GymOwner Approval Requests\n4. View Pending GymCenter's Approval Requests\n5. Exit");
            System.out.print("\nEnter your choice: ");
            int pendingChoice = scanner.nextInt();
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
            }
        }
    }

}
