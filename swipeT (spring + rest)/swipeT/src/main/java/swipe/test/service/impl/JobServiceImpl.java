package swipe.test.service.impl;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import swipe.test.domain.Job;
import swipe.test.domain.JobSearchAddress;
import swipe.test.domain.Location;
import swipe.test.domain.Worker;
import swipe.test.service.JobService;
import swipe.test.util.RestUtil;

@Service("jobService")
public class JobServiceImpl implements JobService {

	@Autowired
	private RestUtil restUtil;
	private Map<Integer, Worker> workerMap;
	private List<Job> jobs;
	private static final Logger LOGGER = Logger.getLogger(JobServiceImpl.class);
	
	public void initService() {
		workerMap = mapWorkerByUserId(restUtil.callWorkerRestService());
		jobs = Arrays.asList(restUtil.callJobRestService());
		LOGGER.debug(String.format("Retrieved %d jobs and %d workers", jobs.size(), workerMap.size()));
	}

	public List<Job> findBestThreeMatches(Integer workerId) {
		final Worker worker = workerMap.get(workerId);
		return jobs.stream()
			.filter(j -> (!j.getDriverLicenseRequired()) || worker.getHasDriversLicense())
			.filter(j -> worker.getSkills().contains(j.getJobTitle()))
			.filter(j -> hasRequiredCertficates(j.getRequiredCertificates(), worker.getCertificates()))
			.filter(j -> isJobLocationWithinSearchRange(j.getLocation(), worker.getJobSearchAddress()))
			.limit(3)
			.collect(Collectors.toList());
	}
	
	private Map<Integer, Worker> mapWorkerByUserId (Worker[] workers) {
		Map<Integer, Worker> map = new HashMap<>();
		for (int i = 0; i < workers.length; i++) {
			map.put(workers[i].getUserId(), workers[i]);
		}
		return map;
		
	}
	
	private boolean hasRequiredCertficates (List<String> requiredCertificates, List<String> workerCertificates) {
		return requiredCertificates.stream()
								.allMatch(rc -> workerCertificates.contains(rc));	
	}
	
	private boolean isJobLocationWithinSearchRange (Location jobLocation, JobSearchAddress jobSearchAddress) {
		double longitudeDifference = Double.parseDouble(jobLocation.getLongitude()) - jobSearchAddress.getLongitude();
		double latitudeDifference = Double.parseDouble(jobLocation.getLatitude()) - jobSearchAddress.getLatitude();
		double distance = Math.sqrt(Math.pow(longitudeDifference, 2) + Math.pow(latitudeDifference, 2));
		return (jobSearchAddress.getMaxJobDistance() >= distance);
	}

}
