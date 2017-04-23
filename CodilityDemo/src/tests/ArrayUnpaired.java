package tests;

import java.util.Arrays;

public class ArrayUnpaired {
	
	public static int solution (int[] a) {
		if (a.length % 2 == 1) {
			Arrays.sort(a);
			for (int i = 0; i < a.length; i +=2) {
				if (a.length == (i + 1) || a[i] != a[i + 1]) {
					return a[i];
				}
			}
		}
		
		throw new IllegalArgumentException();
	}
	
	public static void main (String[] args) {
		System.out.println(solution(new int[]{1, 3, 3, 4, 1, 5, 7, 5, 7}));
		System.out.println(solution(new int[]{5, 7, 3, 3, 5}));
		System.out.println(solution(new int[]{1, 3, 1, 3, 9}));
		System.out.println(solution(new int[]{0, 0, 2, 2, 3, 3, 7}));
		System.out.println(solution(new int[]{1, 2, 2, 4, 4, 6, 6}));
		System.out.println(solution(new int[]{1, 1, 1, 1, 1}));
	}

}
