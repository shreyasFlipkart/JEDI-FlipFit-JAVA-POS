package com.flipkart.client;
import com.flipkart.business.CustomerService;
import com.flipkart.business.CustomerServiceInterface;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
            System.out.println("Customer MENU");
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
        System.out.println("Customer MENU");
    }


}