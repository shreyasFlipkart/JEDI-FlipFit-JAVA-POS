package com.flipkart.dao;

import com.flipkart.bean.Schedule;
import com.flipkart.utils.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static com.flipkart.constants.SQLConstants.*;

public class ScheduleDAO implements ScheduleInterfaceDAO {

    public void addSchedule(Schedule schedule) {
        try (Connection conn = DBConnection.connect();
             PreparedStatement stmt = conn.prepareStatement(ADD_SCHEDULE)) {
            stmt.setString(1, schedule.getScheduleID());
            stmt.setDate(2, schedule.getDate());
            stmt.setString(3, schedule.getSlotID());
            stmt.setInt(4, schedule.getAvailability());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Schedule getSchedule(String scheduleId) {
        try (Connection conn = DBConnection.connect();
             PreparedStatement stmt = conn.prepareStatement(GET_SCHEDULE_BY_ID)) {
            stmt.setString(1, scheduleId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return mapResultSetToSchedule(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Schedule> getAllScheduleByDate(Date date) {
        List<Schedule> response = new ArrayList<>();
        try (Connection conn = DBConnection.connect();
             PreparedStatement stmt = conn.prepareStatement(GET_SCHEDULES_BY_DATE)) {
            stmt.setDate(1, date);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                response.add(mapResultSetToSchedule(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return response;
    }

    public boolean modifySchedule(String scheduleId, int action) {
        try (Connection conn = DBConnection.connect();
             PreparedStatement stmt = conn.prepareStatement(MODIFY_SCHEDULE_AVAILABILITY)) {
            Schedule schedule = getSchedule(scheduleId);
            if (schedule == null) {
                return false;
            }
            int availability = schedule.getAvailability();
            if (availability < 1 && action == -1) {
                return false;
            }
            // Update the availability
            stmt.setInt(1, availability + action);
            stmt.setString(2, scheduleId);
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    private Schedule mapResultSetToSchedule(ResultSet rs) throws SQLException {
        Schedule schedule = new Schedule();
        schedule.setScheduleID(rs.getString("scheduleId"));
        schedule.setDate(rs.getDate("date"));
        schedule.setSlotID(rs.getString("slotId"));
        schedule.setAvailability(rs.getInt("availability"));
        return schedule;
    }
}
