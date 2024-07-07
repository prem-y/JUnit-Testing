package com.application.voting.test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.application.voting.Voter;
import com.application.voting.Candidate;
import com.application.voting.VotingService;

class VotingServiceTest {
    private VotingService votingService;
    private Voter voter1;
    private Voter voter2;
    private Candidate candidate1;
    private Candidate candidate2;

    @BeforeEach
    void setUp() {
        votingService = new VotingService();
        voter1 = new Voter("1", "John Doe");
        voter2 = new Voter("2", "Jane Smith");
        candidate1 = new Candidate("1", "Alice Johnson");
        candidate2 = new Candidate("2", "Bob Brown");
        votingService.registerVoter(voter1);
        votingService.registerVoter(voter2);
        votingService.registerCandidate(candidate1);
        votingService.registerCandidate(candidate2);
    }

    @Test
    void testRegisterVoter() {
        Voter voter3 = new Voter("3", "Charlie White");
        votingService.registerVoter(voter3);
        assertEquals(voter3, votingService.getVoterById("3"));
    }

    @Test
    void testRegisterCandidate() {
        Candidate candidate3 = new Candidate("3", "David Black");
        votingService.registerCandidate(candidate3);
        assertEquals(candidate3, votingService.getCandidate("3"));
    }

    @Test
    void testVote() {
        votingService.vote("1", "1");
        assertTrue(votingService.getVoterById("1").hasVoted());
        assertEquals(1, votingService.getTotalVotesForCandidate("1"));
    }

    @Test
    void testVoteForNonexistentVoter() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            votingService.vote("3", "1");
        });
        assertEquals("Voter not found", exception.getMessage());
    }

    @Test
    void testVoteForNonexistentCandidate() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            votingService.vote("1", "3");
        });
        assertEquals("Candidate not found", exception.getMessage());
    }

    @Test
    void testGetTotalVotesForCandidate() {
        votingService.vote("1", "1");
        votingService.vote("2", "1");
        assertEquals(2, votingService.getTotalVotesForCandidate("1"));
    }
}