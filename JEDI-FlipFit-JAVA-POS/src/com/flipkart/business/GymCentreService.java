package com.flipkart.business;

import com.flipkart.bean.Slot;
import com.flipkart.bean.*;
import com.flipkart.business.*;

import java.util.Date;
import java.util.List;

public class GymCentreService implements GymCentreServiceInterface{



    public List<GymCentre> getAllCentresByOwnerId(String gymOwnerId) {
        return null;
    }

    public List<GymCentre> getCentresByCity(String city){
        return null;
    }

    @Override
    public List<Slot> getAvailableSlotsByCentreAndDate(String centreID, java.sql.Date date) {
        return null;
    }

    public List<Slot> getAvailableSlotsByCentreAndDate(String centreID, Date date){
        return null;
    }

    public void addCenter(GymCentre gymCentre) {

    }

    public void requestGymCentreApproval(String gymCentreId){
    }

    public GymCentre getGymCentreById(String centreID) {
        return null;
    }
}