package tests;

import java.util.Arrays;

public class MissingInteger {

	public static int solution (int[] a) {
		if (a.length > 100_000) {
			throw new IllegalArgumentException();
		}
		Arrays.sort(a);
		for (int i = 1; i < a.length; i++) {
			if ((a[i - 1] < 0 && a[i] > 1) || a[0] > 1) {
				return 1;
			} else if (a[i - 1] >= 0 && a[i] > 1) {
				if (a[i] - a[i - 1] > 1) {
					return a[i - 1] + 1;
				}
			}
		}
		throw new IllegalArgumentException("No missing numbers");
	}
	
	public static void main (String[] args) {
		System.out.println(solution(new int[]{2, 5, 3, 9}));
		System.out.println(solution(new int[]{-22, 5, 3, 9}));
		System.out.println(solution(new int[]{-2, -5, 0, 3, 9}));
		System.out.println(solution(new int[]{1, 2, 5, 3, 9}));
		System.out.println(solution(new int[]{-3, 9, 8, 0, 3, 6, 1, -9}));
		System.out.println(solution(new int[]{8, 3, 7, 2, 1, 11}));
		System.out.println(solution(new int[]{1, 2, 3, 4, 6}));
	}
}
