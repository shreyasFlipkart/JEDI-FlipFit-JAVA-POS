package com.flipkart.business;

import com.flipkart.dao.BookingDAO;
import com.flipkart.bean.Booking;
import com.flipkart.bean.Slot;
import com.flipkart.exceptions.BookingFailedException;
import com.flipkart.utils.UserPlan;

import java.util.Date;
import java.util.List;


public class BookingFlipfitImplService implements BookingFlipfitServiceInterface {

    private final BookingDAO bookingDAO = new BookingDAO();
    private final ScheduleFlipfitImplService scheduleService  = new ScheduleFlipfitImplService();

    private final SlotFlipfitImplService slotService = new SlotFlipfitImplService();

    public boolean checkBookingOverlap(String customerId, Date date, String slotId){
        //return whether the customer has already booked a slot at same timing
        Slot slot = slotService.getSlotByID(slotId);
        return bookingDAO.checkBookingOverlap(customerId,date,slot.getTime());
    }
    public String addBooking(String userID, String scheduleID) {
        try {
            boolean isAvailable = scheduleService.modifySchedule(scheduleID,-1);
            if(!isAvailable){
                System.out.println("No seats available for the booking");
                return "";
            }
            return bookingDAO.addBooking(userID, scheduleID);
        } catch (BookingFailedException e) {
            System.out.println(e.getMessage());
        }
        return "";
    }

    public List<Booking> getBookingByCustomerId(String customerId){
        try {
            return bookingDAO.getBookingByCustomerId(customerId);
        } catch (BookingFailedException e) {
            System.out.println(e.getMessage());
        }
        return null;

    }

    public List<UserPlan> getCustomerPlan(String customerId){
        return bookingDAO.getCustomerPlan(customerId);
    }

    public void cancelBooking(String bookingID) {
        try {
            Booking booking  = bookingDAO.getBookingByBookingId(bookingID);
            bookingDAO.cancelBookingById(bookingID);
            scheduleService.modifySchedule(booking.getScheduleID(),1);
        } catch (BookingFailedException e) {
            System.out.println(e.getMessage());
        }

    }
}