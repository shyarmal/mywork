package tests;

import java.util.Arrays;

public class ArrayCyclicRotation {
	
	public static int[] solution (int[] a, int k) {
		if (a.length <= 100 && k <= 100) {
			int temp = 0;
			while (k > 0) {
				temp = a[a.length - 1];
				for (int i = a.length - 1; i > 0; i--) {
					a[i] = a[i -1];
				}
				a[0] = temp;
				k--;
			}
			return a;
		} else {
			throw new IllegalArgumentException();
		}
	}
	
	public static void main (String[] args) {
		int[] x = solution(new int[]{1, 2, 3, 4, 5, 6, 7}, 3);
		for (int i = 0; i < x.length; i++) {
			System.out.print(x[i] + " ");
		}
		
	}

}
