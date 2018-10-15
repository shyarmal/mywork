package vote.counting.aconex.util;

import java.util.Properties;

public class ConfigProperties {

	private static final String CANDIDATE_FILE = "app.voting.candidates";
	private static Properties properties = new Properties();
	private static ConfigProperties configProperties = new ConfigProperties();
	
	private ConfigProperties() {
		try {
			properties.load(getClass().getClassLoader().getResourceAsStream("conf.properties"));
		} catch (Exception e) {
			System.err.println("Failed to load candidates");
			e.printStackTrace();
		}
	}
	
	public static ConfigProperties getConfigProperties() {
		return configProperties;
	}
	
	public String getCandidateFilePath() {
		return properties.getProperty(CANDIDATE_FILE);
	}
}
