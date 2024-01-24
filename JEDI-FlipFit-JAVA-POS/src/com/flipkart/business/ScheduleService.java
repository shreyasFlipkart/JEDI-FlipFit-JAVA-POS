package com.flipkart.business;

import com.flipkart.bean.Schedule;
import com.flipkart.bean.Slot;

import java.util.Date;
import java.util.List;

public class ScheduleService implements ScheduleServiceInterface {

    public Schedule createSchedule(Date date, String slotId){
        return null;
    }

    public Schedule getScheduleByDateAndSlotId(String SlotId, Date date){
        return null;
    }

    public boolean modifySchedule(String scheduleId,int action){
        return true;
    }

    public List<Schedule> checkAvailability(String centreID, Date date){
        return null;
    }

    public List<Slot> getAllAvailableSlotsByDate(String centreID, Date date) {
        return null;
    }

    public Schedule getSchedule(String scheduleID){
        return null;
    }

    public Schedule getOrCreateSchedule(String slotId, Date date) {
        return null;
    }
}