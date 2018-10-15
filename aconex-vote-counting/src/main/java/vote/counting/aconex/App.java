package vote.counting.aconex;

import java.util.Map;

import vote.counting.aconex.service.CandidateInputService;
import vote.counting.aconex.service.VotingService;
import vote.counting.aconex.service.impl.CandidateFileInputService;
import vote.counting.aconex.service.impl.DefaultVoteProcessingService;
import vote.counting.aconex.service.impl.StdInVotingService;
import vote.counting.aconex.service.translator.InputTranslator;

public class App {
	
	public static void main(String[] args) throws Exception {
		CandidateInputService candidateInputService = new CandidateFileInputService();
		Map<String, String> candidateInfo = candidateInputService.read();
		VotingService votingService = new StdInVotingService(new InputTranslator(), new DefaultVoteProcessingService());
		votingService.registerVotes(candidateInfo);
	}
}
