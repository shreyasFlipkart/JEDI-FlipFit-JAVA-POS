package com.flipkart.client;
import com.flipkart.bean.Role;

import java.util.*;


public class MainApplicationClient {

    public static void main(String args[]){
        mainPage();
    }
    private static void mainPage()
    {
        System.out.println("<------------ Welcome to the FlipFit Application ------------>");
        System.out.println("Enter the Choice");
        System.out.println("1. Login\n2. Customer Registration\n3. Gym Owner Registration \n4. Update Password\n5. Exit");
        Scanner scanner=new Scanner(System.in);
        int choice = scanner.nextInt();
        switch (choice) {
            case 1:
                login();
                break;
            case 2:
                registration(2);
                break;
            case 3:
                registration(3);
            case 4:
                updatePassword();
                break;
            case 5:
                System.out.println("EXIT");
                return;
            default:
                System.out.println("INVALID CHOICE");
                break;
        }

    }
    private static void login(){
        Scanner scanner=new Scanner(System.in);
        AdminClient adminClient = new AdminClient();
        GymOwnerClient gymOwnerClient = new GymOwnerClient();
        CustomerClient customerClient = new CustomerClient();
        try {
            System.out.println("Enter your Role (1. ADMIN/2. GYMOWNER/3. CUSTOMER) : ");
            int role = scanner.nextInt();

            System.out.println("Enter your UserName : ");
            String userName = scanner.next();

            System.out.println("Enter your Password :");
            String password = scanner.next();

            switch (role){
                case 1:
                    adminClient.adminLogin(userName,password);
                    break;
                case 2:
                    gymOwnerClient.gymOwnerLogin(userName,password);
                    break;
                case 3:
                    customerClient.customerLogin(userName,password);
                    break;
                default:
                    System.out.println("INVALID CHOICE");
                    break;
            }
        }catch (IllegalArgumentException e){
            System.out.println("INVALID CHOICE");
        }
    }
    private static void registration(int role){
        GymOwnerClient gymOwnerClient = new GymOwnerClient();
        CustomerClient customerClient = new CustomerClient();
        try {

            switch (role){
                case 2:
                    customerClient.register();
                    break;
                case 3:
                    gymOwnerClient.register();
                    break;
                default:
                    System.out.println("INVALID CHOICE");
                    break;
            }
        }catch (IllegalArgumentException e){
            System.out.println("INVALID CHOICE");
        }
    }
    private static void updatePassword(){
        Scanner scanner=new Scanner(System.in);
        System.out.println("Enter your updated Password :");
        String updatedPassword = scanner.next();

        System.out.println("Password updated!");

    }
}