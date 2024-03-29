package com.flipkart.bean;

import java.time.LocalTime;
import java.util.Date;
/**
 * Represents a booking in the system.
 */
public class Booking {
      /**
    //     * The unique identifier for the booking.
    //     */
    private String bookingID;
    /**
     * The user ID associated with the booking.
     */
    private String userID;
    /**
     * The schedule ID associated with the booking.
     */
    private String scheduleID;
    /**
     * The Date associated with the booking.
     */
    private Date date;
    /**
     * The time associated with the booking.
     */
    private LocalTime time;
    /**
     * Constructs a new booking with the given user ID and schedule ID.
     *
     * @param userID     The user ID associated with the booking.
     * @param scheduleID The schedule ID associated with the booking.
     */
    public Booking(String userID, String scheduleID) {
        this.userID = userID;
        this.scheduleID = scheduleID;
    }
    /**
     * Constructs a new booking with the given booking ID, user ID, and schedule ID.
     *
     * @param bookingID  The unique identifier for the booking.
     * @param userID     The user ID associated with the booking.
     * @param scheduleID The schedule ID associated with the booking.
     */
    public Booking(String bookingID, String userID, String scheduleID) {
        this.bookingID = bookingID;
        this.userID = userID;
        this.scheduleID = scheduleID;
    }
    /**
     * Constructs a new booking with the given booking ID, user ID, and schedule ID.
     *
     * @param bookingID  The unique identifier for the booking.
     * @param userID     The user ID associated with the booking.
     * @param scheduleID The schedule ID associated with the booking.
     * @param date    The Date associated with the booking.
     * @param time The Time associated with the booking.
     */

    public Booking(String bookingID, String userID, String scheduleID, Date date, LocalTime time){
        this.bookingID = bookingID;
        this.userID = userID;
        this.scheduleID = scheduleID;
        this.date = date;
        this.time = time;
    }
    /**
     * Gets the booking ID.
     *
     * @return The booking ID.
     */
    public String getBookingID() {
        return bookingID;
    }
    /**
     * Sets the booking ID.
     *
     * @param bookingID The unique identifier for the booking.
     */
    public void setBookingID(String bookingID) {
        this.bookingID = bookingID;
    }
    /**
     * Gets the user ID associated with the booking.
     *
     * @return The user ID.
     */
    public String getUserID() {
        return userID;
    }
    /**
     * Sets the user ID associated with the booking.
     *
     * @param userID The user ID.
     */
    public void setUserID(String userID) {
        this.userID = userID;
    }
    /**
     * Gets the schedule ID associated with the booking.
     *
     * @return The schedule ID.
     */
    public String getScheduleID() {
        return scheduleID;
    }
    /**
     * Sets the schedule ID associated with the booking.
     *
     * @param scheduleID The schedule ID.
     */
    public void setScheduleID(String scheduleID) {
        this.scheduleID = scheduleID;
    }
    /**
     * Gets the Date associated with the booking.
     *
     * @return date
     */
    public Date getDate() {
        return date;
    }
    /**
     * Sets the Date associated with the booking.
     *
     * @param date The schedule ID.
     */
    public void setDate(Date date) {
        this.date = date;
    }
    /**
     * Gets the time associated with the booking.
     *
     * @return date
     */
    public LocalTime getTime() {
        return time;
    }
    /**
     * Sets the Date associated with the booking.
     *
     * @param time The schedule ID.
     */
    public void setTime(LocalTime time) {
        this.time = time;
    }
}
