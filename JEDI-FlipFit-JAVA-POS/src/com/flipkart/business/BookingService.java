package com.flipkart.business;

import com.flipkart.bean.Booking;

import java.util.Date;
import java.util.List;

public class BookingService implements BookingServiceInterface {



    public boolean checkBookingOverlap(String customerId, Date date, String slotId){
        return true;
    }



    public void addBooking(String userName, String scheduleID) {

    }

    @Override
    public List<Booking> getBookingByCustomerId(String customerId) {
        return null;
    }

//    public List<Booking> getBookingByCustomerId(String customerId){
//        return {};
//    }

//    public List<UserPlan> getCustomerPlan(String customerId){
//    }

    public void cancelBooking(String bookingID) {


    }
}