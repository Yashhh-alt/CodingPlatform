package com.codingplatform;

import javax.swing.*;
import java.awt.*;

public class DashboardFrame {

    JFrame frame = new JFrame("Coding Platform - Dashboard");
    ProblemRepository repository = new ProblemRepository();
    JLabel titleLabel = new JLabel("CODING PLATFORM");
    JLabel searchLabel = new JLabel("Search Problem:");
    JTextField searchField = new JTextField(20);
    JButton searchButton = new JButton("Search");
    JPanel headerPanel = new JPanel(new BorderLayout());
    JPanel searchPanel = new JPanel();
    String[] columns = {"Problem", "Platform", "Difficulty", "Topic"};
    JTable problemTable;

    public DashboardFrame() {

        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));

        headerPanel.add(titleLabel, BorderLayout.CENTER);
        headerPanel.setBackground(new Color(210, 225, 245));

        searchLabel.setFont(new Font("Arial", Font.BOLD, 16));
        searchPanel.add(searchLabel);
        searchPanel.add(searchField);
        searchPanel.add(searchButton);
        searchPanel.setBackground(new Color(230, 235, 245));

        String[][] data = new String[repository.getProblems().size()][4];
        for (int i = 0; i < repository.getProblems().size(); i++) {
            Problem problem = repository.getProblems().get(i);
            data[i][0] = problem.getTitle();
            data[i][1] = problem.getSource();
            data[i][2] = problem.getDifficulty();
            data[i][3] = problem.getTopic();
        }

        problemTable = new JTable(data, columns);
        
        searchButton.addActionListener(e -> {
        	String searchText = searchField.getText().toLowerCase();
        	for (int i = 0; i<repository.getProblems().size(); i++) {
        		Problem problem = repository.getProblems().get(i);
        		
        		if(problem.getTitle().toLowerCase().contains(searchText)) {
        			problemTable.setRowSelectionInterval(i, i);
        			break;
        		}
        	}
        
        });

        problemTable.setRowHeight(32);
        problemTable.getTableHeader().setPreferredSize(new Dimension(0, 35));

        frame.add(headerPanel, BorderLayout.NORTH);
        frame.add(searchPanel, BorderLayout.CENTER);

        JPanel mainPanel = new JPanel(new BorderLayout());

        mainPanel.add(searchPanel, BorderLayout.NORTH);
        mainPanel.add(new JScrollPane(problemTable), BorderLayout.CENTER);

        frame.add(mainPanel, BorderLayout.CENTER);
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}