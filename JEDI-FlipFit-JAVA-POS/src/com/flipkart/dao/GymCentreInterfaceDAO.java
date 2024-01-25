package com.flipkart.dao;

import com.flipkart.bean.GymCentre;

import java.util.List;

public interface GymCentreInterfaceDAO {

    public List<GymCentre> getGymCentreList();
    List<GymCentre> getAllCentresByOwmerId(String gymOwnerId);
    GymCentre getGymCentreByCentreId(String gymCentreId);
    void addGymCentre(GymCentre centre);
    List<GymCentre> getPendingGymCentreList();
    void validateGymCentre(String gymCentreId, int isApproved);
    void sendCentreApprovalRequest(String gymCentreId);
    List<GymCentre> getGymCentreListByCity(String city);
    List<GymCentre> getCentersSortedByCities();
}
