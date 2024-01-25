package com.flipkart.business;

import com.flipkart.dao.CustomerDAO;
import com.flipkart.dao.CustomerInterfaceDAO;
import com.flipkart.bean.Booking;
import com.flipkart.bean.Customer;
import com.flipkart.bean.GymCentre;
import com.flipkart.bean.Slot;
import com.flipkart.exceptions.RegistrationFailedException;
import com.flipkart.exceptions.UserInvalidException;
import com.flipkart.utils.UserPlan;

import java.sql.Date;
import java.util.List;


import static com.flipkart.constants.Constants.*;

public class CustomerFlipfitService implements CustomerFlipfitServiceInterface {
    private static CustomerFlipfitService instance;
    public static CustomerFlipfitService getInstance() {
        if (instance == null) {
            instance = new CustomerFlipfitService();
        }
        return instance;
    }
    private CustomerInterfaceDAO customerDAO = CustomerDAO.getInstance();
    private GymCentreFlipfitServiceInterface gymCentreService = new GymCentreFlipfitService();
    private BookingFlipfitServiceInterface bookingService = new BookingFlipfitService();
    private ScheduleFlipfitServiceInterface scheduleService = new ScheduleFlipfitService();

    private SlotFlipfitServiceInterface slotService = new SlotFlipfitService();

    public List<GymCentre> getAllGymCenterDetailsByCity(String city){
        //takes City (Location) as input and returns List<GymCenter>
        return gymCentreService.getCentresByCity(city);
    }

    public List<Slot> getAvailableSlots(String centreID, Date date){
        //takes centerID and date for input and returns List<Slot>
        return gymCentreService.getAvailableSlotsByCentreAndDate(centreID,date);
    }

    public List<Booking> getCustomerBookings(String customerId){
        //takes userId and returns List<Bookings>
        return bookingService.getBookingByCustomerId(customerId);
    }

    public List<UserPlan> getCustomerPlan(String customerId){
        return bookingService.getCustomerPlan(customerId);
    }

    public boolean bookSlot(String userName,Date date, String slotId,String centreId){
        if(!slotService.isSlotValid(slotId,centreId)){
            System.out.println(INVALID_SLOT);
            return false;
        }
        String scheduleId = scheduleService.getOrCreateSchedule(slotId,date).getScheduleID();
        //create booking
        boolean isOverlap = bookingService.checkBookingOverlap(userName,date,slotId);
        if(isOverlap) {
            System.out.println(RED_COLOR + "There exists a conflicting booking, First cancel it!!!!" + RESET_COLOR);
            return false;
        }
        bookingService.addBooking(userName, scheduleId);
        return true;
    }



    public void cancelBookingbyID(String bookingID){
        //cancel a booking
        bookingService.cancelBooking(bookingID);
    }

    public void registerCustomer(String userName, String password, String email, String phoneNumber, String cardNumber) {
        try {
            customerDAO.registerCustomer(userName,password,email,phoneNumber,cardNumber);
        } catch (RegistrationFailedException e) {
            e.getMessage();
        }

    }

    public Customer viewMyProfile(String userName) {
        return customerDAO.getCustomerById(userName);
    }

    public boolean editProfile(String customerId, String username, String email, String phoneNumber, String cardNumber){
        return customerDAO.editCustomer(customerId, username, email, phoneNumber, cardNumber);
    }
    public boolean updatePassword(String customerId, String newPassword){

        return customerDAO.updatePassword(customerId,newPassword);
    }

    public boolean isUserValid(String userName, String password) {
        try {
            return customerDAO.isUserValid(userName,password);
        } catch (UserInvalidException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    public List<GymCentre> getCentersSortedByCities(){
        return gymCentreService.getCentersSortedByCity();
    }

}