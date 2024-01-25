package com.flipkart.dao;

import com.flipkart.bean.Booking;

import java.util.List;

public interface BookingInterfaceDAO {
    void  addBooking(String userName, String scheduleID);
    List<Booking> getBookingByCustomerId(String customerId);
    void cancelBookingById(String bookingID);
}