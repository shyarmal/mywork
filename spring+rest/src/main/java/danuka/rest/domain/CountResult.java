package danuka.rest.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * 
 * @author Danuka
 * The class to hold results of searched words. 
 * Converts to json format at the end point
 *
 */
public class CountResult {
	
	/** List of words with counts. (format: [{word: count}, {word: count}, {word: count}] */
	private List<Map<String, Integer>> counts = new ArrayList<>(); 
	@JsonIgnore
	private Set<String> words = new HashSet<>();/** Used to hold processed keys by the CountingService. Not exposed in json result*/

	/**
	 * getter of counts
	 * @return List of word counts
	 */
	public List<Map<String, Integer>> getCounts() {
		return counts;
	}
	
	/**
	 * gets the counted words.
	 * @return set of counted words
	 */
	public Set<String> countedWords () {
		return words;
	}
	
	/**
	 * Adds entry to the counts map and aslso updates the counted word set
	 * @param word 
	 * @param count
	 */
	public void addCount (String word, Integer count) {
		HashMap<String, Integer> entry = new HashMap<>();
		entry.put(word, count);
		words.add(word);
		counts.add(entry);
	}
	
}
