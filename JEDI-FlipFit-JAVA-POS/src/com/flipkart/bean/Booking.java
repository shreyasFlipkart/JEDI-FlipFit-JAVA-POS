package com.flipkart.bean;

import java.time.LocalTime;
import java.util.Date;

public class Booking {
    // properties
    private String bookingID;
    private String userID;
    private String scheduleID;

    private Date date;

    private LocalTime time;

    public Booking(String userID, String scheduleID) {
        this.userID = userID;
        this.scheduleID = scheduleID;
    }

    public Booking(String bookingID, String userID, String scheduleID) {
        this.bookingID = bookingID;
        this.userID = userID;
        this.scheduleID = scheduleID;
    }

    public Booking(String bookingID, String userID, String scheduleID, Date date, LocalTime time){
        this.bookingID = bookingID;
        this.userID = userID;
        this.scheduleID = scheduleID;
        this.date = date;
        this.time = time;
    }

    public String getBookingID() {
        return bookingID;
    }

    public void setBookingID(String bookingID) {
        this.bookingID = bookingID;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getScheduleID() {
        return scheduleID;
    }

    public void setScheduleID(String scheduleID) {
        this.scheduleID = scheduleID;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }
}
