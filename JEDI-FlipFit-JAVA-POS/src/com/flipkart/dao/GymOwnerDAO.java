package com.flipkart.dao;

import com.flipkart.bean.GymOwner;

import java.util.ArrayList;
import java.util.List;

public class GymOwnerDAO implements GymOwnerInterfaceDAO {

    private static List<GymOwner> gymOwnerList = new ArrayList<>();

    public List<GymOwner> getGymOwnerList() {

        gymOwnerList.add(new GymOwner("123","name","name@email.com","1234123",1));
        return gymOwnerList;
    }

    public void setGymOwnerList(List<GymOwner> gymOwnerList) {
        this.gymOwnerList = new ArrayList<>(gymOwnerList);
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
        gymOwner.setIsApproved(2);

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
                owner.setIsApproved(2);
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
                owner.setIsApproved(isApproved);
                break;
            }
        }
    }

    private String generateUniqueGymOwnerId(String userName) {
        // Implement logic to generate a unique GymOwner ID based on your requirements
        return userName + System.currentTimeMillis();
    }
}
