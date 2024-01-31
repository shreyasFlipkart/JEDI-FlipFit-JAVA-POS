package com.flipkart.business;

import java.util.ArrayList;
import java.sql.Date;
import java.util.List;
import java.util.stream.Collectors;

import com.flipkart.dao.ScheduleDAO;
import com.flipkart.bean.Schedule;
import com.flipkart.bean.Slot;

public class ScheduleFlipfitImplService implements ScheduleFlipfitServiceInterface {

    private GymCentreFlipfitImplService gymCentreService = new GymCentreFlipfitImplService();
    private SlotFlipfitImplService slotService = new SlotFlipfitImplService();
    private ScheduleDAO scheduleDAO = new ScheduleDAO();

    public Schedule createSchedule(Date date, String slotId){
        String centreID = slotService.getSlotByID(slotId).getCentreID();
        int availability = gymCentreService.getGymCentreById(centreID).getCapacity();
        Schedule schedule = new Schedule( date, slotId, availability);
        scheduleDAO.addSchedule(schedule);

        return schedule;
    }

    public Schedule getScheduleByDateAndSlotId(String slotId, Date date){
        //returns whether current schedule exists or not
        List<Schedule> scheduleList = scheduleDAO.getAllScheduleByDate(date);
        Schedule foundSchedule = scheduleList.stream()
                .filter(schedule -> schedule.getSlotID().equals(slotId))
                .findFirst()
                .orElse(null);

        return foundSchedule;


        //Schedule doesn't exist, return null
    }

    public boolean modifySchedule(String scheduleId,int action){
        // increment or decrement availability based on action
        // 1 for increasing availability, -1 for decreasing
        return scheduleDAO.modifySchedule(scheduleId, action);
    }

    public List<Schedule> checkAvailability(String centreID, Date date){
        List<Slot> allSlotsForGym = slotService.getAllSlotsByCentre(centreID);
        List<Schedule> allAvailableSchedules = new ArrayList<>();
        allAvailableSchedules.addAll(
                allSlotsForGym.stream()
                        .map(slot -> {
                            String slotId = slot.getSlotId();
                            return getOrCreateSchedule(slotId, date);
                        })
                        .filter(schedule -> schedule.getAvailability() > 0)
                        .collect(Collectors.toList())
        );


        return allAvailableSchedules;
    }

    public List<Slot> getAllAvailableSlotsByDate(String centreID, Date date) {
        List<Slot> allSlotsOfThatCentre = slotService.getAllSlotsByCentre(centreID);
        List<Slot> response = slotService.getAllSlotsByCentre(centreID);
        allSlotsOfThatCentre.removeIf(slot ->
                scheduleDAO.getAllScheduleByDate(date).stream()
                        .anyMatch(schedule ->
                                slotService.getSlotByID(schedule.getSlotID())
                                        .getCentreID().equals(centreID) && schedule.getAvailability() <= 0
                        )
        );
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