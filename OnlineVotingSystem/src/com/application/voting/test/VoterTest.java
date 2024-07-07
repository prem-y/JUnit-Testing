package com.application.voting.test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.application.voting.Voter;

class VoterTest {
    private Voter voter;

    @BeforeEach
    void setUp() {
        voter = new Voter("1", "John Doe");
    }

    @Test
    void testVoterDetails() {
        assertEquals("1", voter.getId());
        assertEquals("John Doe", voter.getName());
        assertFalse(voter.hasVoted());
    }

    @Test
    void testVote() {
        voter.vote();
        assertTrue(voter.hasVoted());
    }

    @Test
    void testVoteTwice() {
        voter.vote();
        Exception exception = assertThrows(IllegalStateException.class, () -> {
            voter.vote();
        });
        assertEquals("Voter has already voted", exception.getMessage());
    }
}
