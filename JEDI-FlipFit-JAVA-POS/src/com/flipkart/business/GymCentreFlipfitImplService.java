package com.flipkart.business;

import com.flipkart.dao.GymCentreDAO;
import com.flipkart.dao.GymCentreInterfaceDAO;
import com.flipkart.bean.GymCentre;
import com.flipkart.bean.Slot;

import java.sql.Date;
import java.util.List;

public class GymCentreFlipfitImplService implements GymCentreFlipfitServiceInterface {

    private static GymCentreInterfaceDAO gymCentreDAO = new GymCentreDAO();
    private static ScheduleFlipfitServiceInterface scheduleService = new ScheduleFlipfitImplService();

    public List<GymCentre> viewAllGymCentres(){
        return gymCentreDAO.getGymCentreList();
    }

    public List<GymCentre> getAllCentresByOwnerId(String gymOwnerId) {
        return gymCentreDAO.getAllCentresByOwmerId(gymOwnerId);
    }

    public List<GymCentre> getCentresByCity(String city){
        return gymCentreDAO.getGymCentreListByCity(city);
    }

    public List<Slot> getAvailableSlotsByCentreAndDate(String centreID, Date date){
        return scheduleService.getAllAvailableSlotsByDate(centreID, date);
    }

    public void addCenter(GymCentre gymCentre) {
        //takes gymCenter details
        gymCentreDAO.addGymCentre(gymCentre);

    }

    public void requestGymCentreApproval(String gymCentreId){
        gymCentreDAO.sendCentreApprovalRequest(gymCentreId);
    }

    public GymCentre getGymCentreById(String centreID) {
        GymCentre gymCentre = gymCentreDAO.getGymCentreByCentreId(centreID);
        return gymCentre;
    }

    public List<GymCentre> getCentersSortedByCity(){
        return gymCentreDAO.getCentersSortedByCities();
    }

    @Override
    public List<GymCentre> getAllCentresByOwmerId(String gymOwnerId) {
        return gymCentreDAO.getAllCentresByOwmerId(gymOwnerId);
    }
}