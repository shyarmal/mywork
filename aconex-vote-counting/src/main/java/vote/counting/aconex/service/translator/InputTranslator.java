package vote.counting.aconex.service.translator;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import vote.counting.aconex.domain.Ballot;
import vote.counting.aconex.domain.Candidate;

public class InputTranslator {

	public List<Ballot> translate(List<String> votes, Map<String, String> candidates) {
		return votes.stream().map(vote -> createBallot(vote, candidates))
				.filter(bal -> !bal.getCandidateVotes().isEmpty())
				.collect(Collectors.toList());
	}

	private Ballot createBallot(String vote, Map<String, String> candidates) {
		List<String> preferences = vote.chars().mapToObj(i -> Character.valueOf((char) i))
				.filter(ch -> !Character.isWhitespace(ch)).map(ch -> ch.toString()).collect(Collectors.toList());
		
		List<Candidate> candidateList = new ArrayList<>();
		createBallotEntries(candidates, preferences, candidateList);
		
		return new Ballot(candidateList);
	}

	private void createBallotEntries(Map<String, String> candidates, List<String> preferences,
			List<Candidate> candidateList) {
		
		if(!preferences.isEmpty() && 
				preferences.stream().allMatch(pref -> candidates.keySet().contains(pref))) {
			
			for(int i = 0; i < preferences.size(); i++) {
				Candidate candidate = new Candidate(candidates.get(preferences.get(i)), i + 1);
				candidateList.add(candidate);
			}
			
			List<String> optedOutPrefs = candidates.entrySet().stream().filter(entry -> !preferences.contains(entry.getKey()))
					.map(entry -> entry.getValue()).collect(Collectors.toList());
			
			optedOutPrefs.forEach(pref -> candidateList.add(new Candidate(pref, 0)));
		}
	}
	
}
