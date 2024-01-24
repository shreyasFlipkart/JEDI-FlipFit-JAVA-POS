package com.flipkart.client;

import com.flipkart.bean.Admin;
import com.flipkart.business.AdminService;
import com.flipkart.business.AdminServiceInterface;
import com.flipkart.business.GymOwnerService;
import com.flipkart.business.GymOwnerServiceInterface;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


import java.util.Scanner;


public class AdminClient {

    private static Admin admin = new Admin();
    private static AdminServiceInterface adminService = new AdminService();
    private static GymOwnerServiceInterface gymOwnerService = new GymOwnerService();
    Scanner scanner = new Scanner(System.in);

    public boolean isUserValid(String userName, String password) {
        if (userName.equals(admin.getUserName()) && password.equals(admin.getPassword())) {
            return true;
        }
        return false;
    }

    public boolean adminLogin(String userName, String password) {
        System.out.println("Successfully logged in");
        adminClientMenu();
        return true;
    }

    public void adminClientMenu(){
        LocalDateTime currentTime = LocalDateTime.now();
        DateTimeFormatter myFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String formattedDate = currentTime.format(myFormat);
        System.out.println("WELCOME ADMIN!!\nLogin Time: "+currentTime);

        while(true){
            System.out.println("1. View All Gym Owners\n2. View All Gym Centers\n3. GymOwner Approval Requests\n4. GymCenter Approval Requests\n5.EXIT");
            System.out.println("Enter the Choice:");
            int pendingChoice = scanner.nextInt();
            switch (pendingChoice) {
                case 1:
//                    List<GymOwner> allGymOwners =  gymOwnerService.viewAllGymOwners();
                    System.out.println("Fetching Gym owners list...");
                    System.out.println("Owner1 \nOwner2 \nOwner3\nOwner4");
                    break;
                case 2:
                    System.out.println("Fetching Gym centers list...");
                    System.out.println("Request from FlipFit, North Bangalore\nRequest from FlipFit, East Bangalore \nRequest from FlipFit, West Bangalore\nRequest from FlipFit, South Bangalore");
                    break;
                case 3:
//                    List<GymOwner> pendingGymOwners = adminService.viewPendingGymOwners();
                    System.out.println("1. Request from owner1 \n2. Request from owner2 \n3. Request from owner3\n4. Request from owner4");
                    System.out.println("Approve request from owners: \n1.Yes \n2.No");
                    System.out.println("Enter your choice:");
                    int choice = scanner.nextInt();
                    switch (choice){
                        case 1:
                            System.out.println("Enter the id of the gym owner to whose request is required approval");
                            int ownerId = scanner.nextInt();
                            System.out.println("Approved request of the owner:"+ownerId);
                        case 2:
                            break;
                        default:
                            System.out.println("Invalid Choice");
                            break;
                    }
                    break;

                case 4:
//                    List<GymCentre> pendingGymCentres = adminService.viewPendingGymCentres(); //get listGymCenterIds
                    System.out.println("Request from FlipFit, North Bangalore\nRequest from FlipFit, East Bangalore \nRequest from FlipFit, West Bangalore\nRequest from FlipFit, South Bangalore");
                    System.out.println("Approve request from center: \n1.Yes \n2.No");
                    System.out.println("Enter your choice:");
                    int ch = scanner.nextInt();
                    switch (ch){
                        case 1:
                            System.out.println("Enter the id of the gym owner to whose request is required approval");
                            int ownerId = scanner.nextInt();
                            System.out.println("Approved request of the owner:"+ownerId);
                        case 2:
                            break;
                        default:
                            System.out.println("Invalid Choice");
                            break;
                    }
                    break;
                default:
                    System.out.println("Exiting....");
                    return;
            }
        }
    }
}
