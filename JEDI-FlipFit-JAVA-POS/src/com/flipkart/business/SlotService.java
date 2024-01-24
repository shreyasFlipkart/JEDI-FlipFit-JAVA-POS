package com.flipkart.business;

import com.flipkart.bean.Slot;

import java.util.List;

public class SlotService implements SlotServiceInterface {

    public List<Slot> getAllSlotsByCentre(String centreID) {
        return null;
    }

    public Slot getSlotByID(String slotID){
        return null;
    }

    public Slot getSlotByIDAndCentreId(String slotID, String centreId){
        return null;
    }

    public List<Slot> getSlotList(){
        return null;
    }

    public void addSlotsForGym(String gymCentreId, List<Slot> slotList){
    }

    public boolean isSlotValid(String slotID,String centreId){
        return true;
    }
}