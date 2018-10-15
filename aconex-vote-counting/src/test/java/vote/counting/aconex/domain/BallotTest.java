package vote.counting.aconex.domain;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class BallotTest {

	@Test
	public void shouldBeGreyedOutIfAllCandidatesAreGreyed() {
		List<Candidate> candidates = Arrays.asList(new Candidate("XX", 0), new Candidate("YY", 0), new Candidate("ZZ", 0));
		Ballot ballot = new Ballot(candidates);
		
		assertTrue(ballot.isGreyed());
	}
	
	@Test
	public void shouldNotBeGreyedOutIfAllCandidatesAreNotGreyed() {
		List<Candidate> candidates = Arrays.asList(new Candidate("XX", 0), new Candidate("YY", 1), new Candidate("ZZ", 0));
		Ballot ballot = new Ballot(candidates);
		
		assertFalse(ballot.isGreyed());
	}
	
	@Test
	public void shouldReturnTheMostPreferedCandidate() {
		List<Candidate> candidates = Arrays.asList(new Candidate("XX", 1), new Candidate("YY", 0), new Candidate("ZZ", 2));
		Ballot ballot = new Ballot(candidates);
		
		assertEquals(ballot.belongsToCandidateName(), "XX");
	}
	
	@Test
	public void shouldNotReturnTheMostPreferedCandidateIfGreyed() {
		Candidate candidate1 = new Candidate("XX", 1);
		candidate1.greyOutIfInList(Arrays.asList("XX"));
		List<Candidate> candidates = Arrays.asList(candidate1, new Candidate("YY", 0), new Candidate("ZZ", 2));
		Ballot ballot = new Ballot(candidates);
		
		assertEquals(ballot.belongsToCandidateName(), "ZZ");
	}
	
	@Test
	public void shouldGreyOutCandidatesSuccussfully() {
		List<Candidate> candidates = Arrays.asList(new Candidate("XX", 1), new Candidate("YY", 0), new Candidate("ZZ", 2));
		Ballot ballot = new Ballot(candidates);
		ballot.greyOutCandidates(Arrays.asList("XX", "YY"));
		
		assertTrue(ballot.getCandidateVotes().stream().filter(cand -> cand.getName().equals("XX")).findAny().get().isGreyed());
		assertTrue(ballot.getCandidateVotes().stream().filter(cand -> cand.getName().equals("YY")).findAny().get().isGreyed());
		assertFalse(ballot.getCandidateVotes().stream().filter(cand -> cand.getName().equals("ZZ")).findAny().get().isGreyed());
		
	}
}
