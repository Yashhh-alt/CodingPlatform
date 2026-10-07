package com.codingplatform;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProblemRepository {

    private ArrayList<Problem> problems = new ArrayList<>();

    public ProblemRepository() {
        loadProblemsFromDatabase();
    }

    private void loadProblemsFromDatabase() {
        String sql = "SELECT id, title, source, difficulty, topic, description FROM problems";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Problem problem = new Problem(
                    rs.getInt("id"),
                    rs.getString("title"),
                    rs.getString("source"),
                    rs.getString("difficulty"),
                    rs.getString("topic"),
                    rs.getString("description")
                );
                problems.add(problem);
            }
        } catch (SQLException e) {
            System.err.println("Database error while loading problems: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public ArrayList<Problem> getProblems() {
        return problems;
    }
}