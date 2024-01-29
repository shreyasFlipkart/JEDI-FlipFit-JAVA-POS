package com.flipkart.client;
import com.flipkart.bean.Role;


import java.util.InputMismatchException;
import java.util.Scanner;

import static com.flipkart.constants.Constants.*;

public class FlipfitApplication {

    public static int userId = 0;
    public static Scanner scanner = new Scanner(System.in);
    private static AdminFlipfitClient adminFlipfitClient = new AdminFlipfitClient();
    private static CustomerFlipfitClient customerFlipfitClient = new CustomerFlipfitClient();
    private static GymOwnerFlipfitClient gymOwnerFlipfitClient = new GymOwnerFlipfitClient();




    public static void mainPage(){

        System.out.println(BOLD_TEXT+CYAN_COLOR+"Enter your choice (1, 2, 3, 4, 5, 6): \n"+RESET_COLOR);
        System.out.println(BLUE_COLOR+"1. Login\n2. Customer Registration\n3. Gym Owner Registration \n4. Update Password for Customer\n5. Update Password fot Gym Owner\n6. Exit"+RESET_COLOR);
        int choice = 1;
        try {
            choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    login();
                    break;
                case 2:
                    registration(2);
                    break;
                case 3:
                    registration(3);
                    break;
                case 4:
                    updatePassword(2);
                    break;
                case 5:
                    updatePassword(3);
                    break;
                case 6:
                    System.out.println(EXIT_MESSAGE);
                    return;

                default:
                    System.out.println(INVALID_CHOICE_ERROR);
                    break;
            }



        } catch (InputMismatchException e) {
            System.out.println(RED_COLOR+"Invalid input, please enter a numerical value."+RESET_COLOR);
            scanner.nextLine(); // Clear the buffer
        }


        mainPage();
    }

    private static void login(){
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.println(CYAN_COLOR+"Enter your choice (1, 2, 3): \n"+RESET_COLOR);
            System.out.println(MAGENTA_COLOR+"Enter your Role \n1. ADMIN\n2. GYMOWNER\n3. CUSTOMER \n "+RESET_COLOR);
//            String curRole = scanner.next();
//            Role roleEnum = Role.valueOf(curRole.toUpperCase());
            int role = 1;
            try {
                role = scanner.nextInt();
                String userName="";String password = "";
                if(role>=1 &&  role<=3){
                    System.out.println("Enter your UserName : ");
                    userName = scanner.next();
                    System.out.println("Enter your Password :");
                    password = scanner.next();
                }


                switch (role){
                    case 1:
                        adminFlipfitClient.adminLogin(userName,password);
                        break;
                    case 2:
                        gymOwnerFlipfitClient.gymOwnerLogin(userName,password);
                        break;
                    case 3:
                        customerFlipfitClient.customerLogin(userName,password);
                        break;
                    default:
                        login();
                        System.out.println(INVALID_CHOICE_ERROR);
                        break;
                }
            }catch (IllegalArgumentException e){
                System.out.println(INVALID_CHOICE_ERROR);
            }
            }
            catch (InputMismatchException e) {
                login();
                System.out.println(RED_COLOR+"Invalid input, please enter a numerical value."+RESET_COLOR);
                scanner.nextLine(); // Clear the buffer
            }



    }

    private static void registration(int role){

        try {
            switch (role){
                case 2:
                    customerFlipfitClient.register();
                    break;
                case 3:
                    gymOwnerFlipfitClient.register();
                    break;
                default:
                    System.out.println(RED_COLOR+"INVALID CHOICE"+RESET_COLOR);
                    break;
            }
        }catch (IllegalArgumentException e){
            System.out.println(RED_COLOR+"INVALID CHOICE"+RESET_COLOR);
        }

    }
    private static void updatePassword(int role){

        Scanner scanner=new Scanner(System.in);
        switch (role){
            case 2://customerFlipfitClient
                System.out.println("Enter the UserName of the Customer: ");
                String customerUserId = scanner.next();
                System.out.println("Enter the Password of the Customer: ");
                String customerPassword = scanner.next();
                if (customerFlipfitClient.validateCredentials(customerUserId,customerPassword)) {
                    System.out.println("Enter the new Password: ");
                    String newPassword = scanner.next();
                    customerFlipfitClient.updatePassword(customerUserId,newPassword);

                } else {
                    System.out.println(RED_COLOR+"UserName or password doesn't match"+RESET_COLOR+'\n');
                    System.out.println(EXIT_MESSAGE);
                    return;
                }
                break;
            case 3:
                System.out.println("Enter the User Id of the Gym Owner: ");
                String gymOwnerUserId = scanner.next();
                System.out.println("Enter the Password of the Gym Owner: ");
                String gymOwnerPassword = scanner.next();
                if(gymOwnerFlipfitClient.validateCredentials(gymOwnerUserId, gymOwnerPassword)){
                    System.out.println("Enter the new password: ");
                    String newPassword = scanner.next();
                    gymOwnerFlipfitClient.updatePassword(gymOwnerUserId, newPassword);
                } else {
                    System.out.println(RED_COLOR + "UserName or password doesn't match" + RESET_COLOR + '\n');
                    System.out.println(EXIT_MESSAGE);
                    return;
                }
                break;
            default:
                System.out.println(RED_COLOR+"INVALID CHOICE"+RESET_COLOR);
                break;
        }


        System.out.println( GREEN_COLOR + "Password updated!\n" + RESET_COLOR);

    }

    public static void main(String[] args) {
        System.out.println(WELCOME_MESSAGE);
        mainPage();
    }
}