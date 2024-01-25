package com.flipkart.dao;

import com.flipkart.bean.GymOwner;

import java.util.ArrayList;
import java.util.List;

public class GymOwnerDAO implements com.flipkart.DAO.GymOwnerInterfaceDAO {

    private static List<GymOwner> gymOwnerList = new ArrayList<>();

    public List<GymOwner> getGymOwnerList() {
        return new ArrayList<>(gymOwnerList);
    }

    public void setGymOwnerList(List<GymOwner> gymOwnerList) {
        this.gymOwnerList = new ArrayList<>(gymOwnerList);
    }

    public GymOwner sendProfileInfo(String gymOwnerId){
        for (GymOwner owner : gymOwnerList) {
            if (owner.getUserID().equals(gymOwnerId)) {
                return owner;
            }
        }

        return new GymOwner("-1", "None", "none", "none", "none", "none");
    }
    public boolean loginGymOwner(String username, String password) {
        for (GymOwner owner : gymOwnerList) {
            if (username.equals(owner.getUserName()) && password.equals(owner.getPassword())) {
                System.out.println("Login Success\n");
                return true;
            }
        }
        return false;
    }

    public void registerGymOwner(GymOwner gymOwner) {
        // Assuming you have a method to generate a unique GymOwner ID
        //String gymOwnerId = generateUniqueGymOwnerId(gymOwner.getUserName());
        String gymOwnerId = gymOwner.getUserID();

        // Set the generated ID to the GymOwner object
        gymOwner.setUserID(gymOwnerId);
        gymOwner.setApproved(2);

        // Add the GymOwner object to the list
        gymOwnerList.add(gymOwner);
        System.out.println("Registration Success\n");
    }

    public List<GymOwner> getPendingGymOwnerList() {
        List<GymOwner> pendingList = new ArrayList<>();
        for (GymOwner owner : gymOwnerList) {
            if (owner.getIsApproved()==2) {
                pendingList.add(owner);
            }
        }
        return pendingList;
    }

    public void sendOwnerApprovalRequest(String gymOwnerId) {
        for (GymOwner owner : gymOwnerList) {
            if (owner.getUserID().equals(gymOwnerId)) {
                owner.setApproved(2);
                break;
            }
        }
        System.out.println("Approval Request sent to Admin");
    }

    public void setPendingGymOwnerList() {

    }

    public void validateGymOwner(String gymOwnerId, int isApproved) {
        for (GymOwner owner : gymOwnerList) {
            if (owner.getUserID().equals(gymOwnerId)) {
                owner.setApproved(isApproved);
                break;
            }
        }
    }

    public boolean editGymOwner(String gymOwnerId, String username, String email, String cardNumber){
        for(GymOwner gymOwner : gymOwnerList) {
            if(gymOwner.getUserID().equals(gymOwnerId)) {
                if(username!=null)gymOwner.setUserName(username);
                if(email!=null)gymOwner.setEmail(email);
                if(cardNumber!=null)gymOwner.setCardDetails(cardNumber);
                return true;
            }
        }
        return false;
    }

    private String generateUniqueGymOwnerId(String userName) {
        // Implement logic to generate a unique GymOwner ID based on your requirements
        return userName + System.currentTimeMillis();
    }
}
