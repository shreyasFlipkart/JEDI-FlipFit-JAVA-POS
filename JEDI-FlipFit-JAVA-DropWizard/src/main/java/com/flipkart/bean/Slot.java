package com.flipkart.bean;

import java.sql.Date;
import java.time.LocalTime;

/**
 * Represents a time slot for scheduling purposes.
 */
public class Slot {
    // Properties
    private String slotId;      // Unique identifier for the slot
    private String centreID;    // Identifier for the center associated with the slot
    private LocalTime time;     // Time of the slot

    /**
     * Constructor to create a new slot.
     *
     * @param slotId    The unique identifier for the slot.
     * @param centreID  The identifier for the center associated with the slot.
     * @param time      The time of the slot.
     */
    public Slot(String slotId, String centreID, LocalTime time) {
        this.slotId = slotId;
        this.centreID = centreID;
        this.time = time;
    }

    /**
     * Default constructor for Slot.
     */
    public Slot() {
    }

    /**
     * Getter for slotId.
     *
     * @return The unique identifier for the slot.
     */
    public String getSlotId() {
        return slotId;
    }

    /**
     * Setter for slotId.
     *
     * @param slotId The unique identifier for the slot.
     */
    public void setSlotId(String slotId) {
        this.slotId = slotId;
    }

    /**
     * Getter for centreID.
     *
     * @return The identifier for the center associated with the slot.
     */
    public String getCentreID() {
        return centreID;
    }

    /**
     * Setter for centreID.
     *
     * @param centreID The identifier for the center associated with the slot.
     */
    public void setCentreID(String centreID) {
        this.centreID = centreID;
    }

    /**
     * Getter for time.
     *
     * @return The time of the slot.
     */
    public LocalTime getTime() {
        return time;
    }

    /**
     * Setter for time.
     *
     * @param time The time of the slot.
     */
    public void setTime(LocalTime time) {
        this.time = time;
    }

    /**
     * Represents a user's plan associated with a particular slot.
     */
    public static class UserPlan {

        private String slotId;      // Unique identifier for the slot
        private String centreID;    // Identifier for the center associated with the slot
        private LocalTime time;     // Time of the slot
        private String scheduleID;  // Unique identifier for the associated schedule
        private Date date;          // Date for the associated schedule

        /**
         * Constructor to create a new user plan.
         *
         * @param slotId      The unique identifier for the slot.
         * @param centreID    The identifier for the center associated with the slot.
         * @param time        The time of the slot.
         * @param scheduleID  The unique identifier for the associated schedule.
         * @param date        The date for the associated schedule.
         */
        public UserPlan(String slotId, String centreID, LocalTime time, String scheduleID, Date date) {
            this.slotId = slotId;
            this.centreID = centreID;
            this.time = time;
            this.scheduleID = scheduleID;
            this.date = date;
        }

        /**
         * Getter for slotId.
         *
         * @return The unique identifier for the slot.
         */
        public String getSlotId() {
            return slotId;
        }

        /**
         * Setter for slotId.
         *
         * @param slotId The unique identifier for the slot.
         */
        public void setSlotId(String slotId) {
            this.slotId = slotId;
        }

        /**
         * Getter for centreID.
         *
         * @return The identifier for the center associated with the slot.
         */
        public String getCentreID() {
            return centreID;
        }

        /**
         * Setter for centreID.
         *
         * @param centreID The identifier for the center associated with the slot.
         */
        public void setCentreID(String centreID) {
            this.centreID = centreID;
        }

        /**
         * Getter for time.
         *
         * @return The time of the slot.
         */
        public LocalTime getTime() {
            return time;
        }

        /**
         * Setter for time.
         *
         * @param time The time of the slot.
         */
        public void setTime(LocalTime time) {
            this.time = time;
        }

        /**
         * Getter for scheduleID.
         *
         * @return The unique identifier for the associated schedule.
         */
        public String getScheduleID() {
            return scheduleID;
        }

        /**
         * Setter for scheduleID.
         *
         * @param scheduleID The unique identifier for the associated schedule.
         */
        public void setScheduleID(String scheduleID) {
            this.scheduleID = scheduleID;
        }

        /**
         * Getter for date.
         *
         * @return The date for the associated schedule.
         */
        public Date getDate() {
            return date;
        }

        /**
         * Setter for date.
         *
         * @param date The date for the associated schedule.
         */
        public void setDate(Date date) {
            this.date = date;
        }
    }
}