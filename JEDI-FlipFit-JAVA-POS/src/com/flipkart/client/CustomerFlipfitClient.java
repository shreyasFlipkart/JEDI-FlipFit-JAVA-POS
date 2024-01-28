package com.flipkart.client;

import com.flipkart.bean.Booking;
import com.flipkart.bean.Customer;
import com.flipkart.bean.GymCentre;
import com.flipkart.bean.Slot;
import com.flipkart.business.CustomerFlipfitImplService;
import com.flipkart.business.CustomerFlipfitServiceInterface;
import com.flipkart.exceptions.DataEntryException;
import com.flipkart.utils.UserPlan;
import com.flipkart.utils.util;
import com.flipkart.validator.Validators;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.sql.Date;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static com.flipkart.client.FlipfitApplication.mainPage;
import static com.flipkart.client.FlipfitApplication.scanner;
import static com.flipkart.constants.Constants.*;
import static com.flipkart.constants.Constants.RESET_COLOR;


public class CustomerFlipfitClient {
    private CustomerFlipfitServiceInterface customerService  =  CustomerFlipfitImplService.getInstance();

    public boolean customerLogin(String userName, String password) {
//        Check if credentials are right
        if (customerService.isUserValid(userName, password)) {
            System.out.println(GREEN_COLOR+"Successfully logged in"+RESET_COLOR);
            customerClientMainPage(userName);
        } else {
            System.out.println(RED_COLOR+"UserName or password doesn't match"+RESET_COLOR);
            return false;
        }
        return true;
    }


    public void register(){
        Validators validate = new Validators();
        System.out.println("Enter your UserName");
        String userName = scanner.next();
        userName = userName.toUpperCase();


        System.out.println("Enter your Password");
        String password = scanner.next();



        boolean isValidEmail = false;
        String email ="";
        while(!isValidEmail){
            System.out.println("Enter your Email");
            email = scanner.next();
            if (!validate.isEmailValid(email)){
                System.out.println("Please enter a valid email");
            }
            else isValidEmail = true;
        }
        boolean isValidPhone = false;
        String phoneNumber = "";
        while(!isValidPhone){
            System.out.println("Enter your Phone Number");
            phoneNumber = scanner.next();
            if(!validate.isPhoneValid(phoneNumber)){
                System.out.println("Please enter a valid phone number");
            }
            else isValidPhone = true;

        }
        boolean isValidCard = false;
        String cardNumber = "";
        while(!isValidCard){
            System.out.println("Enter your Card Number");
            cardNumber = scanner.next();
            if(!validate.isCardValid(cardNumber)){
                System.out.println("Please enter a valid card number");
            }
            else isValidCard = true;
        }

        customerService.registerCustomer(userName,password,email,phoneNumber,cardNumber);
//        customerClientMainPage(userName);
        //mainPage();
    }
    public void registerCustomerManually(String userName,String password, String email, String phoneNumber,String cardNumber ){
        customerService.registerCustomer(userName,password,email,phoneNumber,cardNumber);

    }

    private void printSlots(List<Slot> slots){
        System.out.println(DASHED_LINE);
        System.out.printf(YELLOW_COLOR + "%-8s\t", "SLOT-ID");
        System.out.printf("%-8s\t\n", "SLOT-TIME" + RESET_COLOR);
        System.out.println(DASHED_LINE);
        for(Slot slot: slots) {
            System.out.printf("%-8s\t", slot.getSlotId());
            System.out.printf("%-8s\t\n", slot.getTime());
        }
        System.out.println(DASHED_LINE);
    }

    private void bookSlotSubMenu(String userName){
        //Get Location for filter
        System.out.println("Provide Location to search :");
        System.out.println("Enter your choice (1, 2, 3, 4): \n");
        System.out.println("Choose the location: \n1. North Bangalore\n2. South Bangalore\n3. West Bangalore \n4. East Bangalore \n");
        int choice = 1;
        try{
            choice = scanner.nextInt();
            String location = null;
            switch (choice){
                case 1:
                    location = "North Bangalore";
                    break;
                case 2:
                    location = "South Bangalore";
                    break;
                case 3:
                    location = "West Bangalore";
                    break;
                case 4:
                    location = "East Bangalore";
                    break;
                default:
                    System.out.println("Invalid input, please enter a valid numerical value.");
                    bookSlotSubMenu(userName);
                    return ;


            }
            List<GymCentre> centreListByLocation = customerService.getAllGymCenterDetailsByCity(location);
            // Print All Centres
            util.printGymCentres(centreListByLocation);
            //Select Gym Centre
            if(centreListByLocation.isEmpty()){
                System.out.println(RED_COLOR +"There are no available GYM Centres in " + location + ". Please Select some other location" + RESET_COLOR);
                customerClientMainPage(userName);
                return;
            }
            System.out.print("Choose a gymCentre ID to proceed:");
            String chosenGym = scanner.next();
            //Select Date
            Date sqlDate = selectDate();
            //Choose Slot
            boolean result = chooseSlot(chosenGym,userName,sqlDate,chosenGym);
            if(result){
                System.out.println(GREEN_COLOR + "Booking Successful\n" + RESET_COLOR);
            }
        }catch (Exception e) {
            System.out.println("Invalid input, please enter a numerical value.");
            scanner.nextLine();
            bookSlotSubMenu(userName);
            // Clear the buffer
        }


    }

    private Date selectDate(){
        //Select Date
        System.out.print("Enter Date (dd/MM/yyyy): ");
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        java.util.Date date;
        Date sqlDate = null;
        try {
            String dat = scanner.next();
            date = sdf.parse(dat);
            sqlDate = new Date(date.getTime());
        } catch (ParseException e) {
            throw new DataEntryException();
        }
        return sqlDate;
    }

    private boolean chooseSlot(String gymCentreId,String userName,Date sqlDate,String centreId){
        while(true){
            System.out.println("Choose from the Below Slots");
            List<Slot> availableSlots = customerService.getAvailableSlots(gymCentreId,sqlDate);
            printSlots(availableSlots);
            if(availableSlots.isEmpty()){
                System.out.println(RED_COLOR +"There are no available slots in the " + gymCentreId + ". Please Select some other gym" + RESET_COLOR);
                customerClientMainPage(userName);
                return false;
            }
            System.out.println("Press 0 to exit");
            System.out.println("Enter SlotID :");
            String slotID = scanner.next();
            if(slotID.equals("0"))return false;
            //Select Slot to book
            if(customerService.bookSlot(userName,sqlDate,slotID,centreId))return true;
        }
    }

    private void printUserPlan(String userName){
        System.out.println("Bookings : ");
        List<UserPlan> allUserPlan= customerService.getCustomerPlan(userName);
        List<Booking> bookingList = customerService.getCustomerBookings(userName);
        System.out.println(DASHED_LINE);
        System.out.printf(YELLOW_COLOR + "%-8s\t", "Centre-ID");
        System.out.printf(YELLOW_COLOR + "%-8s\t", "SLOT-ID");
        System.out.printf(YELLOW_COLOR + "%-8s\t", "DATE");
        System.out.printf(YELLOW_COLOR + "%8s\t", "SLOT-TIME");
        System.out.printf("%-8s\t\n", "SCHEDULE_ID" + RESET_COLOR);
        System.out.println(DASHED_LINE);
        for(UserPlan userPlan: allUserPlan) {
            System.out.printf("%-8s\t", userPlan.getCentreID());
            System.out.printf("%-8s\t", userPlan.getSlotId());
            System.out.printf("%-8s\t", userPlan.getDate());
            System.out.printf("%-8s\t", userPlan.getTime());
            System.out.printf("%-8s\t\n", userPlan.getScheduleID());
        }
        System.out.println(DASHED_LINE);
    }

    private void printbookingsSubMenu(String userName){
        System.out.println("Bookings : ");
        List<Booking> allBookingList= customerService.getCustomerBookings(userName);
        String[][] table = new String[allBookingList.size()+1][4];
//        String[][] table_dup = new String[allBookingList.size()+1][2];
        String[] cols = { "BOOKING-ID", "SCHEDULE-ID" , "DATE", "TIME"};
        for(int i =0;i<4;i++){
            table[0][i] = cols[i];
//            table_dup[0][i] = cols[i];
        }
        for(int i=1;i< allBookingList.size()+1;i++){
            table[i][0] = allBookingList.get(i - 1).getBookingID();
            table[i][1] = allBookingList.get(i - 1).getScheduleID();
            table[i][2] = new SimpleDateFormat("yyyy-mm-dd").format(allBookingList.get(i-1).getDate());
            table[i][3] = allBookingList.get(i-1).getTime().toString();

        }
//        System.out.println(DASHED_LINE);
//        System.out.printf(YELLOW_COLOR + "%-8s\t", "BOOKING-ID");
//        System.out.printf("%47s\t\n", "SCHEDULE-ID" + RESET_COLOR);
//        System.out.println(DASHED_LINE);
//        for(Booking booking: allBookingList) {
//            System.out.printf("%-8s\t", booking.getBookingID());
//            System.out.printf("%-8s\t\n", booking.getScheduleID());
//        }
//        System.out.println(DASHED_LINE);

        util.tableWithLines(table, table);
    }

    private void cancelBookingSubMenu(String userName){
        printbookingsSubMenu(userName);
        System.out.println("Select the Booking you want to cancel: ");
        String bookingId = scanner.next();
        customerService.cancelBookingbyID(bookingId);
        System.out.println(GREEN_COLOR + "Booking Cancellation Successful\n" + RESET_COLOR);
    }

    public void printCustomerProfile(Customer customer){
        System.out.println(GREEN_COLOR +"------------------------------------------------------------------------" + RESET_COLOR);
        System.out.println(YELLOW_COLOR + "USER ID: "+ RESET_COLOR + customer.getUserID());
        System.out.println(YELLOW_COLOR + "USER NAME: "+ RESET_COLOR + customer.getUserName());
        System.out.println(YELLOW_COLOR + "EMAIL: "+ RESET_COLOR + customer.getEmail());
        System.out.println(YELLOW_COLOR + "CONTACT: "+ RESET_COLOR + customer.getCustomerPhone());
        System.out.println(YELLOW_COLOR + "CARD DETAILS: "+ RESET_COLOR + customer.getCardDetails());
        System.out.println(GREEN_COLOR +"------------------------------------------------------------------------" + RESET_COLOR);
    }
    public void updatePassword(String customerId,String newPassword){
        customerService.updatePassword(customerId,newPassword);
    }

    public void editCustomerProfile(Customer customer){
        Validators validate = new Validators();
        System.out.println(YELLOW_COLOR+"WELCOME TO EDIT PROFILE");
        System.out.println(YELLOW_COLOR+"Select what you want to edit\n");
        System.out.println("Enter your choice (1, 2, 3, 4, 5 ): \n");
        System.out.println("1. Edit user name\n2. Edit email\n3. Edit contact\n4. Edit card details\n5. Go Back");
        int choice = 1;
        try{
            choice = scanner.nextInt();
            boolean status = false;
            switch(choice){
                case 1:
                    System.out.println("Enter new user name: ");
                    String name = scanner.next();
                    customer.setUserName(name);
                    status = customerService.editProfile(customer.getUserID(), name, customer.getEmail(), customer.getCustomerPhone(), customer.getCardDetails());
                    break;
                case 2:
                    boolean isValidEmail = false;
                    String email ="";
                    while(!isValidEmail){
                        System.out.println("Enter your Email");
                        email = scanner.next();
                        if (!validate.isEmailValid(email)){
                            System.out.println("Please enter a valid email");
                        }
                        else isValidEmail = true;
                    }
                    customer.setEmail(email);
                    status = customerService.editProfile(customer.getUserID(), customer.getUserName(), email, customer.getCustomerPhone(), customer.getCardDetails());
                    break;
                case 3:
                    boolean isValidPhone = false;
                    String phoneNumber = "";
                    while(!isValidPhone){
                        System.out.println("Enter your Phone Number");
                        phoneNumber = scanner.next();
                        if(!validate.isPhoneValid(phoneNumber)){
                            System.out.println("Please enter a valid phone number");
                        }
                        else isValidPhone = true;

                    }
                    customer.setCustomerPhone(phoneNumber);
                    status = customerService.editProfile(customer.getUserID(), customer.getUserName(), customer.getEmail(), phoneNumber, customer.getCardDetails());
                    break;
                case 4:
                    boolean isValidCard = false;
                    String cardNumber = "";
                    while(!isValidCard){
                        System.out.println("Enter your Card Number");
                        cardNumber = scanner.next();
                        if(!validate.isCardValid(cardNumber)){
                            System.out.println("Please enter a valid card number");
                        }
                        else isValidCard = true;
                    }
                    customer.setCardDetails(cardNumber);
                    status = customerService.editProfile(customer.getUserID(),  customer.getUserName(), customer.getEmail(), customer.getCustomerPhone(), cardNumber);
                    break;
                case 5:
                    customerClientMainPage(customer.getUserName());
                    return;
                default:
                    System.out.println(INVALID_CHOICE_ERROR);
                    editCustomerProfile(customer);
                    return ;
            }
            if(status){
                System.out.println(GREEN_COLOR+"Successfully edited customer details");
            }else{
                System.out.println(RED_COLOR+"Couldn't edit customer details");
            }
            editCustomerProfile(customer);
        }catch (Exception e) {
            System.out.println("Invalid input, please enter a valid numerical value.");
            scanner.nextLine(); // Clear the buffer
            editCustomerProfile(customer);
        }

    }


    public void customerClientMainPage(String userName) {
        LocalDateTime currentTime = LocalDateTime.now();
        DateTimeFormatter myFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String formattedDate = currentTime.format(myFormat);
        System.out.println(YELLOW_COLOR+"WELCOME "+userName+" !!\nWhat do you want to do\nLogin TIME: "+currentTime+RESET_COLOR);
        int choice = 1;


        while(true){
            System.out.println("Enter your choice (1, 2, 3, 4, 5, 6, 7 ): \n");
            System.out.println("1. View My Profile \n2. Edit My Profile \n3. View gyms by cities \n4. Book a slot in a Gym \n5. View Booked Slots\n6. Cancel Bookings\n7. Go Back to previous menu");

            try{
                choice = scanner.nextInt();
                switch(choice){
                    case 1:
                        Customer customer= customerService.viewMyProfile(userName);
                        printCustomerProfile(customer);
                        break;
                    case 2:
                        Customer cust= customerService.viewMyProfile(userName);
                        editCustomerProfile(cust);
                        return;
                    case 3:
                        displayCentersSortedByCities();
                        break;
                    case 4:
                        bookSlotSubMenu(userName);
                        break;
                    case 5:
                        printbookingsSubMenu(userName);
                        //printUserPlan(userName);
                        break;
                    case 6:
                        cancelBookingSubMenu(userName);
                        break;
                    case 7:
                        System.out.println(PREVIOUS_MENU_MESSAGE);
                        return;
                    default:
                        System.out.println(INVALID_CHOICE_ERROR);
                        break;
                }
            }
            catch (Exception e) {
                System.out.println("Invalid input, please enter a valid numerical value.");
                scanner.nextLine(); // Clear the buffer
            }


        }
    }

    public boolean validateCredentials(String userName,String password){
        if (customerService.isUserValid(userName, password)) return true;
        else return false;
    }

    public void displayCentersSortedByCities(){
        List<GymCentre> sortedByCities = customerService.getCentersSortedByCities();
        util.printGymCentres(sortedByCities);
    }

}