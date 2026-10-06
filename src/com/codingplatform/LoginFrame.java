package com.codingplatform;

import javax.swing.*;
import java.awt.*;

public class LoginFrame {
	
	JFrame frame = new JFrame("Coding Platform");
	JPanel panel = new JPanel(new GridLayout(3,2,10,10));
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
		
		login.addActionListener(e -> {
		    new DashboardFrame();
		    frame.dispose();
		});
	}
	

}
