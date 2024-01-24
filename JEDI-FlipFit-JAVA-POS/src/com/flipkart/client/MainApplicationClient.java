package com.flipkart.client;
import com.flipkart.bean.Role;


public class MainApplicationClient {
    public static void main(String[] args)
    {
        System.out.println("<------------ Welcome to the FlipFit Application ------------>");
        System.out.println("Enter the Choice");
        System.out.println("1. Login\n2. Registration\n3. Update Password\n4. Exit");
        int choice = scanner.nextInt();
        switch (choice) {
            case 1:
                login();
                break;
            case 2:
                registration();
                break;
            case 3:
                updatePassword();
                break;
            case 4:
                System.out.println(EXIT_MESSAGE);
                return;
            default:
                System.out.println(INVALID_CHOICE_ERROR);
                break;
        }

    }
    private static void login(){
        try {
            System.out.println("Enter your Role (ADMIN/GYMOWNER/CUSTOMER) : ");
            Role role = Role.valueOf(scanner.next().toUpperCase());

            System.out.println("Enter your UserName : ");
            String userName = scanner.next();

            System.out.println("Enter your Passkey :");
            String password = scanner.next();

            switch (role){
                case ADMIN:
                    adminClient.adminLogin(userName,password);
                    break;
                case GYMOWNER:
                    gymOwnerClient.gymOwnerLogin(userName,password);
                    break;
                case CUSTOMER:
                    customerClient.customerLogin(userName,password);
                    break;
                default:
                    System.out.println(INVALID_CHOICE_ERROR);
                    break;
            }
        }catch (IllegalArgumentException e){
            System.out.println(INVALID_CHOICE_ERROR);
        }
    }
    private static void registration(){
        try {
            System.out.println("Enter your role (ADMIN/GYMOWNER/CUSTOMER) : ");
            Role role = Role.valueOf(scanner.next().toUpperCase());

            switch (role){
                case ADMIN:
                    System.out.println("Admin is already registered");
                    mainPage();
                    break;
                case CUSTOMER:
                    customerClient.register();
                    break;
                case GYMOWNER:
                    gymOwnerClient.register();
                    break;
                default:
                    System.out.println(INVALID_CHOICE_ERROR);
                    break;
            }
        }catch (IllegalArgumentException e){
            System.out.println(INVALID_CHOICE_ERROR);
        }
    }
    private static updatePassword(){

    }
}