package vote.counting.aconex.service;

import java.util.Map;

public interface VotingService {

	public void registerVotes(Map<String, String> candidates);
}
