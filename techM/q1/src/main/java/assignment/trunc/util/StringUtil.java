package assignment.trunc.util;

/**
 * 
 * @author danuka
 * Utility class for string manipulations
 *
 */
public class StringUtil {
	
	private static final String REPLACER = " ... (truncated) ... "; /** Replacement in the truncated string */

	/**
	 * Given the character limit, truncates the message replacing characters 
	 * in the middle with " ... (truncated) ... ". 
	 * For the message to be truncated, it should be 
	 * 1. longer than the character limit  
	 * 2. the string " ... (truncated) ... "
	 * 3. character limit should be grater than " ... (truncated) ... " 
	 * @param message : message to be truncated
	 * @param charLimit: length of the truncated message (character limit)
	 * @return truncated message
	 */
	public static String truncate (String message, int charLimit) {
		if (message != null && message.length() > REPLACER.length() 
				&& message.length() > charLimit && REPLACER.length() < charLimit) {
			int noOfExtra = message.length() - charLimit;
			StringBuffer buffer = new StringBuffer(message).delete(charLimit/2, charLimit/2 + noOfExtra);
			int index1 = (buffer.length() - REPLACER.length()) / 2;
			int index2 = index1 + REPLACER.length();
			buffer.replace(index1, index2, REPLACER);
			return buffer.toString();
		}
		return message;
	}
}
  