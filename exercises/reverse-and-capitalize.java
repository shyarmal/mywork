package test.streams;

import java.util.Collections;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Ex4 {

	// reverse the sentence and capitalize the first letter of every word.
	public static String reverseAndCapitalise(String sentenceIn) {
		String reversed = sentenceIn.chars()
				.mapToObj(i -> Character.toString((char) i))
				.collect(Collectors.collectingAndThen(Collectors.toList(), list -> {
					Collections.reverse(list);
					return list.stream().collect(Collectors.joining());
				}));
		
		return Stream.of(reversed.split(" ")).map(Ex4::replaceNonAlpahNumeric)
		.map(str -> str.replaceFirst("" + str.charAt(0), "" + Character.toUpperCase(str.charAt(0)))).collect(Collectors.joining(" "));
    }
	
	private static String replaceNonAlpahNumeric(String string) {
		if (string.length() > 0) {
			if (!Character.isLetter(string.charAt(0)) && !Character.isDigit(string.charAt(0))) {
				return string.substring(1, string.length()) + string.charAt(0);
			}
		}
		return string;
	}
	
	public static void main(String[] args) {
		System.out.println(reverseAndCapitalise("Muller, Jill, Jack and Mary went away."));
	}
}
