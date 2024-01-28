package com.flipkart.dao;

import com.flipkart.bean.Booking;
import com.flipkart.exceptions.BookingFailedException;
import com.flipkart.utils.UserPlan;
import com.flipkart.utils.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import static com.flipkart.constants.SQLConstants.*;

public class BookingDAO implements BookingInterfaceDAO {

    private static ScheduleDAO scheduleDAO = new ScheduleDAO();

    public String addBooking(String userID, String scheduleID) throws BookingFailedException {
        try {
            // Assuming you have a method to generate a unique booking ID
            String bookingId = generateUniqueBookingId(userID, scheduleID);

            // Add the booking to the database
            try (Connection conn = DBConnection.connect();
                 PreparedStatement stmt = conn.prepareStatement(ADD_BOOKING)) {
                stmt.setString(1, bookingId);
                stmt.setString(2, userID);
                stmt.setString(3, scheduleID);
                stmt.executeUpdate();
            }

            return bookingId;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new BookingFailedException("Booking failed for current slot. Try again later.");
        } catch (Exception e) {
            e.printStackTrace();
            throw new BookingFailedException("Booking failed for current slot. Try again later.");
        }
    }

    public List<Booking> getBookingByCustomerId(String customerId) throws BookingFailedException {
        List<Booking> customerBookings = new ArrayList<>();
        try (Connection conn = DBConnection.connect();
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
                    System.out.println(e);
                }
                LocalTime time = LocalTime.parse(rs.getString("time"));
                customerBookings.add(new Booking(bookingId, customerId, scheduleId, date, time));
            }
        } catch (SQLException e) {
            throw new BookingFailedException("Failed to retrieve customer bookings.");
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
        try (Connection conn = DBConnection.connect();
             PreparedStatement stmt = conn.prepareStatement(CANCEL_BOOKING_BY_ID)) {
            stmt.setString(1, bookingID);
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected == 0) {
                throw new BookingFailedException("Could not cancel booking for BookingId: " + bookingID);
            }
        } catch (SQLException e) {
            throw new BookingFailedException("Failed to cancel booking for BookingId: " + bookingID);
        }
    }

    public Booking getBookingByBookingId(String bookingId) throws BookingFailedException {
        try (Connection conn = DBConnection.connect();
             PreparedStatement stmt = conn.prepareStatement(GET_BOOKING_BY_BOOKING_ID)) {
            stmt.setString(1, bookingId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                String customerId = rs.getString("userID");
                String scheduleId = rs.getString("scheduleID");
                return new Booking(bookingId, customerId, scheduleId);
            } else {
                throw new BookingFailedException("Could not fetch booking by bookingId: " + bookingId);
            }
        } catch (SQLException e) {
            throw new BookingFailedException("Failed to fetch booking by bookingId: " + bookingId);
        }
    }

    private String generateUniqueBookingId(String userID, String scheduleID) {
        return userID + scheduleID;
    }
}
