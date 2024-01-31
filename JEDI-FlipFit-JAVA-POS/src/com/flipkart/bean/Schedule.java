package com.flipkart.bean;

import java.sql.Date;
import java.util.UUID;

/**
 * Represents a schedule for a particular date and time slot.
 */
public class Schedule {
    // Properties
    private String scheduleID;   // Unique identifier for the schedule
    private Date date;           // Date for which the schedule is set
    private String slotID;       // Identifier for the time slot
    private int availability;    // Availability status for the schedule

    /**
     * Constructor to create a new schedule.
     *
     * @param date         The date for the schedule.
     * @param slotID       The identifier for the time slot.
     * @param availability The availability status for the schedule.
     */
    public Schedule(Date date, String slotID, int availability) {
        this.scheduleID = date.toString() + slotID; // Generating a unique schedule ID
        this.date = date;
        this.slotID = slotID;
        this.availability = availability;
    }

    /**
     * Constructor to create a schedule with a predefined schedule ID.
     *
     * @param scheduleID   The unique identifier for the schedule.
     * @param date         The date for the schedule.
     * @param slotID       The identifier for the time slot.
     * @param availability The availability status for the schedule.
     */
    public Schedule(String scheduleID, Date date, String slotID, int availability) {
        this.scheduleID = scheduleID;
        this.date = date;
        this.slotID = slotID;
        this.availability = availability;
    }

    /**
     * Default constructor for Schedule.
     */
    public Schedule() {

    }

    /**
     * Getter for scheduleID.
     *
     * @return The unique identifier for the schedule.
     */
    public String getScheduleID() {
        return scheduleID;
    }

    /**
     * Setter for scheduleID.
     *
     * @param scheduleID The unique identifier for the schedule.
     */
    public void setScheduleID(String scheduleID) {
        this.scheduleID = scheduleID;
    }

    /**
     * Getter for date.
     *
     * @return The date for the schedule.
     */
    public Date getDate() {
        return date;
    }

    /**
     * Setter for date.
     *
     * @param date The date for the schedule.
     */
    public void setDate(Date date) {
        this.date = date;
    }

    /**
     * Getter for slotID.
     *
     * @return The identifier for the time slot.
     */
    public String getSlotID() {
        return slotID;
    }

    /**
     * Setter for slotID.
     *
     * @param slotID The identifier for the time slot.
     */
    public void setSlotID(String slotID) {
        this.slotID = slotID;
    }

    /**
     * Getter for availability.
     *
     * @return The availability status for the schedule.
     */
    public int getAvailability() {
        return availability;
    }

    /**
     * Setter for availability.
     *
     * @param availability The availability status for the schedule.
     */
    public void setAvailability(int availability) {
        this.availability = availability;
    }
}