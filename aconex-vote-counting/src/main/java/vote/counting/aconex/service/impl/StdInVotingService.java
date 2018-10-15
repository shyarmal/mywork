package vote.counting.aconex.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import vote.counting.aconex.service.VoteProcessingService;
import vote.counting.aconex.service.VotingService;
import vote.counting.aconex.service.translator.InputTranslator;

public class StdInVotingService implements VotingService {

	private InputTranslator inputTranslator;
	private VoteProcessingService voteProcessingService;
	private Scanner scanner = new Scanner(System.in);
	
	private static final String PROCESSING_COMMAND = "tally";
	
	public StdInVotingService(InputTranslator inputTranslator, VoteProcessingService voteProcessingService) {
		this.inputTranslator = inputTranslator;
		this.voteProcessingService = voteProcessingService;
	}
	
	public void registerVotes(Map<String, String> candidates) {
		displayCandidates(candidates);
		List<String> votes = new ArrayList<>();
		while(scanner.hasNext()) {
			String input = scanner.nextLine();
			if(!input.equals(PROCESSING_COMMAND)) {
				votes.add(input);
			} else {
				break;
			}
		}
		voteProcessingService.processVotes(inputTranslator.translate(votes, candidates));		
	}
	
	private void displayCandidates(Map<String, String> candidates) {
		candidates.forEach((k, v) -> System.out.println(k + ". " + v));
	}
}
