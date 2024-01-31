package com.flipkart.bean;

import java.sql.Date;
import java.time.LocalTime;
/**
 * The UserPlan class represents a user's plan for a specific time slot in a gym centre.
 * It includes properties such as slot ID, centre ID, time, schedule ID, and date.
 * The class provides getter and setter methods for each property.
 *
 * @version 1.0
 * @author
 */
public class UserPlan {

    /** The unique identifier for the time slot. */
    private String slotId;

    /** The unique identifier for the gym centre. */
    private String centreID;

    /** The time associated with the user's plan. */
    private LocalTime time;

    /** The unique identifier for the schedule. */
    private String scheduleID;

    /** The date associated with the user's plan. */
    private Date date;

    /**
     * Parameterized constructor for the UserPlan class.
     * Initializes the properties with the provided values.
     *
     * @param slotId The unique identifier for the time slot.
     * @param centreID The unique identifier for the gym centre.
     * @param time The time associated with the user's plan.
     * @param scheduleID The unique identifier for the schedule.
     * @param date The date associated with the user's plan.
     */
    public UserPlan(String slotId, String centreID, LocalTime time, String scheduleID, Date date) {
        // Implementation details...
        this.slotId = slotId;
        this.centreID = centreID;
        this.time = time;
        this.scheduleID = scheduleID;
        this.date = date;
    }

    /**
     * Getter method to retrieve the slot ID.
     *
     * @return The slot ID.
     */
    public String getSlotId() {
        // Implementation details...
        return slotId;
    }

    /**
     * Setter method to set the slot ID.
     *
     * @param slotId The slot ID to be set.
     */
    public void setSlotId(String slotId) {
        // Implementation details...
        this.slotId = slotId;
    }

    /**
     * Getter method to retrieve the centre ID.
     *
     * @return The centre ID.
     */
    public String getCentreID() {
        // Implementation details...
        return centreID;
    }

    /**
     * Setter method to set the centre ID.
     *
     * @param centreID The centre ID to be set.
     */
    public void setCentreID(String centreID) {
        // Implementation details...
        this.centreID = centreID;
    }

    /**
     * Getter method to retrieve the time.
     *
     * @return The time associated with the user's plan.
     */
    public LocalTime getTime() {
        // Implementation details...
        return time;
    }

    /**
     * Setter method to set the time.
     *
     * @param time The time to be set.
     */
    public void setTime(LocalTime time) {
        // Implementation details...
        this.time = time;
    }

    /**
     * Getter method to retrieve the schedule ID.
     *
     * @return The schedule ID.
     */
    public String getScheduleID() {
        // Implementation details...
        return scheduleID;
    }

    /**
     * Setter method to set the schedule ID.
     *
     * @param scheduleID The schedule ID to be set.
     */
    public void setScheduleID(String scheduleID) {
        // Implementation details...
        this.scheduleID = scheduleID;
    }

    /**
     * Getter method to retrieve the date.
     *
     * @return The date associated with the user's plan.
     */
    public Date getDate() {
        // Implementation details...
        return date;
    }

    /**
     * Setter method to set the date.
     *
     * @param date The date to be set.
     */
    public void setDate(Date date) {
        // Implementation details...
        this.date = date;
    }
}