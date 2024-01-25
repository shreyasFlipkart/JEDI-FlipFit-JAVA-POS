package com.flipkart.dao;

import com.flipkart.bean.GymCentre;

import java.util.*;

public class GymCentreDAO implements GymCentreInterfaceDAO {

    private static List<GymCentre> allGymCentres = new ArrayList<>();


    public List<GymCentre> getGymCentreList(){
        return allGymCentres;
    }
    public List<GymCentre> getAllCentresByOwmerId(String ownerId) {
        List<GymCentre> ownerGymCentres = new ArrayList<>();
        for (GymCentre gymCentre : allGymCentres) {
            if (gymCentre.getOwnerID().equals(ownerId)) {
                ownerGymCentres.add(gymCentre);
            }
        }
        return ownerGymCentres;
    }

    public GymCentre getGymCentreByCentreId(String gymCentreId) {
        for (GymCentre gymCentre : allGymCentres) {
            if (gymCentre.getGymCentreID().equals(gymCentreId)) {
                return gymCentre;
            }
        }
        return null; // or throw an exception if not found
    }

    public void addGymCentre(GymCentre centre) {
        // Assuming you have a method to generate a unique gym centre ID
        //String gymCentreId = generateUniqueGymCentreId(centre.getGymCenterName());
        String gymCentreId = centre.getGymCentreID();
        // Set the generated ID to the GymCentre object
        centre.setGymCentreID(gymCentreId);

        // Add the GymCentre object to the list
        allGymCentres.add(centre);
        System.out.println("Your Gym Center has been added, Send an admin approval request by pressing 5 to get you gym registered");

    }

    public List<GymCentre> getPendingGymCentreList() {
        List<GymCentre> pendingList = new ArrayList<>();
        for (GymCentre gymCentre : allGymCentres) {
            if (gymCentre.getIsApproved()==2) {
                pendingList.add(gymCentre);
            }
        }
        return pendingList;
    }

    public void validateGymCentre(String gymCentreId, int isApproved) {
        for (GymCentre gymCentre : allGymCentres) {
            if (gymCentre.getGymCentreID().equals(gymCentreId)) {
                gymCentre.setIsApproved(isApproved);
                break;
            }
        }
    }

    public void sendCentreApprovalRequest(String gymCentreId) {
        for (GymCentre gymCentre : allGymCentres) {
            if (gymCentre.getGymCentreID().equals(gymCentreId)) {
                gymCentre.setIsApproved(2);
                System.out.println("Sending gym centre approval request for ID: " + gymCentreId);
                break;
            }
        }
    }

    public List<GymCentre> getGymCentreListByCity(String city) {
        List<GymCentre> allCentreByCity = new ArrayList<>();
        for (GymCentre gymCentre : allGymCentres) {
            if (gymCentre.getCity().equals(city) && gymCentre.getIsApproved()==1) {
                allCentreByCity.add(gymCentre);
            }
        }
        return allCentreByCity;
    }

    private String generateUniqueGymCentreId(String centreName) {
        // Implement logic to generate a unique gym centre ID based on your requirements
        return centreName + System.currentTimeMillis();
    }

    public List<GymCentre> getCentersSortedByCities(){
        Comparator<GymCentre> compareByCity = (GymCentre g1, GymCentre g2) -> g1.getCity().compareTo(g2.getCity());

        List<GymCentre> allCentreByCity = new ArrayList<>();

        for (GymCentre gymCentre : allGymCentres) {
            if (gymCentre.getIsApproved()==1) {
                allCentreByCity.add(gymCentre);
            }
        }

        allCentreByCity.sort(compareByCity);

        return allCentreByCity;
    }
}
