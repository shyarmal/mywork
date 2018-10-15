package vote.counting.aconex.util;

import org.junit.Test;

import static org.junit.Assert.*;

public class ConfigPropertiesTest {

	@Test
	public void testLoadProperties() {
		
		assertNotNull(ConfigProperties.getConfigProperties());
		assertNotNull(ConfigProperties.getConfigProperties().getCandidateFilePath());
	}
}
