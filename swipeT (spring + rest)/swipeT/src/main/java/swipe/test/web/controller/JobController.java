package swipe.test.web.controller;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import swipe.test.domain.Job;
import swipe.test.service.JobService;

@RestController
public class JobController {
	
	@Resource(name = "jobService")
	private JobService jobService;
	
	private final static Logger LOGGER = Logger.getLogger(JobController.class);
	
	@PostConstruct
	public void init () {
		jobService.initService();
		LOGGER.info("Initialized matching engine .... ");
	}
	
	@RequestMapping(value = "/jobs/{workerId}", method = RequestMethod.GET, headers="Accept=application/json")
	public List<Job> displayJobs (@PathVariable Integer workerId) {
		List<Job> matchingJobs = null;
		try {
			matchingJobs = jobService.findBestThreeMatches(workerId); 			
			LOGGER.info(String.format("Found %d matches for worker id %d", matchingJobs.size(), workerId));
		} catch (Exception e) {
			LOGGER.error(String.format("Search operation failed for worker Id, %d", workerId), e);
		}
		return matchingJobs;
	}
	
}
