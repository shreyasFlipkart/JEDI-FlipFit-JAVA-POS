package com.flipkart.business;

import com.flipkart.bean.GymCentre;
import com.flipkart.bean.GymOwner;

import java.util.ArrayList;
import java.util.List;

public class AdminService implements AdminServiceInterface{


    private List<GymOwner> pendingGymOwnerList = new ArrayList<>();
    //private List<GymCentre> pendingGymCentreList = new ArrayList<>();

    public void approveGymCenter(String gymCentreId,int isApproved){

    }

    public void approveGymOwner(String gymOwnerId,int isApprove){
    }

    public List<GymCentre> viewPendingGymCentres(){
        return null;
    }

    public List<GymOwner> viewPendingGymOwners(){
        return null;
    }

}