package com.application.voting;

public class Voter {
    private String id;
    private String name;
    private boolean hasVoted;

    public Voter(String id, String name) {
        this.id = id;
        this.name = name;
        this.hasVoted = false;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public boolean hasVoted() {
        return hasVoted;
    }

    public void vote() {
        if (hasVoted) {
            throw new IllegalStateException("Voter has already voted");
        }
        this.hasVoted = true;
    }
}