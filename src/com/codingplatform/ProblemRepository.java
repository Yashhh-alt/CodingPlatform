package com.codingplatform;

import java.util.ArrayList;

public class ProblemRepository {

    private ArrayList<Problem> problems = new ArrayList<>();

    public ProblemRepository() {

        problems.add(new Problem(
            1,
            "Two Sum",
            "LeetCode",
            "Easy",
            "Arrays",
            "Given an array of integers and a target value, return the indices of two numbers that add up to the target."
        ));

        problems.add(new Problem(
            2,
            "Binary Search",
            "LeetCode",
            "Easy",
            "Searching",
            "Given a sorted array, find the position of a target element."
        ));

        problems.add(new Problem(
            3,
            "Maximum Subarray",
            "LeetCode",
            "Medium",
            "Dynamic Programming",
            "Find the contiguous subarray with the largest sum."
        ));

        problems.add(new Problem(
            4,
            "Small Factorials",
            "CodeChef",
            "Easy",
            "Mathematics",
            "Calculate the factorial of a given number."
        ));

        problems.add(new Problem(
            5,
            "Aggressive Cows",
            "CodeChef",
            "Medium",
            "Binary Search",
            "Place cows in stalls so that the minimum distance between any two cows is maximized."
        ));
    }

    public ArrayList<Problem> getProblems() {
        return problems;
    }
}