package vote.counting.aconex.service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;

public interface CandidateInputService {

	Map<String, String> read() throws FileNotFoundException, IOException;
}
