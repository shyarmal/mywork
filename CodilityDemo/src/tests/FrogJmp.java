package tests;

public class FrogJmp {

	public static int solution (int x, int y, int d) {
		if (x <= y &&  1 <= y && 1_000_000_000 >= y
				&&  1 <= d && 1_000_000_000 >= d) {
			int count = 0;
			while (x <= y) {
				x += d;
				count++;
			}
			return count;
		} else {
			throw new IllegalArgumentException();
		}
	}
	
	public static void main (String[] args) {
		System.out.println(solution(10, 85, 30));
	}
}
