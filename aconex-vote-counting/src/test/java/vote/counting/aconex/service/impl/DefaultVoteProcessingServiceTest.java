package vote.counting.aconex.service.impl;

import static org.junit.Assert.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import vote.counting.aconex.domain.Ballot;
import vote.counting.aconex.domain.Candidate;

public class DefaultVoteProcessingServiceTest {

	private DefaultVoteProcessingService defaultVoteProcessingService = new DefaultVoteProcessingService();
	private ByteArrayOutputStream output;
	private List<String> choices = Arrays.asList("AA", "BB", "CC", "DD");
	
	@Before
	public void setUp() {
		output = new ByteArrayOutputStream();
	    System.setOut(new PrintStream(output));
	}
	
	@After
	public void tearDown() {
		System.setOut(System.out);
	}
	
	@Test
	public void withSamePreferenceThreeVoters() {
		List<Ballot> ballots = Arrays.asList(createBallot(choices.get(0), choices.get(1), choices.get(2)),
				createBallot(choices.get(0), choices.get(1), choices.get(2)), 
				createBallot(choices.get(0), choices.get(1), choices.get(2)));
		defaultVoteProcessingService.processVotes(ballots);
		
		String outContent = output.toString();
		assertTrue(outContent.contains("Quota for selection set to 2"));
		assertTrue(outContent.contains("Candidate 'AA' selected as winner"));
	}
	
	@Test
	public void withOneVoter() {
		List<Ballot> ballots = Arrays.asList(createBallot(choices.get(0), choices.get(1), choices.get(2)));
		defaultVoteProcessingService.processVotes(ballots);
		
		String outContent = output.toString();
		assertTrue(outContent.contains("Round 1 on"));
		assertTrue(outContent.contains("Quota for selection set to 1"));
		assertTrue(outContent.contains("Candidate 'AA' selected as winner"));
	}
	
	@Test
	public void withDifferentPreferencesEightVoters() {
		List<Ballot> ballots = Arrays.asList(createBallot(choices.get(0), choices.get(1), choices.get(2)),
				createBallot(choices.get(2), choices.get(1), choices.get(3)), 
				createBallot(choices.get(1), choices.get(3), choices.get(2), choices.get(0)),
				createBallot(choices.get(2), choices.get(1), choices.get(3)),
				createBallot(choices.get(2), choices.get(0), choices.get(3)),
				createBallot(choices.get(0), choices.get(1)),
				createBallot(choices.get(0), choices.get(1), choices.get(3), choices.get(2)),
				createBallot(choices.get(1), choices.get(3), choices.get(2)));
		defaultVoteProcessingService.processVotes(ballots);
		
		String outContent = output.toString();
		assertTrue(outContent.contains("Round 1 on"));
		assertTrue(outContent.contains("Round 2 on"));
		assertTrue(outContent.contains("Candidate 'DD' eliminated"));
		assertTrue(outContent.contains("Candidate 'BB' eliminated"));
		assertTrue(outContent.contains("Quota for selection set to 5"));
		assertTrue(outContent.contains("Candidate 'CC' selected as winner"));
	}
	
	@Test
	public void withDifferentPreferencesFiveVoters() {
		List<Ballot> ballots = Arrays.asList( 
				createBallot(choices.get(1), choices.get(3), choices.get(2), choices.get(0)),
				createBallot(choices.get(2), choices.get(1), choices.get(3)),
				createBallot(choices.get(0), choices.get(1)),
				createBallot(choices.get(0), choices.get(1), choices.get(3), choices.get(2)),
				createBallot(choices.get(3), choices.get(1), choices.get(2)));
		defaultVoteProcessingService.processVotes(ballots);
		
		String outContent = output.toString();
		assertTrue(outContent.contains("Quota for selection set to 3"));
		assertTrue(outContent.contains("Round 1 on"));
		assertTrue(outContent.contains("Round 2 on"));
		assertTrue(outContent.contains("Round 3 on"));
	}
	
	@Test
	public void quotaChangesIfBallotsAreGreyed() {
		List<Ballot> ballots = Arrays.asList( 
				createBallot(choices.get(1), choices.get(3)),
				createBallot(choices.get(2), choices.get(1)),
				createBallot(choices.get(1)),
				createBallot(choices.get(3)),
				createBallot(choices.get(3)),
				createBallot(choices.get(2)),
				createBallot(choices.get(0)),
				createBallot(choices.get(0), choices.get(1)));
		defaultVoteProcessingService.processVotes(ballots);
		
		String outContent = output.toString();
		assertTrue(outContent.contains("Quota for selection set to 5"));
		assertTrue(outContent.contains("Quota for selection set to 4"));
		assertTrue(outContent.contains("Quota for selection set to 3"));
	}
	
	private Ballot createBallot(String ...prefs) {
		List<Candidate> candidates = new ArrayList<Candidate>();
		for(int i = 0; i < prefs.length; i++) {
			candidates.add(new Candidate(prefs[i], i + 1));
		}
		
		List<String> optedOut = choices.stream()
				.filter(choice -> candidates.stream().noneMatch(can -> can.getName().equals(choice)))
				.collect(Collectors.toList());
		optedOut.forEach(choice -> candidates.add(new Candidate(choice, 0)));
		
		return new Ballot(candidates);
	}
}
