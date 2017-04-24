package tests;

public class PermMissingElem {
	
	public static int solution (int[] a) {
		if (a.length <= 100_000 && a.length > 0) {
			if (a[0] != 1)
				return 1;
			for (int i = 1; i < a.length; i++) {
				if (a[i] != a[i - 1] + 1) 
					return a[i -1] + 1;
			}
		} else {
			throw new IllegalArgumentException();
		}
		return -1;
	}
	
	public static void main(String[] args) {
		System.out.println(solution(new int[]{1, 2, 3, 5, 6, 7}));
		System.out.println(solution(new int[]{2, 3, 4}));
	}

}
