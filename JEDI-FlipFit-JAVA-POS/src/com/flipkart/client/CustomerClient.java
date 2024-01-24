package com.flipkart.client;
import com.flipkart.business.CustomerService;
import com.flipkart.business.CustomerServiceInterface;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;


public class CustomerClient {
    private CustomerServiceInterface customerService  =  new CustomerService();

    public boolean customerLogin(String userName, String password) {
//        Check if credentials are right
        if (customerService.isUserValid(userName, password)) {
            LocalDateTime currentTime = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
            String formattedDateTime = currentTime.format(formatter);

            System.out.println("Successfully logged in at " + formattedDateTime);
            customerClientMainPage();
        } else {
            System.out.println("UserName or password doesn't match");
            return false;
        }
        return true;
    }


    public void register(){
        Scanner scanner=new Scanner(System.in);
        System.out.println("Enter your UserName");
        String userName = scanner.next();

        System.out.println("Enter your Passkey");
        String password = scanner.next();

        System.out.println("Enter your Email");
        String email = scanner.next();

        System.out.println("Enter your Phone Number (10 Digits)");
        String phoneNumber = scanner.next();

        System.out.println("Enter your Card Number (12 Digits)");
        String cardNumber = scanner.next();

        customerService.registerCustomer(userName,password,email,phoneNumber,cardNumber);
        customerClientMainPage();
    }

    public void customerClientMainPage() {
        Scanner scanner = new Scanner(System.in);
        LocalDateTime currentTime = LocalDateTime.now();
        DateTimeFormatter myFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String formattedDate = currentTime.format(myFormat);
        System.out.println("\nWELCOME " + " Customer" + "\nWhat do you want to do");
        while (true) {
            System.out.println("1. View My Profile \n2. View all centers \n3. View Bookings\n4. Cancel Bookings\n5. Edit Profile \n6. View Available slots and Book");
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    System.out.println("Customer Profile");
                    break;
                case 2:
                    System.out.println("Enter your location: (1. Bangalore)");
                    int city = scanner.nextInt();
                    switch(city){
                        case 1:
                            System.out.println("Enter your locality: \n 1. North Bangalore \n 2. East Bangalore\n 3. West Bangalore \n 4. South Bangalore \n");
                            int subcity = scanner.nextInt();
                            switch(subcity){
                                case 1:
                                    System.out.println("FlipFit, North Bangalore");
                                    break;
                                case 2:
                                    System.out.println("FlipFit, East Bangalore");
                                    break;
                                case 3:
                                    System.out.println("FlipFit, West Bangalore");
                                    break;
                                case 4:
                                    System.out.println("FlipFit, South Bangalore");
                                    break;
                                default:
                                    System.out.println("INVALID CHOICE");
                            }
                            break;
                        default:
                            System.out.println("Gyms not available in this location");
                    }
                    break;
                case 3:
                    System.out.println("View Customer Bookings");
                    //printUserPlan(userName);
                    break;
                case 4:
                    System.out.println("Cancel Booking");
                    break;
                case 5:
                    System.out.println("Edit Profile");
                    break;
                case 6:
                    System.out.println("Enter your location: (1. Bangalore)");
                    int cityCenter = scanner.nextInt();
                    switch(cityCenter){
                        case 1:
                            System.out.println("Enter your locality: \n 1. North Bangalore \n 2. East Bangalore\n 3. West Bangalore \n 4. South Bangalore \n");
                            int subcity = scanner.nextInt();
                            int selectedGym;
                            switch(subcity){
                                case 1:
                                    System.out.println("Enter the gym you want -\n 1. FlipFit, North Bangalore\n");
                                    selectedGym = scanner.nextInt();
                                    switch(selectedGym){
                                        case 1:
                                            System.out.println("Choose a slot\n");
                                            System.out.println("1. SLOT 1 - 6:00 AM TO 7:00 AM\n");
                                            System.out.println("2. SLOT 2 - 8:00 AM TO 9:00 AM\n");
                                            System.out.println("3. SLOT 3 - 10:00 AM TO 11:00 AM\n");
                                            System.out.println("4. SLOT 4 - 2:00 PM TO 3:00 PM\n");
                                            int slot = scanner.nextInt();
                                            switch(slot){
                                                case 1:
                                                    System.out.println("25 available slots");
                                                    System.out.println("1. Book the slot");
                                                    System.out.println("Slot booked!");
                                                    break;
                                                case 2:
                                                    System.out.println("2 available slots");
                                                    System.out.println("1. Book the slot");
                                                    System.out.println("Slot booked!");
                                                    break;
                                                case 3:
                                                    System.out.println("21 available slots");
                                                    System.out.println("1. Book the slot");
                                                    System.out.println("Slot booked!");
                                                    break;
                                                case 4:
                                                    System.out.println("12 available slots");
                                                    System.out.println("1. Book the slot");
                                                    System.out.println("Slot booked!");
                                                    break;
                                                default:
                                                    System.out.println("Not available");
                                            }

                                            break;
                                        default:
                                            System.out.println("not available");
                                    }
                                case 2:
                                    System.out.println("Enter the gym you want -\n 1. FlipFit, East Bangalore\n");
                                    selectedGym = scanner.nextInt();
                                    switch(selectedGym){
                                        case 1:
                                            System.out.println("Choose a slot\n");
                                            System.out.println("1. SLOT 1 - 6:00 AM TO 7:00 AM\n");
                                            System.out.println("2. SLOT 2 - 8:00 AM TO 9:00 AM\n");
                                            System.out.println("3. SLOT 3 - 10:00 AM TO 11:00 AM\n");
                                            System.out.println("4. SLOT 4 - 2:00 PM TO 3:00 PM\n");
                                            int slot = scanner.nextInt();
                                            switch(slot){
                                                case 1:
                                                    System.out.println("25 available slots");
                                                    System.out.println("1. Book the slot");
                                                    System.out.println("Slot booked!");
                                                    break;
                                                case 2:
                                                    System.out.println("2 available slots");
                                                    System.out.println("1. Book the slot");
                                                    System.out.println("Slot booked!");
                                                    break;
                                                case 3:
                                                    System.out.println("21 available slots");
                                                    System.out.println("1. Book the slot");
                                                    System.out.println("Slot booked!");
                                                    break;
                                                case 4:
                                                    System.out.println("12 available slots");
                                                    System.out.println("1. Book the slot");
                                                    System.out.println("Slot booked!");
                                                    break;
                                                default:
                                                    System.out.println("Not available");
                                            }
                                            break;
                                        default:
                                            System.out.println("not available");
                                    }
                                case 3:
                                    System.out.println("Enter the gym you want -\n 1. FlipFit, West Bangalore\n");
                                    selectedGym = scanner.nextInt();
                                    switch(selectedGym){
                                        case 1:
                                            System.out.println("Choose a slot\n");
                                            System.out.println("1. SLOT 1 - 6:00 AM TO 7:00 AM\n");
                                            System.out.println("2. SLOT 2 - 8:00 AM TO 9:00 AM\n");
                                            System.out.println("3. SLOT 3 - 10:00 AM TO 11:00 AM\n");
                                            System.out.println("4. SLOT 4 - 2:00 PM TO 3:00 PM\n");
                                            int slot = scanner.nextInt();
                                            int book;
                                            switch(slot){
                                                case 1:
                                                    System.out.println("25 available slots");
                                                    System.out.println("1. Book the slot");
                                                    System.out.println("Slot booked!");
                                                    break;
                                                case 2:
                                                    System.out.println("2 available slots");
                                                    System.out.println("1. Book the slot");
                                                    book = scanner.nextInt();
                                                    System.out.println("Slot booked!");
                                                    break;
                                                case 3:
                                                    System.out.println("21 available slots");
                                                    System.out.println("1. Book the slot");
                                                    System.out.println("Slot booked!");
                                                    break;
                                                case 4:
                                                    System.out.println("12 available slots");
                                                    System.out.println("1. Book the slot");
                                                    System.out.println("Slot booked!");
                                                    break;
                                                default:
                                                    System.out.println("Not available");
                                            }
                                            break;
                                        default:
                                            System.out.println("not available");
                                    }
                                case 4:
                                    System.out.println("Enter the gym you want -\n 1. FlipFit, South Bangalore\n");
                                    selectedGym = scanner.nextInt();
                                    switch(selectedGym){
                                        case 1:
                                            System.out.println("Choose a slot\n");
                                            System.out.println("1. SLOT 1 - 6:00 AM TO 7:00 AM\n");
                                            System.out.println("2. SLOT 2 - 8:00 AM TO 9:00 AM\n");
                                            System.out.println("3. SLOT 3 - 10:00 AM TO 11:00 AM\n");
                                            System.out.println("4. SLOT 4 - 2:00 PM TO 3:00 PM\n");
                                            int slot = scanner.nextInt();
                                            switch(slot){
                                                case 1:
                                                    System.out.println("25 available slots");
                                                    System.out.println("1. Book the slot");
                                                    System.out.println("Slot booked!");
                                                    break;
                                                case 2:
                                                    System.out.println("2 available slots");
                                                    System.out.println("1. Book the slot");
                                                    System.out.println("Slot booked!");
                                                    break;
                                                case 3:
                                                    System.out.println("21 available slots");
                                                    System.out.println("1. Book the slot");
                                                    System.out.println("Slot booked!");
                                                    break;
                                                case 4:
                                                    System.out.println("12 available slots");
                                                    System.out.println("1. Book the slot");
                                                    System.out.println("Slot booked!");
                                                    break;
                                                default:
                                                    System.out.println("Not available");
                                            }
                                            break;
                                        default:
                                            System.out.println("not available");
                                    }
                                default:
                                    System.out.println("INVALID CHOICE");
                            }
                        default:
                            System.out.println("Gyms not available in this location");
                    }
                    break;
                default:
                    System.out.println("INVALID CHOICE");
                    break;
            }
        }
    }


}