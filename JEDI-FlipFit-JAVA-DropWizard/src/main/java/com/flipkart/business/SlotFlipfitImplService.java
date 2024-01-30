package com.flipkart.business;

import com.flipkart.bean.Slot;
import com.flipkart.dao.SlotDAO;
import com.flipkart.utils.addSlotDTO;

import java.util.List;

import static com.flipkart.constants.Constants.GREEN_COLOR;
import static com.flipkart.constants.Constants.RESET_COLOR;

public class SlotFlipfitImplService implements SlotFlipfitServiceInterface {
    private static SlotDAO slotDAO = new SlotDAO();
    private GymCentreFlipfitServiceInterface gymCentreService = new GymCentreFlipfitImplService();
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

    public void addSlotListForGym(String gymCentreId, List<addSlotDTO> slotList){
        System.out.println(GREEN_COLOR+"Adding all slots to gym: " +RESET_COLOR+ gymCentreId);
        slotList.stream()
                .filter(slot -> !gymCentreService.isValdidCentre(gymCentreId))
                .forEach(slot -> slotDAO.addSlotDTO(slot.getSlotId(), slot.getCentreID(), slot.getTime()));

    }

    public void addSlotsForGym(String gymCentreId, List<Slot> slotList){
        System.out.println("Adding all slots to gym: " + gymCentreId);
        for(Slot slot : slotList) {
            slot.setCentreID(gymCentreId);
            slotDAO.addSlot(slot);
        }
    }

    public boolean isSlotValid(String slotID,String centreId){
        return getSlotByIDandCentreId(slotID,centreId) != null;
    }
    public boolean deleteSlotById(String slotId){
        return slotDAO.deleteSlotById(slotId);
    }
}