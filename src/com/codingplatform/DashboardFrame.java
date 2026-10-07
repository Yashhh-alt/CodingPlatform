package com.codingplatform;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.util.ArrayList;

public class DashboardFrame {

    private JFrame frame = new JFrame("Coding Platform - Dashboard");
    private ProblemRepository repository = new ProblemRepository();
    private JLabel titleLabel = new JLabel("CODING PLATFORM");
    private JLabel searchLabel = new JLabel("Search Problem:");
    private JTextField searchField = new JTextField(20);
    private JButton searchButton = new JButton("Search");
    private JButton resetButton = new JButton("Reset");
    private JPanel headerPanel = new JPanel(new BorderLayout());
    private JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
    private String[] columns = {"ID", "Problem", "Platform", "Difficulty", "Topic", "Description"};
    private JTable problemTable;
    private DefaultTableModel tableModel;
    private TableRowSorter<DefaultTableModel> sorter;

    public DashboardFrame() {
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(15, 0, 15, 0));

        headerPanel.add(titleLabel, BorderLayout.CENTER);
        headerPanel.setBackground(new Color(210, 225, 245));

        searchLabel.setFont(new Font("Arial", Font.BOLD, 14));
        searchPanel.add(searchLabel);
        searchPanel.add(searchField);
        searchPanel.add(searchButton);
        searchPanel.add(resetButton);
        searchPanel.setBackground(new Color(235, 240, 250));

        tableModel = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        loadTableData();

        problemTable = new JTable(tableModel);
        sorter = new TableRowSorter<>(tableModel);
        problemTable.setRowSorter(sorter);

        problemTable.setRowHeight(30);
        problemTable.getTableHeader().setFont(new Font("Arial", Font.BOLD, 13));
        problemTable.getTableHeader().setPreferredSize(new Dimension(0, 35));
        problemTable.getColumnModel().getColumn(0).setPreferredWidth(40);
        problemTable.getColumnModel().getColumn(1).setPreferredWidth(160);
        problemTable.getColumnModel().getColumn(2).setPreferredWidth(100);
        problemTable.getColumnModel().getColumn(3).setPreferredWidth(90);
        problemTable.getColumnModel().getColumn(4).setPreferredWidth(140);
        problemTable.getColumnModel().getColumn(5).setPreferredWidth(280);

        searchButton.addActionListener(e -> applyFilter());
        searchField.addActionListener(e -> applyFilter());
        resetButton.addActionListener(e -> {
            searchField.setText("");
            sorter.setRowFilter(null);
        });

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(searchPanel, BorderLayout.NORTH);
        mainPanel.add(new JScrollPane(problemTable), BorderLayout.CENTER);

        frame.setLayout(new BorderLayout());
        frame.add(headerPanel, BorderLayout.NORTH);
        frame.add(mainPanel, BorderLayout.CENTER);

        frame.setSize(900, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private void loadTableData() {
        tableModel.setRowCount(0);
        ArrayList<Problem> problems = repository.getProblems();
        for (Problem problem : problems) {
            tableModel.addRow(new Object[]{
                problem.getId(),
                problem.getTitle(),
                problem.getSource(),
                problem.getDifficulty(),
                problem.getTopic(),
                problem.getDescription()
            });
        }
    }

    private void applyFilter() {
        String text = searchField.getText().trim();
        if (text.isEmpty()) {
            sorter.setRowFilter(null);
        } else {
            sorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
        }
    }
}