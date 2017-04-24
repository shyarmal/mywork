package tests;

import java.util.Arrays;

public class FrogRiverOne {

	public static int solution (int x, int[] a) {
		if (x > 100_000 || a.length > 100_000)
			throw new IllegalArgumentException();
		int[] b = new int[x];
		for (int i = 0; i < a.length; i++) {
			if (a[i] <= x) {
				if (b[a[i] - 1] == 0 || b[a[i] - 1] > i + 1)
					b[a[i] - 1] = i + 1;
			} else 
				throw new IllegalArgumentException("Elements of the array cannot be grater than " + x);
		}
		Arrays.sort(b);
		return (b[0] != 0) ? b[b.length -1] : -1;	
	}
	
	public static void main (String[] args) {
		System.out.println(solution (3, new int[]{3, 2, 2, 1, 1}));
		System.out.println(solution (3, new int[]{3, 3, 3, 3, 2, 3, 2, 1}));
		System.out.println(solution (4, new int[]{2, 3, 2, 1, 1, 4, 2, 3}));
		System.out.println(solution (5, new int[]{1, 2, 4, 4, 3, 2, 5, 1, 1, 3}));
		System.out.println(solution (6, new int[]{3, 4, 2, 2, 6, 1, 3}));
		System.out.println(solution (4, new int[]{3, 3, 3}));
	}
}
