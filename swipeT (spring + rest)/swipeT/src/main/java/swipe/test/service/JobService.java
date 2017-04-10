package swipe.test.service;

import java.util.List;

import swipe.test.domain.Job;

public interface JobService {
	
	public void initService ();
	
	public List<Job> findBestThreeMatches (Integer workerId);

}
