package com.flipkart.business;

import com.flipkart.bean.Booking;
import com.flipkart.bean.Customer;
import com.flipkart.bean.GymCentre;
import com.flipkart.bean.Slot;

import java.util.Date;
import java.util.List;

public class CustomerService implements CustomerServiceInterface {



    public List<GymCentre> getAllGymCenterDetailsByCity(String city){
        return null;
    }

    @Override
    public List<Slot> getAvailableSlots(String centreID, java.sql.Date date) {
        return null;
    }

    public List<Slot> getAvailableSlots(String centreID, Date date){
    return null;
    }

    public List<Booking> getCustomerBookings(String customerId){
        return null;
    }

    @Override
    public boolean bookSlot(String userID, java.sql.Date date, String slotId, String centreId) {
        return false;
    }

//    public List<UserPlan> getCustomerPlan(String customerId){
//    }

    public boolean bookSlot(String userName, Date date, String slotId, String centreId){
        return true;
    }


    public void cancelBookingbyID(String bookingID){

    }

    public void registerCustomer(String userName, String password, String email, String phoneNumber, String cardNumber) {


    }

    public Customer viewMyProfile(String userName) {
        return null;
    }

    public boolean isUserValid(String userName, String password) {
        return true;
    }


}