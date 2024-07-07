package com.application.voting;

public class Candidate {
    private String id;
    private String name;
    private int votes;

    public Candidate(String id, String name) {
        this.id = id;
        this.name = name;
        this.votes = 0;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getVotes() {
        return votes;
    }

    public void addVote() {
        this.votes++;
    }
}
