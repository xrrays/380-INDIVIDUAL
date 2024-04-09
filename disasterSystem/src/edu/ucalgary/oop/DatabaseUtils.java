package edu.ucalgary.oop;

import java.sql.*;

public class DatabaseUtils {
    private static final String URL = "jdbc:postgresql://localhost:5432/ensf380project";
    private static final String USERNAME = "oop";
    private static final String PASSWORD = "ucalgary";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USERNAME, PASSWORD);
    }

    public static void addDisasterVictim(String firstName, String lastName, String entryDate) {
        String sql = "INSERT INTO disaster_victims (first_name, last_name, entry_date) VALUES (?, ?, ?)";

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, firstName);
            pstmt.setString(2, lastName);
            pstmt.setDate(3, Date.valueOf(entryDate));
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void logInquiry(Inquirer inquirer, String details) {
        try (Connection conn = getConnection()) {
            // Start transaction
            conn.setAutoCommit(false);
    
            // Check if inquirer exists and get ID
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
    



    public static void searchInquirersByName(String query) {
        String sql = "SELECT * FROM inquirer WHERE firstname LIKE ? OR lastname LIKE ?";

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

    public static void viewInquiryLog() {
        String sql = "SELECT * FROM inquiry_log";

        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                int inquirerId = rs.getInt("inquirer");
                Date callDate = rs.getDate("calldate");
                String details = rs.getString("details");
                System.out.println("Inquiry: Inquirer ID: " + inquirerId + ", Date: " + callDate + ", Details: " + details);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
