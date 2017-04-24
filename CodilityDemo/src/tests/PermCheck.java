package tests;

import java.util.Arrays;

public class PermCheck {
	
	public static int solution (int[] n) {
		Arrays.sort(n);
		if (n[0] != 1)
			return 0;
		else {
			for (int i = 1; i < n.length; i++) {
				if (n[i] - 1 != n[i-1])
					return 0;
			}
			return 1;
		}
	}
	
	public static void main (String[] args) {
		System.out.println(solution(new int[]{1, 2, 3, 4, 5}));
		System.out.println(solution(new int[]{-1, 2, -3, 4, 5}));
		System.out.println(solution(new int[]{1, 2, 3, 5}));
		System.out.println(solution(new int[]{3, 4, 5}));
		System.out.println(solution(new int[]{1, 2, 3}));
	}

}
