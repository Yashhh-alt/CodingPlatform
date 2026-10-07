package com.codingplatform;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginFrame {

    private JFrame frame = new JFrame("Coding Platform - Login");
    private JPanel panel = new JPanel(new GridLayout(3, 2, 10, 10));
    private JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0));
    private JPanel container = new JPanel();

    private JLabel usernamelabel = new JLabel("USERNAME:");
    private JTextField usernamefield = new JTextField(15);

    private JLabel passwordlabel = new JLabel("PASSWORD:");
    private JPasswordField passwordfield = new JPasswordField(15);

    private JButton login = new JButton("Login");
    private JButton signup = new JButton("Sign Up");

    public LoginFrame() {
        this("");
    }

    public LoginFrame(String initialUsername) {
        if (initialUsername != null && !initialUsername.isEmpty()) {
            usernamefield.setText(initialUsername);
        }

        panel.add(usernamelabel);
        panel.add(usernamefield);

        panel.add(passwordlabel);
        panel.add(passwordfield);

        buttonPanel.add(signup);
        buttonPanel.add(login);

        panel.add(new JLabel());
        panel.add(buttonPanel);

        container.add(panel);
        frame.add(container);

        frame.setSize(420, 240);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        login.addActionListener(e -> handleLogin());
        signup.addActionListener(e -> {
            new SignUpFrame();
            frame.dispose();
        });
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
