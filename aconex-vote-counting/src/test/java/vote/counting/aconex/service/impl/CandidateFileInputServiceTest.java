package vote.counting.aconex.service.impl;

import static org.easymock.EasyMock.expect;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.easymock.EasyMockRunner;
import org.easymock.EasyMockSupport;
import org.easymock.Mock;
import org.easymock.TestSubject;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import vote.counting.aconex.service.impl.CandidateFileInputService;
import vote.counting.aconex.util.ConfigProperties;

@RunWith(EasyMockRunner.class)
public class CandidateFileInputServiceTest extends EasyMockSupport {

	@TestSubject
	private CandidateFileInputService candidateFileInputService = new CandidateFileInputService();
	
	@Mock
	private ConfigProperties configProperties;
	
	Map<String, String> candidateMap = new HashMap<String, String>();
	private String testFile = "candidates.txt";
	
	@Before
	public void setUp() throws IOException {
		try(FileWriter fileWriter = new FileWriter(testFile)) {
			fileWriter.write("Winery tour\n");
			fileWriter.write("Movie night\n");
			fileWriter.write("Surfing lession\n");
		}
		candidateMap.put("A", "Winery tour");
		candidateMap.put("B", "Movie night");
		candidateMap.put("C", "Surfing lession");
	}
	
	@After
	public void tearDown() {
		new File(testFile).delete();
	}
	
	@Test
	public void shouldReadFromFile() throws FileNotFoundException, IOException {
		
		expect(configProperties.getCandidateFilePath()).andReturn(testFile);
		
		replayAll();
		Map<String, String> result = candidateFileInputService.read();
		verifyAll();
		
		assertThat(result.size(), is(3));
		assertThat(result.get("A"), is("Winery tour"));
		assertThat(result.get("B"), is("Movie night"));
		assertThat(result.get("C"), is("Surfing lession"));
	}
}
