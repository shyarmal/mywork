package vote.counting.aconex.service.translator;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.junit.Before;
import org.junit.Test;

import vote.counting.aconex.domain.Ballot;
import vote.counting.aconex.domain.Candidate;

public class InputTranslatorTest {
	
	private InputTranslator inputTranslator = new InputTranslator();
	private Map<String, String> candidates;
	
	@Before
	public void setUp() {
		candidates = new HashMap<>();
		candidates.put("A", "Winery tour");
		candidates.put("B", "Movie night");
		candidates.put("C", "Surfing lession");
	}
	
	@Test
	public void shouldTranslateValidInputs() {
		List<String> votes = Arrays.asList("ABC", "B C", "C", "C A", "B A");
		List<Ballot> ballots = inputTranslator.translate(votes, candidates);
		
		assertThat(ballots.size(), is(5));
		List<Candidate> allBallotEntries = ballots.stream()
				.flatMap(bal -> bal.getCandidateVotes().stream()).collect(Collectors.toList());
		
		assertThat(filterCandidate(allBallotEntries, "Winery tour", 2), is(2));
		assertThat(filterCandidate(allBallotEntries, "Winery tour", 1), is(1));
		assertThat(filterCandidate(allBallotEntries, "Winery tour", 3), is(0));
		assertThat(filterCandidate(allBallotEntries, "Movie night", 2), is(1));
		assertThat(filterCandidate(allBallotEntries, "Movie night", 1), is(2));
		assertThat(filterCandidate(allBallotEntries, "Surfing lession", 1), is(2));
		assertThat(filterCandidate(allBallotEntries, "Surfing lession", 2), is(1));
	}
	
	@Test
	public void shouldIgnoreInvalidInputs() {
		List<String> votes = Arrays.asList("47", "", "xxx", "A", "B A");
		List<Ballot> ballots = inputTranslator.translate(votes, candidates);
		
		assertThat(ballots.size(), is(2));
		List<Candidate> allBallotEntries = ballots.stream()
				.flatMap(bal -> bal.getCandidateVotes().stream()).collect(Collectors.toList());
		
		assertThat(filterCandidate(allBallotEntries, "Winery tour", 1), is(1));
		assertThat(filterCandidate(allBallotEntries, "Winery tour", 2), is(1));
		assertThat(filterCandidate(allBallotEntries, "Movie night", 1), is(1));
		assertThat(filterCandidate(allBallotEntries, "Movie night", 2), is(0));
	}
	
	private int filterCandidate(List<Candidate> candidate, final String name, final int preference) {
		return (int) candidate.stream()
				.filter(cand -> cand.getName().equals(name) && cand.getPreference() == preference)
				.count();
	}

}
