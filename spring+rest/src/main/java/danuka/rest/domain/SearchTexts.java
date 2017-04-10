package danuka.rest.domain;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 
 * @author Danuka
 * Parameter to the end point (formed from json)
 * Holds the words to be searched for counts.
 *
 */
public class SearchTexts {
	
	@JsonProperty
	private List<String> searchText; /** list holding the words to be counted*/

	/**
	 * getter of searchText
	 * @return searchText
	 */
	public List<String> getSearchText() {
		return searchText;
	}

	/**
	 * setter of searchText
	 * @param searchText
	 */
	public void setSearchText(List<String> searchText) {
		this.searchText = searchText;
	}
	
}
