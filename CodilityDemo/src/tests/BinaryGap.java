package tests;

public class BinaryGap {
	
	public int solution (int n) {
		String[] str = Integer.toBinaryString(n).split("1");
		int max = 0;
		for (String st : str) {
			if (st.length() > max) {
				max = st.length();
			}
		}
		return max;
	}
	
	public static void main (String[] args) {
		BinaryGap bg = new BinaryGap();
		System.out.println(bg.solution(9));
		System.out.println(bg.solution(529));
		System.out.println(bg.solution(20));
		System.out.println(bg.solution(15));
	}

}
