/**
 * This class provides methods for database operations, establishing a connection
 * to the database and other utility functions.
 * @author Rayyan Ahmed
 * @version 1.9
 * @since 1.6
 */

package edu.ucalgary.oop;

import java.sql.*;

public class DatabaseUtils {
    private static final String URL = "jdbc:postgresql://localhost:5432/ensf380project";
    private static final String USERNAME = "oop";
    private static final String PASSWORD = "ucalgary";
    // The correct username and password for the database is used

    // and the connection is established.
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USERNAME, PASSWORD);
    }

    // These two sequence functions are used to start the ID used in the table at
    // the max ID, avoiding using duplicate ID's when the database is first used.
    public static void setInitialSequenceValues() {
        setSequenceValueForTable("inquirer_id_seq", "INQUIRER");
        setSequenceValueForTable("inquiry_log_id_seq", "INQUIRY_LOG");
    }

    private static void setSequenceValueForTable(String sequenceName, String tableName) {
        String maxIdSql = "SELECT MAX(id) FROM " + tableName;
        String setvalSql = "SELECT setval('" + sequenceName + "', COALESCE((SELECT MAX(id) FROM " + tableName
                + "), 0) + 1, false)";

        try (Connection conn = getConnection();
                Statement maxIdStmt = conn.createStatement();
                PreparedStatement setvalStmt = conn.prepareStatement(setvalSql)) {

            ResultSet rsMaxId = maxIdStmt.executeQuery(maxIdSql);
            if (rsMaxId.next()) {
                try (ResultSet rsSetval = setvalStmt.executeQuery()) {
                    if (rsSetval.next()) {
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // This method is used to log a new inquiry into the database.
    public static void logInquiry(Inquirer inquirer, String details) {
        try (Connection conn = getConnection()) {
            // Start transaction
            conn.setAutoCommit(false);

            // Check if inquirer exists and get ID, avoid creating a new object
            int inquirerId = getInquirerIdOrCreate(conn, inquirer);

            // Insert into inquiry log
            String sqlInquiryLog = "INSERT INTO INQUIRY_LOG (inquirer, calldate, details) VALUES (?, CURRENT_DATE, ?)";
            try (PreparedStatement pstmtInquiryLog = conn.prepareStatement(sqlInquiryLog)) {
                pstmtInquiryLog.setInt(1, inquirerId);
                pstmtInquiryLog.setString(2, details);
                pstmtInquiryLog.executeUpdate();
            }

            // Commit transaction
            conn.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // This method is used to check if the new inquirer is a new inquirer or an old
    // one.
    // We want to be avoid creating a new inquirer in the table if it is the same
    // person.
    private static int getInquirerIdOrCreate(Connection conn, Inquirer inquirer) throws SQLException {
        String checkSql = "SELECT id FROM INQUIRER WHERE firstName = ? AND lastName = ? AND phoneNumber = ?";
        try (PreparedStatement checkStmt = conn.prepareStatement(checkSql)) {
            checkStmt.setString(1, inquirer.getFirstName());
            checkStmt.setString(2, inquirer.getLastName());
            checkStmt.setString(3, inquirer.getServicesPhone());
            ResultSet rs = checkStmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("id");
            }
        }

        String insertSql = "INSERT INTO INQUIRER (firstName, lastName, phoneNumber) VALUES (?, ?, ?) RETURNING id";
        try (PreparedStatement insertStmt = conn.prepareStatement(insertSql)) {
            insertStmt.setString(1, inquirer.getFirstName());
            insertStmt.setString(2, inquirer.getLastName());
            insertStmt.setString(3, inquirer.getServicesPhone());
            ResultSet rs = insertStmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("id");
            } else {
                throw new SQLException("Creating inquirer failed, no ID obtained.");
            }
        }
    }

    // This method is used to search the inquirer table and return their
    // information.
    // It is also case insensitive and meets the requirements specified in the
    // assignment.
    public static void searchInquirersByName(String query) {
        String sql = "SELECT * FROM inquirer WHERE firstname ILIKE ? OR lastname ILIKE ?";

        try (Connection conn = getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, "%" + query + "%");
            pstmt.setString(2, "%" + query + "%");
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                String firstName = rs.getString("firstname");
                String lastName = rs.getString("lastname");
                String phoneNumber = rs.getString("phonenumber");
                System.out.println("Inquirer: " + firstName + " " + lastName + ", Phone: " + phoneNumber);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // This method is used to view the inquirer table from the database.
    public static void viewInquirers() {
        String sql = "SELECT * FROM inquirer";

        try (Connection conn = getConnection();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                String firstName = rs.getString("firstname");
                String lastName = rs.getString("lastname");
                String phoneNumber = rs.getString("phonenumber");
                System.out.println("Inquirer: " + firstName + " " + lastName + ", Phone: " + phoneNumber);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // This method is used to viuew the inquiry log from the database.
    public static void viewInquiryLog() {
        String sql = "SELECT * FROM inquiry_log";

        try (Connection conn = getConnection();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                int inquirerId = rs.getInt("inquirer");
                Date callDate = rs.getDate("calldate");
                String details = rs.getString("details");
                System.out.println(
                        "Inquiry: Inquirer ID: " + inquirerId + ", Date: " + callDate + ", Details: " + details);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
