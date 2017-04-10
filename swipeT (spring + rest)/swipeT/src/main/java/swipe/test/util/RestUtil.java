package swipe.test.util;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import swipe.test.domain.Job;
import swipe.test.domain.Worker;

@Service
public class RestUtil {

	@Autowired
	private RestTemplate restTemplate;
	@Value(value = "${workers.url}")
	private String workersUrl;
	@Value(value = "${jobs.url}")
	private String jobsUrl;
	private static final Logger LOGGER = Logger.getLogger(RestUtil.class);
	
	public Worker[] callWorkerRestService () {
		initRestTemplate();  	
		LOGGER.debug(String.format("Retrieving workers from [ %s ]", workersUrl));
	    return  restTemplate.getForObject(workersUrl, Worker[].class);
	}
	
	public Job[] callJobRestService () {
		initRestTemplate();   	
		LOGGER.debug(String.format("Retrieving jobs from [ %s ]", jobsUrl));
	    return  restTemplate.getForObject(jobsUrl, Job[].class);
	}
	
	private void initRestTemplate () {
		if (restTemplate.getMessageConverters().isEmpty()) {
			restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());	  	
		}
	}
}
