package vote.counting.aconex.service.impl;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.stream.Collectors;

import vote.counting.aconex.domain.Ballot;
import vote.counting.aconex.service.VoteProcessingService;

public class DefaultVoteProcessingService implements VoteProcessingService {

	private static final String QUOTA_MESSAGE = "Quota for selection set to %s";
	private static final String WINNER_MESSAGE = "Candidate '%s' selected as winner";
	private static final String ELEMENATE_MESSAGE = "Candidate '%s' eliminated";
	private static final String ROUND_MESSAGE = "Round %d on";
	private static final String EMPTY_MESSAGE = "No votes to candidates";
	
	@Override
	public void processVotes(List<Ballot> ballots) {
		processVotes(ballots, 1);	
	}
	
	private void processVotes(List<Ballot> ballots, int roundNumber) {
		System.out.println(String.format(ROUND_MESSAGE, roundNumber));
		if(ballots.isEmpty()) {
			System.out.println(EMPTY_MESSAGE);
			return;
		}
		long validBallotCount = ballots.stream().filter(ballot -> !ballot.isGreyed()).count();
		int quota = (int) (validBallotCount/2 + 1);
		System.out.println(String.format(QUOTA_MESSAGE, quota));
		
		List<String> candidateNames = ballots.stream().findAny().get()
				.getCandidateVotes().stream().map(can -> can.getName()).collect(Collectors.toList());
		
		Map<String, List<Ballot>> roundGrouping = groupBallotsByCandidates(ballots);
		
		if(!pickWinnerIfPossible(quota, roundGrouping)) {		
			eliminateCandidates(ballots, candidateNames, roundGrouping);		
			processVotes(ballots, ++roundNumber);
		}

	}

	private boolean pickWinnerIfPossible(int quota, Map<String, List<Ballot>> roundGrouping) {
		if (roundGrouping.size() == 1) {
			System.out.println(String.format(WINNER_MESSAGE, roundGrouping.keySet().stream().findFirst().get()));
			return true;
		}
		Optional<Entry<String, List<Ballot>>> potentialWinner = roundGrouping.entrySet().stream()
				.filter(entry -> entry.getValue().size() >= quota).findFirst();
		if(potentialWinner.isPresent()) {
			System.out.println(String.format(WINNER_MESSAGE, potentialWinner.get().getKey()));
			return true;
		}
		
		return false;
	}

	private void eliminateCandidates(List<Ballot> ballots, List<String> candidateNames,
			Map<String, List<Ballot>> roundGrouping) {
		Comparator<Entry<String, List<Ballot>>> listSizeComparator = 
				(a, b) -> Integer.valueOf(a.getValue().size()).compareTo(b.getValue().size());
		String candidateWithMinimumVotes = roundGrouping.entrySet().stream().min(listSizeComparator).get().getKey();
		
		List<String> eliminations = candidateNames.stream()
				.filter(cand -> !roundGrouping.keySet().contains(cand)).collect(Collectors.toList());
		eliminations.add(candidateWithMinimumVotes);
		
		ballots.forEach(bal -> bal.greyOutCandidates(eliminations));
		eliminations.forEach(elim -> System.out.println(String.format(ELEMENATE_MESSAGE, elim)));
	}

	private Map<String, List<Ballot>> groupBallotsByCandidates(List<Ballot> ballots) {
		return ballots.stream().filter(ballot -> !ballot.isGreyed())
				.collect(Collectors.groupingBy(Ballot::belongsToCandidateName))
				.entrySet().stream().filter(entry -> !entry.getKey().isEmpty())
				.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
	}

}
