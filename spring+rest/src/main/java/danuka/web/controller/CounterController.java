package danuka.web.controller;

import javax.annotation.Resource;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import danuka.rest.domain.CountResult;
import danuka.rest.domain.SearchTexts;
import danuka.service.CounterService;

/**
 * 
 * @author Danuka
 * Controller holding end-points of the rest api
 *
 */
@RestController
@RequestMapping("counter-api")
public class CounterController {
	
	@Resource(name="counterService")
	private CounterService counterService;
	
	/**
	 * To get the count of words passed as the payload of the post request
	 * @param searchTexts texts to be searched (converted from application/json content)
	 * @return ResponseEntity<CountResult> having text counts as the content content type, application/json. 
	 */
	@RequestMapping(value = "/search", 
			method=RequestMethod.POST,
			headers = "content-type=application/json", 
			consumes = "application/json", 
			produces = "application/json")
	public ResponseEntity<CountResult> search (@RequestBody SearchTexts searchTexts) {
		return new ResponseEntity<CountResult>(
				counterService.searchTextCounts(searchTexts), HttpStatus.OK);
	}
	
	/**
	 * To get top most words, given the limit
	 * @param limit Path variable of the get request, the top word count limit
	 * @return ResponseEntity<String> having the top word count in text/csv format
	 */
	@RequestMapping(value = "/top/{limit}", 
			method=RequestMethod.GET,
			headers = "Accept=text/csv", 
			produces = "text/csv")
	public ResponseEntity<String> topWordCount (@PathVariable Integer limit) {
		return new ResponseEntity<String>(counterService.findTop(limit), HttpStatus.OK);
	}

}
