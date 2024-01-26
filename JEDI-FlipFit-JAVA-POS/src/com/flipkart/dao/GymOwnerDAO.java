package com.flipkart.dao;

import com.flipkart.bean.GymOwner;
import com.flipkart.utils.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static com.flipkart.constants.SQLConstants.*;

public class GymOwnerDAO implements GymOwnerInterfaceDAO {

    List<GymOwner> gymOwnerList = new ArrayList<>();

    public List<GymOwner> getGymOwnerList() {

        try (Connection conn = DBConnection.connect();
             PreparedStatement stmt = conn.prepareStatement(FETCH_ALL_GYM_OWNERS)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                gymOwnerList.add(mapResultSetToGymOwner(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return gymOwnerList;
    }

    public void setGymOwnerList(List<GymOwner> gymOwnerList) {
        this.gymOwnerList = new ArrayList<>(gymOwnerList);
    }

    public boolean loginGymOwner(String username, String password) {
        getGymOwnerList();
        for (GymOwner owner : gymOwnerList) {
            if (username.equals(owner.getUserName()) && password.equals(owner.getPassword())) {
                System.out.println("Login Success\n");
                return true;
            }
        }
        return false;
    }

    public void registerGymOwner(GymOwner gymOwner) {
        try (Connection conn = DBConnection.connect();
             PreparedStatement stmt = conn.prepareStatement(REGISTER_GYM_OWNER)) {
            stmt.setString(1, gymOwner.getUserID());
            stmt.setString(2, gymOwner.getUserName());
            stmt.setString(3, gymOwner.getPassword());
            stmt.setString(4, gymOwner.getEmail());
            stmt.setString(5, gymOwner.getPhoneNumber());
            stmt.setString(6, gymOwner.getCardDetails());

            stmt.executeUpdate();
            System.out.println("Registration Success\n");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<GymOwner> getPendingGymOwnerList() {
        List<GymOwner> pendingList = new ArrayList<>();
        try (Connection conn = DBConnection.connect();
             PreparedStatement stmt = conn.prepareStatement(FETCH_ALL_PENDING_GYM_OWNERS_QUERY)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                pendingList.add(mapResultSetToGymOwner(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pendingList;
    }

    public void sendOwnerApprovalRequest(String gymOwnerId) {
        try (Connection conn = DBConnection.connect();
             PreparedStatement stmt = conn.prepareStatement(SQL_SEND_GYM_OWNER_APPROVAL_REQ_QUERY)) {
            stmt.setString(1, gymOwnerId);
            stmt.executeUpdate();
            System.out.println("Approval Request sent to Admin");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void setPendingGymOwnerList() {

    }

    public void validateGymOwner(String gymOwnerId, int isApproved) {
        try (Connection conn = DBConnection.connect();
             PreparedStatement stmt = conn.prepareStatement(SQL_APPROVE_GYM_OWNER_BY_ID_QUERY)) {
            stmt.setInt(1, isApproved);
            stmt.setString(2, gymOwnerId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean editGymOwner(String gymOwnerId, String username, String email, String cardNumber) {
        try (Connection conn = DBConnection.connect();
             PreparedStatement stmt = conn.prepareStatement(SQL_EDIT_GYM_OWNER)) {
            stmt.setString(1, username);
            stmt.setString(2, email);
            stmt.setString(3, cardNumber);
            stmt.setString(4, gymOwnerId);
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public GymOwner sendProfileInfo(String gymOwnerId) {
        try (Connection conn = DBConnection.connect();
             PreparedStatement stmt = conn.prepareStatement(SQL_GET_GYM_OWNER_BY_ID)) {
            stmt.setString(1, gymOwnerId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return mapResultSetToGymOwner(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return new GymOwner();
    }

    private GymOwner mapResultSetToGymOwner(ResultSet rs) throws SQLException {
        GymOwner gymOwner = new GymOwner();
        gymOwner.setUserID(rs.getString("Id"));
        gymOwner.setUserName(rs.getString("name"));
        gymOwner.setPassword(rs.getString("password"));
        gymOwner.setEmail(rs.getString("email"));
        gymOwner.setPhoneNumber(rs.getString("phoneNumber"));
        gymOwner.setCardDetails(rs.getString("cardDetails"));
        gymOwner.setIsApproved(rs.getInt("isApproved"));
        return gymOwner;
    }
}
