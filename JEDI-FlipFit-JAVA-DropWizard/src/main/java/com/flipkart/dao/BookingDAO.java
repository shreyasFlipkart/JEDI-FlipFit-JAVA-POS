package com.flipkart.dao;

import com.flipkart.bean.Booking;
import com.flipkart.bean.UserPlan;
import com.flipkart.exceptions.BookingFailedException;
import com.flipkart.utils.DBUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.flipkart.constants.Constants.RED_COLOR;
import static com.flipkart.constants.Constants.RESET_COLOR;
import static com.flipkart.constants.SQLConstants.*;

public class BookingDAO implements BookingInterfaceDAO {

    private static ScheduleDAO scheduleDAO = new ScheduleDAO();

    public String addBooking(String userID, String scheduleID) throws BookingFailedException {
        try {
            // Assuming you have a method to generate a unique booking ID
            String bookingId = generateUniqueBookingId(userID, scheduleID);

            // Add the booking to the database
            try (Connection conn = DBUtils.connect();
                 PreparedStatement stmt = conn.prepareStatement(ADD_BOOKING)) {
                stmt.setString(1, bookingId);
                stmt.setString(2, userID);
                stmt.setString(3, scheduleID);
                stmt.executeUpdate();
            }

            return bookingId;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new BookingFailedException(RED_COLOR+"Booking failed for current slot. Try again later."+RESET_COLOR);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new BookingFailedException(RED_COLOR+"Booking failed for current slot. Try again later."+RESET_COLOR);
        }
    }

    public List<Booking> getBookingByCustomerId(String customerId) throws BookingFailedException {
        List<Booking> customerBookings = new ArrayList<>();
        try (Connection conn = DBUtils.connect();
             PreparedStatement stmt = conn.prepareStatement(GET_BOOKING_BY_CUSTOMER_ID)) {
            stmt.setString(1, customerId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                String bookingId = rs.getString("bookingId");
                String scheduleId = rs.getString("scheduleID");
                Date date =null;
                try {
                    date = new SimpleDateFormat("yyyy-mm-dd").parse(rs.getString("date"));
                }catch(Exception e){
                    System.out.println(e.getMessage());
                }
                LocalTime time = LocalTime.parse(rs.getString("time"));
                customerBookings.add(new Booking(bookingId, customerId, scheduleId, date, time));
            }
        } catch (SQLException e) {
            throw new BookingFailedException(RED_COLOR+"Failed to retrieve customer bookings."+RESET_COLOR);
        }
        return customerBookings;
    }

    public List<UserPlan> getCustomerPlan(String customerId) {
        // Add logic to retrieve UserPlan based on the database
        return new ArrayList<>();
    }

    public boolean checkBookingOverlap(String customerId, Date date, LocalTime localTime) {
        // Add logic to check booking overlap based on the database
        return false;
    }

    public void cancelBookingById(String bookingID) throws BookingFailedException {
        try (Connection conn = DBUtils.connect();
             PreparedStatement stmt = conn.prepareStatement(CANCEL_BOOKING_BY_ID)) {
            stmt.setString(1, bookingID);
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected == 0) {
                throw new BookingFailedException(RED_COLOR+"Could not cancel booking for BookingId: "+RESET_COLOR + bookingID);
            }
        } catch (SQLException e) {
            throw new BookingFailedException(RED_COLOR+"Failed to cancel booking for BookingId: "+RESET_COLOR + bookingID);
        }
    }

    public Booking getBookingByBookingId(String bookingId) throws BookingFailedException {
        try (Connection conn = DBUtils.connect();
             PreparedStatement stmt = conn.prepareStatement(GET_BOOKING_BY_BOOKING_ID)) {
            stmt.setString(1, bookingId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                String customerId = rs.getString("userID");
                String scheduleId = rs.getString("scheduleID");
                return new Booking(bookingId, customerId, scheduleId);
            } else {
                throw new BookingFailedException(RED_COLOR+"Could not fetch booking by bookingId: "+RESET_COLOR + bookingId);
            }
        } catch (SQLException e) {
            throw new BookingFailedException(RED_COLOR+"Failed to fetch booking by bookingId: " +RESET_COLOR+ bookingId);
        }
    }

    private String generateUniqueBookingId(String userID, String scheduleID) {
        return userID + scheduleID;
    }
}
