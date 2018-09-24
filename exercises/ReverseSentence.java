package test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Similar to an assignment in hacker rank
 * @author Danuka
 *
 */
public class ReverseSentence {
	
	/**
	 * Given a statement, reverse it and capitalize the first letter of each word.
	 * 
	 * @param sentence
	 * @return
	 */
	static String reverseIt(String sentence) {
		List<Character> charList = sentence.chars()
				.mapToObj(i -> new Character((char) i)).collect(Collectors.toList());
		Collections.reverse(charList);
		String reversed = charList.stream().map(ch -> ch.toString()).collect(Collectors.joining());
		String[] words = reversed.split(" ");
		for (int i = 0; i < words.length; i++) {
			String word = words[i];
			if (!Character.isLetterOrDigit(word.charAt(0))) {
				words[i] = word.substring(1, word.length()) + word.charAt(0);				
			}
		}
		return Arrays.asList(words).stream().map(w -> w.toLowerCase())
				.map(w -> w.replaceFirst("" + w.charAt(0), "" + Character.toUpperCase(w.charAt(0))))
				.collect(Collectors.joining(" "));
	}
	
	public static void main(String[] args) {
		System.out.println(reverseIt("Jim, Peter and Nick ran to the town."));
		//Nwot. Eht Ot Nar Kcin Dna Retep Mij,
	}
}
