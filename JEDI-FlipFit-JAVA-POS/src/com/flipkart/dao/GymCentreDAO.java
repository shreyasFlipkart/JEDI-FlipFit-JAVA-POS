package com.flipkart.dao;

import com.flipkart.bean.GymCentre;
import com.flipkart.utils.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static com.flipkart.constants.SQLConstants.*;

public class GymCentreDAO implements GymCentreInterfaceDAO {

    public List<GymCentre> getGymCentreList() {
        List<GymCentre> allGymCentres = new ArrayList<>();
        try (Connection conn = DBConnection.connect();
             PreparedStatement stmt = conn.prepareStatement(FETCH_ALL_GYM_CENTRES)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                allGymCentres.add(mapResultSetToGymCentre(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return allGymCentres;
    }

    public List<GymCentre> getAllCentresByOwmerId(String ownerId) {
        List<GymCentre> ownerGymCentres = new ArrayList<>();
        try (Connection conn = DBConnection.connect();
             PreparedStatement stmt = conn.prepareStatement(FETCH_GYM_CENTRES_BY_OWNER_ID)) {
            stmt.setString(1, ownerId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                ownerGymCentres.add(mapResultSetToGymCentre(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ownerGymCentres;
    }

    public GymCentre getGymCentreByCentreId(String gymCentreId) {
        try (Connection conn = DBConnection.connect();
             PreparedStatement stmt = conn.prepareStatement(FETCH_GYM_CENTRE_BY_ID)) {
            stmt.setString(1, gymCentreId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return mapResultSetToGymCentre(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void addGymCentre(GymCentre centre) {
        try (Connection conn = DBConnection.connect();
             PreparedStatement stmt = conn.prepareStatement(ADD_GYM_CENTRE)) {
            stmt.setString(1, centre.getGymCentreID());
            stmt.setString(2, centre.getOwnerID());
            stmt.setString(3, centre.getGymCenterName());
            stmt.setString(4, centre.getGstin());
            stmt.setString(5, centre.getCity());
            stmt.setInt(6, centre.getCapacity());
            stmt.setDouble(7, centre.getPrice());
            stmt.setInt(8, centre.getIsApproved());

            stmt.executeUpdate();
            System.out.println("Your Gym Center has been added, Send an admin approval request by pressing 5 to get your gym registered");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<GymCentre> getPendingGymCentreList() {
        List<GymCentre> pendingList = new ArrayList<>();
        try (Connection conn = DBConnection.connect();
             PreparedStatement stmt = conn.prepareStatement(FETCH_ALL_PENDING_GYM_CENTRES_QUERY)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                pendingList.add(mapResultSetToGymCentre(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pendingList;
    }

    public void validateGymCentre(String gymCentreId, int isApproved) {
        try (Connection conn = DBConnection.connect();
             PreparedStatement stmt = conn.prepareStatement(SQL_APPROVE_GYM_CENTRE_BY_ID_QUERY)) {
            stmt.setInt(1, isApproved);
            stmt.setString(2, gymCentreId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void sendCentreApprovalRequest(String gymCentreId) {
        try (Connection conn = DBConnection.connect();
             PreparedStatement stmt = conn.prepareStatement(SQL_SEND_GYM_CENTRE_APPROVAL_REQ_QUERY)) {
            stmt.setString(1, gymCentreId);
            stmt.executeUpdate();
            System.out.println("Sending gym centre approval request for ID: " + gymCentreId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<GymCentre> getGymCentreListByCity(String city) {
        List<GymCentre> allCentreByCity = new ArrayList<>();
        try (Connection conn = DBConnection.connect();
             PreparedStatement stmt = conn.prepareStatement(FETCH_GYM_CENTRES_BY_CITY)) {
            stmt.setString(1, city);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                allCentreByCity.add(mapResultSetToGymCentre(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return allCentreByCity;
    }

    private GymCentre mapResultSetToGymCentre(ResultSet rs) throws SQLException {
        GymCentre gymCentre = new GymCentre();
        gymCentre.setGymCentreID(rs.getString("centreId"));
        gymCentre.setOwnerID(rs.getString("ownerId"));
        gymCentre.setGymCenterName(rs.getString("centreName"));
        gymCentre.setGstin(rs.getString("gstin"));
        gymCentre.setCity(rs.getString("city"));
        gymCentre.setCapacity(rs.getInt("capacity"));
        gymCentre.setPrice(rs.getInt("price"));
        gymCentre.setIsApproved(rs.getInt("isApproved"));
        return gymCentre;
    }

    private String generateUniqueGymCentreId(String centreName) {
        // Implement logic to generate a unique gym centre ID based on your requirements
        return centreName + System.currentTimeMillis();
    }

    public List<GymCentre> getCentersSortedByCities() {
        List<GymCentre> allCentreByCity = new ArrayList<>();
        try (Connection conn = DBConnection.connect();
             PreparedStatement stmt = conn.prepareStatement(FETCH_ALL_GYM_CENTRES)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                if (rs.getInt("isApproved") == 1) {
                    allCentreByCity.add(mapResultSetToGymCentre(rs));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        allCentreByCity.sort(Comparator.comparing(GymCentre::getCity));
        return allCentreByCity;
    }
}
