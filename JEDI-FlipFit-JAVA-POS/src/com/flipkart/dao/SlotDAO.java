package com.flipkart.dao;

import com.flipkart.bean.Slot;
import com.flipkart.utils.DBUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static com.flipkart.constants.SQLConstants.*;

public class SlotDAO implements SlotInterfaceDAO {

    public List<Slot> getSlotList() {
        List<Slot> slotList = new ArrayList<>();
        try (Connection conn = DBUtils.connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(FETCH_ALL_SLOTS)) {

            while (rs.next()) {
                Slot slot = mapResultSetToSlot(rs);
                slotList.add(slot);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());;
        }
        return slotList;
    }

    public List<Slot> getSlotByCentreId(String gymCentreId) {
        List<Slot> filteredSlots = new ArrayList<>();
        try (Connection conn = DBUtils.connect();
             PreparedStatement stmt = conn.prepareStatement(FETCH_SLOT_BY_CENTRE)) {
            stmt.setString(1, gymCentreId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Slot slot = mapResultSetToSlot(rs);
                filteredSlots.add(slot);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return filteredSlots;
    }

    public void addSlot(Slot slot) {
        try (Connection conn = DBUtils.connect();
             PreparedStatement stmt = conn.prepareStatement(ADD_SLOT)) {
            stmt.setString(1, slot.getSlotId());
            stmt.setString(2, slot.getCentreID());
            stmt.setTime(3, Time.valueOf(slot.getTime()));

            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public Slot getSlotById(String slotID) {
        try (Connection conn = DBUtils.connect();
             PreparedStatement stmt = conn.prepareStatement(FETCH_SLOT_BY_ID)) {
            stmt.setString(1, slotID);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return mapResultSetToSlot(rs);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public Slot getSlotByIdandCentreId(String slotID, String centreID) {
        try (Connection conn = DBUtils.connect();
             PreparedStatement stmt = conn.prepareStatement(FETCH_SLOT_BY_ID_AND_CENTRE_ID)) {
            stmt.setString(1, slotID);
            stmt.setString(2, centreID);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return mapResultSetToSlot(rs);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    private Slot mapResultSetToSlot(ResultSet rs) throws SQLException {
        Slot slot = new Slot();
        slot.setSlotId(rs.getString("slotId"));
        slot.setCentreID(rs.getString("centreId"));
        slot.setTime(rs.getTime("time").toLocalTime());
        return slot;
    }

    public boolean deleteSlotById(String slotId) {
        try (Connection conn = DBUtils.connect();
             PreparedStatement stmt = conn.prepareStatement(FETCH_SLOT_BY_ID)) {
            stmt.setString(1, slotId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                try (PreparedStatement deleteStmt = conn.prepareStatement(DELETE_SLOT)) {
                    deleteStmt.setString(1, slotId);
                    deleteStmt.executeUpdate();
                    return true;
                }
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }
}
