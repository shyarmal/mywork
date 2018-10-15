package vote.counting.aconex.service.impl;

import static org.easymock.EasyMock.expect;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import org.easymock.EasyMockRunner;
import org.easymock.EasyMockSupport;
import org.easymock.Mock;
import org.easymock.TestSubject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import vote.counting.aconex.domain.Ballot;
import vote.counting.aconex.service.VoteProcessingService;
import vote.counting.aconex.service.translator.InputTranslator;

@RunWith(EasyMockRunner.class)
public class StdInVotingServiceTest extends EasyMockSupport {

	@Mock
	private InputTranslator inputTranslator;
	@Mock
	private VoteProcessingService voteProcessingService;
	@TestSubject
	private StdInVotingService stdInVotingService = new StdInVotingService(inputTranslator, voteProcessingService);
	
	final String input1 = "ab c";
	final String input2 = "b cd";
	final String input3 = "a d c b";
	final String input4 = "x";
	final String input5 = "tally";
	private Scanner scanner = new Scanner(String.format("%s\n%s\n%s\n%s\n%s\n", input1, input2, input3, input4, input5));
	
	@Before
	public void setUp() throws Exception {
		Field scannerField = stdInVotingService.getClass().getDeclaredField("scanner");
		scannerField.setAccessible(true);
		scannerField.set(stdInVotingService, scanner);
	}
	
	@Test
	public void shouldReadAndDelegateToVoteProcessing() {
		Map<String, String> candidates = new HashMap<>();	
		List<String> votes = Arrays.asList(input1, input2, input3, input4);
		List<Ballot> ballots = Collections.EMPTY_LIST;
				
		expect(inputTranslator.translate(votes, candidates)).andReturn(ballots);
		voteProcessingService.processVotes(ballots);
		
		replayAll();
		stdInVotingService.registerVotes(candidates);
		verifyAll();
	}
}
