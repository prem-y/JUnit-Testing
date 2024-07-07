package com.application.voting;

import java.util.HashMap;
import java.util.Map;


public class VotingService {
    private Map<String, Voter> voters;
    private Map<String, Candidate> candidates;

    public VotingService() {
        voters = new HashMap<>();
        candidates = new HashMap<>();
    }

    public void registerVoter(Voter voter) {
        voters.put(voter.getId(), voter);
    }

    public void registerCandidate(Candidate candidate) {
        candidates.put(candidate.getId(), candidate);
    }

    public void vote(String voterId, String candidateId) {
        Voter voter = voters.get(voterId);
        Candidate candidate = candidates.get(candidateId);
        if (voter == null) {
            throw new IllegalArgumentException("Voter not found");
        }
        if (candidate == null) {
            throw new IllegalArgumentException("Candidate not found");
        }
        voter.vote();
        candidate.addVote();
    }

    public Voter getVoterById(String voterId) {
        return voters.get(voterId);
    }

    public Candidate getCandidate(String candidateId) {
        return candidates.get(candidateId);
    }

    public int getTotalVotesForCandidate(String candidateId) {
        Candidate candidate = candidates.get(candidateId);
        if (candidate == null) {
            throw new IllegalArgumentException("Candidate not found");
        }
        return candidate.getVotes();
    }
}
