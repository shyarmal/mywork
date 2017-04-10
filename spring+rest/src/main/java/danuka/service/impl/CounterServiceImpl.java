package danuka.service.impl;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import danuka.rest.domain.CountResult;
import danuka.rest.domain.SearchTexts;
import danuka.rest.domain.WordCount;
import danuka.service.CounterService;

/**
 * 
 * @author Danuka
 * Word count service implementation
 *
 */
@Service("counterService")
public class CounterServiceImpl implements CounterService {

	private List<WordCount> countList = new ArrayList<>();/** List to hold all words in the file in the ascending order of counts*/
	private static final Pattern TO_REMOVE_CHAR_PATTERN = Pattern.compile("^.*[,.]$"); /** Pattern to match words with ending . or , */
	private final String TEXT_PATH = "../../../text_content.txt"; /** File location where words are */

	/**
	 * No argument constructor. Initializes the service
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public CounterServiceImpl () throws FileNotFoundException, IOException {
		initCountList();
	}
	
	/**
	 * Adds contents (words with counts) into the countList from the file, 
	 * in ascending order of their occurrences. Commas and periods of paragraphs are removed here.
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	private void initCountList() throws FileNotFoundException, IOException {
		Map<String, Integer> wordMap = new HashMap<>();
		try (BufferedReader br = new BufferedReader(
				new FileReader(getClass().getResource(TEXT_PATH).getPath()))) {
			while (br.ready()) {
				String[] line = br.readLine().split(" ");
				for (int i = 0; i < line.length; i++) {
					if (TO_REMOVE_CHAR_PATTERN.matcher(line[i]).matches()) {
						String wd = line[i].trim();
						addToMap(wordMap, wd.substring(0, wd.length() - 1));
					} else {
						addToMap(wordMap, line[i].trim());
					}
				}
			}
		}
		updateCountList(wordMap);
	}

	/**
	 * Given a map with words and counts and a word, adds or updates the word count.
	 * @param map
	 * @param word
	 */
	private void addToMap(Map<String, Integer> map, String word) {
		String wordLower = word.toLowerCase();
		if (map.containsKey(wordLower)) {
			map.put(wordLower, map.get(wordLower) + 1);
		} else {
			map.put(wordLower, new Integer(1));
		}
	}

	/**
	 * Given a map of words and counts, adds to the countList. 
	 * @param map Map with words as the key and counts as the value
	 */
	private void updateCountList (Map<String, Integer> map) {
		map.forEach((k, v) -> countList.add(new WordCount(k, v)));
		Collections.sort(countList, (a, b) -> {
			return (a.getCount() > b.getCount()) ? -1 : 
				(a.getCount() == b.getCount()) ? 0 : 1;
		});
	}
	
	/**
	 * Returns occurrences of the given words.
	 * @param searchTexts : contains the words that counts are needed.
	 * @return CountResult: contains the counts of the words given.
	 */
	@Override
	public CountResult searchTextCounts(SearchTexts searchTexts) {
		CountResult result = new CountResult();
		List<String> searchWords = searchTexts.getSearchText()
						.stream()
						.map(e -> e.toLowerCase())
						.collect(Collectors.toList());
		countList.stream()
				.filter(e -> searchWords.contains(e.getWord()))
				.forEach(e -> result.addCount(e.getWord(), e.getCount()));
		searchWords.stream()
					.filter(e -> !result.countedWords().contains(e))
					.forEach(e -> result.addCount(e, new Integer(0)));
		return result;
	}

	/**
	 * Given the top limit returns the words with most occurrences
	 * @param limit  : top words count limit
	 * @return String: words with their counts (format <word>|<count> <word>|<count> <word>|<count> ...) 
	 */
	@Override
	public String findTop(Integer limit) {
		return countList.stream()
				.limit(limit)
				.map(e -> e.getWord()+"|"+e.getCount())
				.collect(Collectors.joining(" "));
	}

}
