package com.codingplatform;

public class Problem {

    private int id;
    private String title;
    private String source;
    private String difficulty;
    private String topic;
    private String description;

    public Problem(int id, String title, String source,
                   String difficulty, String topic,
                   String description) {

        this.id = id;
        this.title = title;
        this.source = source;
        this.difficulty = difficulty;
        this.topic = topic;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getSource() {
        return source;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public String getTopic() {
        return topic;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return title + " | " + source + " | "
             + difficulty + " | " + topic;
    }
}