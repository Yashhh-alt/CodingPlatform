package com.codingplatform;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SignUpFrame {

    private JFrame frame = new JFrame("Coding Platform - Sign Up");
    private JPanel panel = new JPanel(new GridLayout(4, 2, 10, 10));
    private JPanel container = new JPanel();

    private JLabel usernamelabel = new JLabel("USERNAME:");
    private JTextField usernamefield = new JTextField(15);

    private JLabel passwordlabel = new JLabel("PASSWORD:");
    private JPasswordField passwordfield = new JPasswordField(15);

    private JLabel confirmPasswordLabel = new JLabel("CONFIRM PASSWORD:");
    private JPasswordField confirmPasswordField = new JPasswordField(15);

    private JButton registerButton = new JButton("Register");
    private JButton backButton = new JButton("Back to Login");

    public SignUpFrame() {
        panel.add(usernamelabel);
        panel.add(usernamefield);

        panel.add(passwordlabel);
        panel.add(passwordfield);

        panel.add(confirmPasswordLabel);
        panel.add(confirmPasswordField);

        panel.add(backButton);
        panel.add(registerButton);

        container.add(panel);
        frame.add(container);

        frame.setSize(450, 260);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        registerButton.addActionListener(e -> handleSignUp());
        backButton.addActionListener(e -> {
            new LoginFrame();
            frame.dispose();
        });
    }

    private void handleSignUp() {
        String username = usernamefield.getText().trim();
        String password = new String(passwordfield.getPassword()).trim();
        String confirmPassword = new String(confirmPasswordField.getPassword()).trim();

        if (username.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "All fields are required!", "Validation Error", JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (username.length() < 3) {
            JOptionPane.showMessageDialog(frame, "Username must be at least 3 characters long.", "Validation Error", JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (password.length() < 4) {
            JOptionPane.showMessageDialog(frame, "Password must be at least 4 characters long.", "Validation Error", JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (!password.equals(confirmPassword)) {
            JOptionPane.showMessageDialog(frame, "Passwords do not match!", "Validation Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Check if username already exists in MySQL
        String checkSql = "SELECT id FROM users WHERE username = ?";
        String insertSql = "INSERT INTO users (username, password) VALUES (?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement checkStmt = conn.prepareStatement(checkSql)) {

            checkStmt.setString(1, username);
            try (ResultSet rs = checkStmt.executeQuery()) {
                if (rs.next()) {
                    JOptionPane.showMessageDialog(frame, "Username '" + username + "' is already taken. Please choose another.", "Registration Failed", JOptionPane.WARNING_MESSAGE);
                    return;
                }
            }

            // Insert new user into MySQL database
            try (PreparedStatement insertStmt = conn.prepareStatement(insertSql)) {
                insertStmt.setString(1, username);
                insertStmt.setString(2, password);
                int rowsInserted = insertStmt.executeUpdate();

                if (rowsInserted > 0) {
                    JOptionPane.showMessageDialog(frame, "Account created successfully! Please login.", "Success", JOptionPane.INFORMATION_MESSAGE);
                    new LoginFrame(username);
                    frame.dispose();
                }
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(frame, "Database Error: " + ex.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
    }
}
