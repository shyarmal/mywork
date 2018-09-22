package test.streams;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Ex3 {

	private static void print3sAnd5s() {
		List<Integer> list =IntStream.range(1,  10001)
			.filter(n -> n % 3 == 0 || n % 5 == 0)
			.limit(10)
			.mapToObj(n -> n * n)
			.collect(Collectors.toList());
		System.out.println(list);
	}
	
	private static void countYandZ(String arg) {
		Optional.ofNullable(arg).ifPresent(str -> {
			String tokens = str.chars()
			.mapToObj(c -> Character.isLetter(c)? new Character((char)c).toString() : new String("-"))
			.collect(Collectors.joining());
			
			long count = Arrays.asList(tokens.split("-")).stream()
				.map(tok -> tok.toUpperCase())
				.filter(tok -> tok.endsWith("Y") || tok.endsWith("Z")).count();
			
			System.out.println(count);
		});
			
	}
	
	private static void incrementAndCapitalize(String str) {
		List<String> vowels = Arrays.asList("a", "e", "i", "o", "u");
		String result = str.chars().filter(ch -> Character.isLetter(ch))
				.map(i -> i + 1)
				.mapToObj(c -> Character.toString((char) c))
				.map(l -> vowels.contains(l)? l.toUpperCase(): l)
				.collect(Collectors.joining(","));
		System.out.println(result);
	}
	
	public static void main(String[] args) {
		print3sAnd5s();
		countYandZ("skz kklk23(*)) ky");
		countYandZ("!@hid&kilz*kyks bnz");
		countYandZ("sdkYsd@@*liz");
		countYandZ("Y3*3su zkyx iz");
		
		incrementAndCapitalize("wet cat ran");
	}
	
	
}
