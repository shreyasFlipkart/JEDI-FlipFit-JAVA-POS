package com.flipkart.business;

import com.flipkart.bean.GymCentre;
import com.flipkart.bean.GymOwner;
import com.flipkart.dao.AdminDAO;
import com.flipkart.dao.AdminInterfaceDAO;

import java.util.ArrayList;
import java.util.List;

import static com.flipkart.constants.Constants.RESET_COLOR;
import static com.flipkart.constants.Constants.YELLOW_COLOR;

public class AdminFlipfitImplService implements AdminFlipfitServiceInterface {

    AdminInterfaceDAO adminDAO  = new AdminDAO();
    private List<GymOwner> pendinGymOwnerList = new ArrayList<>();
    private List<GymCentre> pendinGymCentreList = new ArrayList<>();

    public void approveGymCenter(String gymCentreId,int isApproved){
        adminDAO.validateGymCentre(gymCentreId,isApproved);
    }

    public void approveGymOwner(String gymOwnerId,int isApprove){
        //takes GymOwner Object as input and return boolean
        adminDAO.validateGymOwner(gymOwnerId,isApprove);
    }

    public List<GymCentre> viewPendingGymCentres(){
        //views all pending requests
        //System.out.println("Viewing pending Gym Center Approvals: ");
        pendinGymCentreList = adminDAO.getPendingGymCentres();
        return pendinGymCentreList;
    }

    public List<GymOwner> viewPendingGymOwners(){
        //views all pending requests
        System.out.println(YELLOW_COLOR+"Viewing pending Gym Owner Approvals: "+RESET_COLOR);
        pendinGymOwnerList = adminDAO.getPendingGymOwners();
        return pendinGymOwnerList;
    }

}