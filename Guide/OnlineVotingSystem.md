# Online Voting System

### Step 1: Set Up Your Project

1. **Create a new Java Project**:
   - Open Eclipse.
   - Go to `File -> New -> Java Project`.
   - Enter the project name (`OnlineVotingSystem`).

2. **Add JUnit to your Project**:
   - Right-click on your project in the Project Explorer.
   - Select `Build Path -> Add Libraries...`.
   - Choose `JUnit` and click `Next`.
   - Select `JUnit 5` and click `Finish`.

### Step 2: Create Your Online Voting System Classes

1. **Create a package**:
   - Right-click on `src`.
   - Select `New -> Package`.
   - Enter a package name (`com.application.voting`).

2. **Create a `Voter` class**:
   - Right-click on the package you just created.
   - Select `New -> Class`.
   - Enter the class name (`Voter`).

```java
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
```

3. **Create a `Candidate` class**:
   - Right-click on the package you just created.
   - Select `New -> Class`.
   - Enter the class name (`Candidate`).

```java
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
```

4. **Create a `VotingService` class**:
   - Right-click on the package you just created.
   - Select `New -> Class`.
   - Enter the class name (`VotingService`).

```java
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

```

### Step 3: Create Your JUnit Test Classes

1. **Create a test package**:
   - Right-click on `src`.
   - Select `New -> Package`.
   - Enter the test package name (`com.application.voting.test`).

2. **Create a `VoterTest` class**:
   - Right-click on the test package.
   - Select `New -> JUnit Test Case`.
   - Enter the test class name (`VoterTest`).
   - Ensure `JUnit 5` is selected and click `Finish`.

3. **Write JUnit tests for `Voter` class**:

```java
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
```

4. **Create a `CandidateTest` class**:
   - Right-click on the test package.
   - Select `New -> JUnit Test Case`.
   - Enter the test class name (`CandidateTest`).
   - Ensure `JUnit 5` is selected and click `Finish`.

5. **Write JUnit tests for `Candidate` class**:

```java
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
```

6. **Create a `VotingServiceTest` class**:
   - Right-click on the test package.
   - Select `New -> JUnit Test Case`.
   - Enter the test class name (`VotingServiceTest`).
   - Ensure `JUnit 5` is selected and click `Finish`.

7. **Write JUnit tests for `VotingService` class**:

```java
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
```

### Step 4: Run Your Tests

1.

 **Run the tests**:
   - Right-click on the `VoterTest` class.
   - Select `Run As -> JUnit Test`.
   - Repeat the same steps for `CandidateTest` and `VotingServiceTest` classes.

### Explanation

- **Voter Class**: This class represents a voter with properties like ID, name, and a flag indicating whether they have voted. It includes methods to get these properties and to vote.
- **Candidate Class**: This class represents a candidate with properties like ID, name, and the number of votes received. It includes methods to get these properties and to add a vote.
- **VotingService Class**: This class manages voter and candidate records. It includes methods to register voters and candidates, and to handle the voting process.
- **VoterTest Class**: This is your JUnit test class for the `Voter` class. It contains various test methods to verify the behavior of the `Voter` class.
- **CandidateTest Class**: This is your JUnit test class for the `Candidate` class. It contains various test methods to verify the behavior of the `Candidate` class.
- **VotingServiceTest Class**: This is your JUnit test class for the `VotingService` class. It contains various test methods to verify the behavior of the `VotingService` class.
