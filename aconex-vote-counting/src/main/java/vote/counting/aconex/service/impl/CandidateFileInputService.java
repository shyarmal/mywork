package vote.counting.aconex.service.impl;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import vote.counting.aconex.service.CandidateInputService;
import vote.counting.aconex.util.ConfigProperties;

public class CandidateFileInputService implements CandidateInputService {

	private ConfigProperties configProperties = ConfigProperties.getConfigProperties();
	private char firstKey = 'A';
		
	public Map<String, String> read() throws FileNotFoundException, IOException {
		Map<String, String> candidateMap = new HashMap<>();
		try (BufferedReader bufferedReader =  new BufferedReader(new FileReader(configProperties.getCandidateFilePath()))) {			
			while(bufferedReader.ready()) {
				includeInMap(bufferedReader.readLine().trim(), candidateMap);
			}
		}
		return candidateMap;
	}

	private void includeInMap(String value, Map<String, String> map) {
		String key = Character.toString((char) Integer.valueOf((int) firstKey + map.size()).intValue());
		map.put(key, value);
	}
}
