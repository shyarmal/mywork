package vote.counting.aconex.domain;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;

import org.junit.Test;

public class CandidateTest {

	@Test
	public void shouldGreyOutSuccessfully() {
		Candidate candidate = new Candidate("X", 1);
		
		assertFalse(candidate.isGreyed());
		candidate.greyOutIfInList(Arrays.asList("Y"));
		assertFalse(candidate.isGreyed());
		candidate.greyOutIfInList(Arrays.asList("X"));
		assertTrue(candidate.isGreyed());
	}
}
