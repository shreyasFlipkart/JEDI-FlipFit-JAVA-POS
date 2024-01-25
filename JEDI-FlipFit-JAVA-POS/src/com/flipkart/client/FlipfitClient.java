package com.flipkart.client;
import com.flipkart.bean.Role;



import java.util.Scanner;

import static com.flipkart.constants.Constants.*;

public class FlipfitClient {

    public static int userId = 0;
    public static Scanner scanner = new Scanner(System.in);
    private static AdminFlipfitClient adminFlipfitClient = new AdminFlipfitClient();
    private static CustomerFlipfitClient customerFlipfitClient = new CustomerFlipfitClient();
    private static GymOwnerFlipfitClient gymOwnerFlipfitClient = new GymOwnerFlipfitClient();




    private static void mainPage(){
        System.out.println("Enter the Choice\n");
        System.out.println("1. Login\n2. Customer Registration\n3. Gym Owner Registration \n4. Update Password for Customer\n5. Update Password fot Gym Owner\n6. Exit");
        int choice = scanner.nextInt();
        //customerFlipfitClient.registerCustomerManually("Shreyas","Ketkar","1","2","3");
        //gymOwnerFlipfitClient.registerGymOwnerManually("123","riya","1234","e@gmail.com","1234","12345123");
        //gymOwnerFlipfitClient.registerGymOwnerManually("456","name","3455","f@gmail.com","1235","15345123");

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
        mainPage();
    }

    private static void login(){
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.println("Enter your Role (ADMIN/GYMOWNER/CUSTOMER) : ");
            String curRole = scanner.next();
            Role roleEnum = Role.valueOf(curRole.toUpperCase());

            int role = roleEnum.ordinal()+1;


            System.out.println("Enter your UserName : ");
            String userName = scanner.next();

            System.out.println("Enter your Password :");
            String password = scanner.next();

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
                    System.out.println(INVALID_CHOICE_ERROR);
                    break;
            }
        }catch (IllegalArgumentException e){
            System.out.println(INVALID_CHOICE_ERROR);
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
                    System.out.println("INVALID CHOICE");
                    break;
            }
        }catch (IllegalArgumentException e){
            System.out.println("INVALID CHOICE");
        }

    }
    private static void updatePassword(int role){

        Scanner scanner=new Scanner(System.in);
        switch (role){
            case 2://customerFlipfitClient
                System.out.println("Enter the UserName of the Customer\n");
                String customerUserId = scanner.next();
                System.out.println("Enter the Password of the Customer\n");
                String customerPassword = scanner.next();
                if (customerFlipfitClient.validateCredentials(customerUserId,customerPassword)) {
                    System.out.println("Enter the new Password\n");
                    String newPassword = scanner.next();
                    customerFlipfitClient.updatePassword(customerUserId,newPassword);

                } else {
                    System.out.println(RED_COLOR+"UserName or password doesn't match"+RESET_COLOR+'\n');
                    System.out.println(EXIT_MESSAGE);
                    return;
                }
                break;
            case 3:
                System.out.println("Enter the User Id of the Gym Owner\n");
                String gymOwnerUserId = scanner.next();
                System.out.println("Enter the Password of the Gym Owner\n");
                String gymOwnerPassword = scanner.next();
                break;
            default:
                System.out.println("INVALID CHOICE");
                break;
        }


        System.out.println("Password updated!");

    }

    public static void main(String[] args) {
        System.out.println(WELCOME_MESSAGE);
        mainPage();
    }
}