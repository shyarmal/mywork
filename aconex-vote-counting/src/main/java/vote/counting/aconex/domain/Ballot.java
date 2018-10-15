package vote.counting.aconex.domain;

import static java.util.Objects.nonNull;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class Ballot {

	private final List<Candidate> candidateVotes = new ArrayList<>();
	
	public Ballot(List<Candidate> candidateList) {
		if(nonNull(candidateList)) {
			candidateVotes.addAll(candidateList);
		}
	}
	
	public List<Candidate> getCandidateVotes() {
		return candidateVotes;
	}
	
	public boolean isGreyed() {
//		candidateVotes.forEach(c -> System.err.print(c.isGreyed() + " "));
//		System.err.println();
		return candidateVotes.stream().allMatch(candidate -> candidate.isGreyed());
	}
	
	public String belongsToCandidateName() {
		Optional<Candidate> optionalCandidate = candidateVotes.stream()
				.filter(candidate -> !candidate.isGreyed())
				.filter(candidate -> candidate.getPreference() > 0)
				.min(Comparator.comparing(Candidate::getPreference));
		return optionalCandidate.isPresent()? optionalCandidate.get().getName() : "";
	}

	public void greyOutCandidates(List<String> eliminations) {
		candidateVotes.forEach(cand -> cand.greyOutIfInList(eliminations));
	}
}
