package assignment.trunc.util;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * 
 * @author danuka
 * Test case for StringUtil
 *
 */
public class StringUtilTest {

	@Test
	public void testTruncate() {
		assertEquals("abcdefg", StringUtil.truncate("abcdefg", 5));
		assertEquals("xyzxyzxyzxyzxyzxyzxyzxyzxyzxyz", 
				StringUtil.truncate("xyzxyzxyzxyzxyzxyzxyzxyzxyzxyz", 5));
		assertNull(StringUtil.truncate(null, 30));
		assertEquals("will not change", StringUtil.truncate("will not change", 35));
		assertEquals("will not change", StringUtil.truncate("will not change", -1));
		String result1 = StringUtil.truncate("xyzxyzxyzxyzxyzxyzxyxyzxyzxyzxyzxyzxyzxyzxyzxyzxyz", 25);
		assertEquals("xy ... (truncated) ... yz", result1);
		assertEquals(25, result1.length());
		String result2 = StringUtil.truncate("1234512345123451234512345", 23);
		assertEquals("1 ... (truncated) ... 5", result2);
		assertEquals(23, result2.length());
		String result3 = StringUtil.truncate("This is a sample text. I'm testing my method.", 30);
		assertEquals("This ... (truncated) ... thod.", result3);
		assertEquals(30, result3.length());
		String result4 = StringUtil.truncate("This is a sample text. I'm testing my method.", 22);
		assertEquals(" ... (truncated) ... .", result4);
		assertEquals(22, result4.length());
	}

}
