package danuka.service;

import danuka.rest.domain.CountResult;
import danuka.rest.domain.SearchTexts;

/**
 * 
 * @author Danuka
 * Interface for word counting service
 *
 */
public interface CounterService {

	public CountResult searchTextCounts(SearchTexts searchTexts);

	public String findTop (Integer limit);
}
