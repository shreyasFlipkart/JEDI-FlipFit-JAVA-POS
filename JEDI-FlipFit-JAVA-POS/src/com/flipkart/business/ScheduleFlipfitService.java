package com.flipkart.business;

import java.util.ArrayList;
import java.sql.Date;
import java.util.List;

import com.flipkart.dao.ScheduleDAO;
import com.flipkart.bean.Schedule;
import com.flipkart.bean.Slot;

public class ScheduleFlipfitService implements ScheduleFlipfitServiceInterface {

    private GymCentreFlipfitService gymCentreFlipfitService = new GymCentreFlipfitService();
    private SlotFlipfitService slotFlipfitService = new SlotFlipfitService();
    private ScheduleDAO scheduleDAO = new ScheduleDAO();

    public Schedule createSchedule(Date date, String slotId){
        String centreID = slotFlipfitService.getSlotByID(slotId).getCentreID();
        int availability = gymCentreFlipfitService.getGymCentreById(centreID).getCapacity();
        Schedule schedule = new Schedule( date, slotId, availability);
        scheduleDAO.addSchedule(schedule);

        return schedule;
    }

    public Schedule getScheduleByDateAndSlotId(String SlotId, Date date){
        //returns whether current schedule exists or not
        List<Schedule> scheduleList = scheduleDAO.getAllScheduleByDate(date);
        for(Schedule schedule: scheduleList){
            if(schedule.getSlotID().equals(SlotId))
                return schedule;
        }

        //Schedule doesn't exist, return null
        return null;
    }

    public boolean modifySchedule(String scheduleId,int action){
        // increment or decrement availability based on action
        // 1 for increasing availability, -1 for decreasing
        return scheduleDAO.modifySchedule(scheduleId, action);
    }

    public List<Schedule> checkAvailability(String centreID, Date date){
        List<Slot> allSlotsForGym = slotFlipfitService.getAllSlotsByCentre(centreID);
        List<Schedule> allAvailableSchedules = new ArrayList<>();
        for(Slot slot : allSlotsForGym){
            String slotId = slot.getSlotId();
            Schedule schedule = getOrCreateSchedule(slotId, date);
            if(schedule.getAvailability() > 0)
                allAvailableSchedules.add(schedule);
        }

        return allAvailableSchedules;
    }

    public List<Slot> getAllAvailableSlotsByDate(String centreID, Date date) {
        List<Slot> allSlotsOfThatCentre = slotFlipfitService.getAllSlotsByCentre(centreID);
        List<Slot> response = slotFlipfitService.getAllSlotsByCentre(centreID);
        for(Slot slot: allSlotsOfThatCentre){
            for(Schedule schedule: scheduleDAO.getAllScheduleByDate(date)){
                if (slotFlipfitService.getSlotByID(schedule.getSlotID()).getCentreID().equals(centreID)){
                    if(schedule.getAvailability() <= 0){
                        response.remove(slot);
                    }
                }
            }
        }
        return response;
    }

    public Schedule getSchedule(String scheduleID){
        return scheduleDAO.getSchedule(scheduleID);
    }

    public Schedule getOrCreateSchedule(String slotId, Date date) {
        Schedule schedule = getScheduleByDateAndSlotId(slotId, date);
        if( schedule == null ){
            return createSchedule(date,slotId);
        }
        return schedule;

    }
}