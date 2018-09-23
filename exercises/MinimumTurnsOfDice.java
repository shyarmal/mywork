package test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
* Solution to a codility question.
*/
public class MinimumTurnsOfDice {
	
	/**
	 * Given an array of pips of the side of dice turned up, calculates the minimum number of 
	 * turns needed to get the same pips on top of all dice. 
	 * 
	 * @param A, array representing the pips of dice on the sides facing up.
	 * @return minimum number of turns needed to bring the same number of pips of all dice.
	 */
	public static int solution(int[] A) {
		int min = Arrays.stream(A).boxed().min(Comparator.comparing(Integer::intValue)).get();
		int max = Arrays.stream(A).boxed().max(Comparator.comparing(Integer::intValue)).get();
		int otherNumberCount = (int) Arrays.stream(A).boxed().filter(i -> i != min && i != max).count();
		if (otherNumberCount == 0) {
			int twoTurnCounts = (int) Arrays.stream(A).boxed().filter(i -> i != min).filter(i -> i + min == 7).count();
	        return 2 * twoTurnCounts;
		}
        return otherNumberCount + 1;
    }
	
	public static void main(String[] args) {
		System.out.println(solution(new int[]{1, 3, 2, 6}));
		System.out.println(solution(new int[]{1, 1, 6}));
		System.out.println(solution(new int[]{1, 4, 5}));
	}
}
