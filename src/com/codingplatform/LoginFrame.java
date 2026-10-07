package com.codingplatform;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginFrame {

    JFrame frame = new JFrame("Coding Platform - Login");
    JPanel panel = new JPanel(new GridLayout(3, 2, 10, 10));
    JPanel container = new JPanel();

    JLabel usernamelabel = new JLabel("USERNAME:");
    JTextField usernamefield = new JTextField();

    JLabel passwordlabel = new JLabel("PASSWORD:");
    JPasswordField passwordfield = new JPasswordField();

    JButton login = new JButton("Login");

    public LoginFrame() {

        panel.add(usernamelabel);
        panel.add(usernamefield);

        panel.add(passwordlabel);
        panel.add(passwordfield);

        panel.add(new JLabel());
        panel.add(login);

        container.add(panel);
        frame.add(container);

        frame.setSize(400, 250);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        login.addActionListener(e -> handleLogin());
    }

    private void handleLogin() {
        String username = usernamefield.getText().trim();
        String password = new String(passwordfield.getPassword()).trim();

        if (username.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "Please enter both Username and Password.", "Validation Error", JOptionPane.WARNING_MESSAGE);
            return;
        }

        String sql = "SELECT * FROM users WHERE username = ? AND password = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, username);
            stmt.setString(2, password);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    JOptionPane.showMessageDialog(frame, "Login Successful! Welcome, " + username + ".", "Success", JOptionPane.INFORMATION_MESSAGE);
                    new DashboardFrame();
                    frame.dispose();
                } else {
                    JOptionPane.showMessageDialog(frame, "Invalid Username or Password.", "Login Failed", JOptionPane.ERROR_MESSAGE);
                }
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(frame, "Database Connection Error: " + ex.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
    }
}
