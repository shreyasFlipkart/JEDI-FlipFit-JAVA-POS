package com.flipkart.business;

import com.flipkart.dao.GymOwnerDAO;
import com.flipkart.bean.GymOwner;

import java.util.List;

public class GymOwnerFlipfitImplService implements GymOwnerFlipfitServiceInterface {
    private static GymOwnerDAO gymOwnerDAO = new GymOwnerDAO();
    public void requestGymOwnerApproval(String gymOwnerId) {
        gymOwnerDAO.sendOwnerApprovalRequest(gymOwnerId);

    }

    public List<GymOwner> viewAllGymOwners() {
        return gymOwnerDAO.getGymOwnerList();
    }


//    public Object addGymOwnerDetails() {
//        //takes gymOwner object as input
//        return new Object();
//    }

    public boolean updatePassword(String customerId, String newPassword){

        return gymOwnerDAO.updatePassword(customerId,newPassword);
    }

    public boolean loginGymOwner(String username,String password){
        return gymOwnerDAO.loginGymOwner(username,password);
    }

    public void registerGymOwner(String userId,String userName, String password, String email, String panNumber,String cardNumber) {
        gymOwnerDAO.registerGymOwner(new GymOwner(userId,userName,email,password,2,panNumber,cardNumber));
    }
    public boolean editProfile(String gymOwnerId, String username, String email, String cardNumber){
        return gymOwnerDAO.editGymOwner(gymOwnerId, username, email, cardNumber);
    }
    public GymOwner viewGymOwnerProfile(String gymOwnerId){
        return gymOwnerDAO.sendProfileInfo(gymOwnerId);
    }


}

