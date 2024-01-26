package com.flipkart.utils;

import com.flipkart.bean.GymCentre;
import com.flipkart.bean.GymOwner;

import java.util.List;

import static com.flipkart.constants.Constants.*;
import static com.flipkart.constants.Constants.DASHED_LINE;

public class util {

    public static void printList(Iterable<?> list) {
        for (Object element : list) {
            System.out.println(element);
        }
    }

    public static void printGymCentres(List<GymCentre> gymCentres){
        System.out.println(DASHED_LINE);
        System.out.printf(YELLOW_COLOR + "%-8s\t", "CENTRE-ID");
        System.out.printf("%-8s\t", "NAME");
        System.out.printf("%-6s\t", "CITY");
        System.out.printf("%12s\t", "OWNER-ID");
        System.out.printf("%8s\t", "CAPACITY");
        System.out.printf("%-8s\t\n", "IS-APPROVED" + RESET_COLOR);
        System.out.println(DASHED_LINE);
        System.out.println("");
        for(GymCentre gymCentre: gymCentres) {
            System.out.printf("%-8s\t", gymCentre.getGymCentreID());
            System.out.printf("%-8s\t", gymCentre.getGymCenterName());
            System.out.printf("%-8s\t", gymCentre.getCity());
            System.out.printf("%-8s\t", gymCentre.getOwnerID());
            System.out.printf("%-8s\t", gymCentre.getCapacity());
            if(gymCentre.getIsApproved() == 0) System.out.println("No\n");
            else if(gymCentre.getIsApproved() == 1) System.out.println("Approved\n");
            else System.out.println("Pending\n");
        }
        System.out.println(DASHED_LINE);
    }

    public static void printOwnerList(List<GymOwner> gymOwnerList){
        System.out.println(DASHED_LINE);
        System.out.printf(YELLOW_COLOR + "%-8s\t", "ID");
        System.out.printf("%-8s\t", "NAME");
        System.out.printf("%-8s\t", "EMAIL-ID");
        System.out.printf("%11s\t", "PAN");
        System.out.printf("%-8s\t\n", "IS-APPROVED" + RESET_COLOR);
        System.out.println(DASHED_LINE);
        System.out.println("");
        for(GymOwner gymOwner: gymOwnerList) {
            System.out.printf("%-8s\t", gymOwner.getUserID());
            System.out.printf("%-8s\t", gymOwner.getUserName());
            System.out.printf("%-8s\t", gymOwner.getEmail());
            System.out.printf("%-8s\t", gymOwner.getPanNumber());
            if(gymOwner.getIsApproved()==1)
            {
                System.out.println("Approved\n");
            }
            else if(gymOwner.getIsApproved() == 0)
            {
                System.out.println("No\n");
            } else {
                System.out.println("Pending\n");
            }
        }
        System.out.println("\n" + DASHED_LINE);
    }

    public static void printGymCentreList(List<GymCentre> gymCentreList){
        System.out.println(DASHED_LINE);
        System.out.printf(YELLOW_COLOR + "%-8s\t", "ID");
        System.out.printf("%-8s\t", "GymCenterName");
        System.out.printf("%-8s\t", "City");
        System.out.printf("%6s\t", "Capacity");
        System.out.printf("%-8s\t", "Price");
        System.out.printf("%-8s\t\n", "IS-APPROVED" + RESET_COLOR);
        System.out.println(DASHED_LINE);
        System.out.println("");
        for(GymCentre gymCentre: gymCentreList) {
            System.out.printf("%-8s\t", gymCentre.getGymCentreID());
            System.out.printf("%-8s\t", gymCentre.getGymCenterName());
            System.out.printf("%-8s\t", gymCentre.getCity());
            System.out.printf("%-8s\t", gymCentre.getCapacity());
            System.out.printf("%-8s\t", gymCentre.getPrice());
            if(gymCentre.getIsApproved() == 0) System.out.println("No\n");
            else if(gymCentre.getIsApproved() == 1) System.out.println("Approved\n");
            else System.out.println("Pending\n");
        }
        System.out.println("\n" + DASHED_LINE);
    }
}
