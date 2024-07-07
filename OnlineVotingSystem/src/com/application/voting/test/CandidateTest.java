package com.application.voting.test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.application.voting.Candidate;

class CandidateTest {
    private Candidate candidate;

    @BeforeEach
    void setUp() {
        candidate = new Candidate("1", "Alice Johnson");
    }

    @Test
    void testCandidateDetails() {
        assertEquals("1", candidate.getId());
        assertEquals("Alice Johnson", candidate.getName());
        assertEquals(0, candidate.getVotes());
    }

    @Test
    void testAddVote() {
        candidate.addVote();
        assertEquals(1, candidate.getVotes());
    }
}
