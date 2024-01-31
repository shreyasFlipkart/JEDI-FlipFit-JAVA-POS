package com.flipkart.business;

import com.flipkart.dao.SlotDAO;
import com.flipkart.bean.Slot;

import java.util.List;
import static com.flipkart.constants.Constants.*;

public class SlotFlipfitImplService implements SlotFlipfitServiceInterface {
    private static SlotDAO slotDAO = new SlotDAO();
    public List<Slot> getAllSlotsByCentre(String centreID) {
        return slotDAO.getSlotByCentreId(centreID);
    }

    public Slot getSlotByID(String slotID){
        return slotDAO.getSlotById(slotID);
    }

    public Slot getSlotByIDandCentreId(String slotID,String centreId){
        return slotDAO.getSlotByIdandCentreId(slotID,centreId);
    }

    public List<Slot> getSlotList(){
        return slotDAO.getSlotList();
    }

    public void addSlotsForGym(String gymCentreId, List<Slot> slotList){
        System.out.println(GREEN_COLOR+"Adding all slots to gym: " +RESET_COLOR+ gymCentreId);
        slotList.forEach(slot -> {
            slot.setCentreID(gymCentreId);
            slotDAO.addSlot(slot);
        });
    }

    public boolean isSlotValid(String slotID,String centreId){
        return getSlotByIDandCentreId(slotID,centreId) != null;
    }
    public boolean deleteSlotById(String slotId){
        return slotDAO.deleteSlotById(slotId);
    }
}