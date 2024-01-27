package com.flipkart.dao;

import com.flipkart.bean.GymOwner;

import java.util.List;

public interface GymOwnerInterfaceDAO {
    boolean updatePassword(String gymownerName, String newPassword);
    List<GymOwner> getGymOwnerList();
    void setGymOwnerList(List<GymOwner> gymOwnerList);
    void registerGymOwner(GymOwner gymOwner);
    List<GymOwner> getPendingGymOwnerList();
    void sendOwnerApprovalRequest(String gymOwnerId);
    void setPendingGymOwnerList();
    void validateGymOwner(String gymOwnerId, int isApproved);
    public boolean editGymOwner(String gymOwnerId, String username, String email, String cardNumber);
}
