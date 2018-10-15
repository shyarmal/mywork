package vote.counting.aconex.service;

import java.util.List;

import vote.counting.aconex.domain.Ballot;

public interface VoteProcessingService {

	void processVotes(List<Ballot> ballots);
}
