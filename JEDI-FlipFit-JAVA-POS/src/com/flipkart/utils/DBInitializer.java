import com.flipkart.utils.DBConnection;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DBInitializer {

    public static void main(String[] args) {
        try (Connection connection = DBConnection.connect()) {
            createTables(connection);
            System.out.println("Database tables created successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void createTables(Connection connection) throws SQLException {
        createGymOwnerTable(connection);
        createGymCentreTable(connection);
        createCustomerTable(connection);
        createSlotTable(connection);
        createScheduleTable(connection);
        createBookingTable(connection);

    }

    private static void createGymOwnerTable(Connection connection) throws SQLException {
        try (Statement statement = connection.createStatement()) {
            statement.execute("CREATE TABLE IF NOT EXISTS FlipFit.GymOwner (" +
                    "Id INT PRIMARY KEY," +
                    "name VARCHAR(255) NOT NULL," +
                    "password VARCHAR(255) NOT NULL," +
                    "email VARCHAR(255) NOT NULL," +
                    "phone VARCHAR(20) NOT NULL," +
                    "cardDetails VARCHAR(16) NOT NULL," +
                    "isApproved INT NOT NULL)");
        }
    }

    private static void createGymCentreTable(Connection connection) throws SQLException {
        try (Statement statement = connection.createStatement()) {
            statement.execute("CREATE TABLE IF NOT EXISTS FlipFit.GymCentre (" +
                    "centreId INT PRIMARY KEY," +
                    "ownerId INT NOT NULL," +
                    "centreName VARCHAR(255) NOT NULL," +
                    "gstin VARCHAR(20) NOT NULL," +
                    "city VARCHAR(255) NOT NULL," +
                    "capacity INT NOT NULL," +
                    "price DOUBLE NOT NULL," +
                    "isApproved INT NOT NULL," +
                    "FOREIGN KEY (ownerId) REFERENCES FlipFit.GymOwner(Id))");
        }
    }

    private static void createCustomerTable(Connection connection) throws SQLException {
        try (Statement statement = connection.createStatement()) {
            statement.execute("CREATE TABLE IF NOT EXISTS FlipFit.Customer (" +
                    "Id INT PRIMARY KEY," +
                    "name VARCHAR(255) NOT NULL," +
                    "password VARCHAR(255) NOT NULL," +
                    "email VARCHAR(255) NOT NULL," +
                    "phone VARCHAR(20) NOT NULL," +
                    "cardDetails VARCHAR(16) NOT NULL)");
        }
    }

    private static void createBookingTable(Connection connection) throws SQLException {
        try (Statement statement = connection.createStatement()) {
            statement.execute("CREATE TABLE IF NOT EXISTS FlipFit.Booking (" +
                    "bookingId INT PRIMARY KEY," +
                    "userID INT NOT NULL," +
                    "scheduleID INT NOT NULL," +
                    "FOREIGN KEY (userID) REFERENCES FlipFit.Customer(Id)," +
                    "FOREIGN KEY (scheduleID) REFERENCES FlipFit.Schedule(scheduleId))");
        }
    }

    private static void createScheduleTable(Connection connection) throws SQLException {
        try (Statement statement = connection.createStatement()) {
            statement.execute("CREATE TABLE IF NOT EXISTS FlipFit.Schedule (" +
                    "scheduleId INT PRIMARY KEY," +
                    "date DATE NOT NULL," +
                    "slotId INT NOT NULL," +
                    "availability INT NOT NULL," +
                    "FOREIGN KEY (slotId) REFERENCES FlipFit.Slot(slotId))");
        }
    }

    private static void createSlotTable(Connection connection) throws SQLException {
        try (Statement statement = connection.createStatement()) {
            statement.execute("CREATE TABLE IF NOT EXISTS FlipFit.Slot (" +
                    "slotId INT PRIMARY KEY," +
                    "centreId INT NOT NULL," +
                    "time TIME NOT NULL," +
                    "FOREIGN KEY (centreId) REFERENCES FlipFit.GymCentre(centreId))");
        }
    }
}
