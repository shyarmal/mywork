package test;

/**
 * This is a hacker rank problem.
 * @author Danuka
 *
 */
public class AlternatingCharacters {
	
	/**
	 * Return the number of deletions needed to make the string have alternating characters.
	 * Input string can contain only 'A's and 'B's.
	 * eg: AAAB will need 2 deletions to make it AB
	 * eg: AAABBBBBABB will need 7 deletions to make it ABAB
	 * 
	 * @param s
	 * @return
	 */
    static int alternatingCharacters(String s) {
    	int deletionCount = 0;
        char[] chars = s.toCharArray();
        for(int i =1; i < chars.length; i++) {
        	if(chars[i] == chars[i-1]) {
        		deletionCount++;
        	}
        }
        
        return deletionCount;

    }
    
	public static void main(String[] args) {
		
	}
	
	
}
