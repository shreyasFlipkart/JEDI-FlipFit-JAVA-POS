package com.flipkart.dao;

import com.flipkart.bean.GymCentre;
import com.flipkart.bean.GymOwner;
import com.flipkart.utils.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static com.flipkart.constants.SQLConstants.*;

public class AdminDAO implements AdminInterfaceDAO {

    private static GymOwnerInterfaceDAO gymOwnerDAO = new GymOwnerDAO();
    private static GymCentreInterfaceDAO gymCentreDAO = new GymCentreDAO();

    public List<GymOwner> getPendingGymOwners() {
        return gymOwnerDAO.getPendingGymOwnerList();
    }

    public void validateGymOwner(String gymOwnerId, int isApproved) {
        gymOwnerDAO.validateGymOwner(gymOwnerId, isApproved);
    }

    public void validateGymCentre(String gymCentreId, int isApproved) {
        try {
            Connection conn = DBConnection.connect();
            PreparedStatement stmt = conn.prepareStatement(SQL_APPROVE_GYM_CENTRE_BY_ID_QUERY);
            stmt.setInt(1, isApproved);
            stmt.setString(2, gymCentreId);
            stmt.executeUpdate();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<GymCentre> getPendingGymCentres() {
        return gymCentreDAO.getPendingGymCentreList();
    }
}
