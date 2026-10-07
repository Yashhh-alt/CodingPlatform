package com.codingplatform;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    private static final String URL = "jdbc:mysql://localhost:3306/coding_platform?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PASSWORD = "root";

    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.err.println("MySQL JDBC Driver not found: " + e.getMessage());
        }
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public static void main(String[] args) {
        try (Connection conn = getConnection()) {
            System.out.println("Connection to MySQL 'coding_platform' successful!");
            ProblemRepository repo = new ProblemRepository();
            System.out.println("Loaded " + repo.getProblems().size() + " problems from MySQL database:");
            for (Problem p : repo.getProblems()) {
                System.out.println(" - " + p.getTitle() + " (" + p.getSource() + ", " + p.getDifficulty() + ")");
            }
        } catch (Exception e) {
            System.err.println("Connection failed: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
