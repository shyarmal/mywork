package danuka.service.impl;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import danuka.rest.domain.CountResult;
import danuka.rest.domain.SearchTexts;
import danuka.rest.domain.WordCount;


/**
 * 
 * @author Danuka
 * Test case for CounterServiceImpl
 *
 */
public class CounterServiceImplTest {

	private CounterServiceImpl counterService;
	private static final String FILE_PATH = "../../../text_content_mock.txt";
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		try (OutputStreamWriter writer = 
				new OutputStreamWriter(new FileOutputStream(new File(FILE_PATH)))) {
			writer.write("abc t89 kk abc pqr kkld p abc pe 123 34 b abc, pqr");
			writer.write("ac tfe kek bc pqr kkld p abc pe 123 34 b abc, qr.");
			
		}
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		new File(FILE_PATH).delete();
	}

	@Before
	public void setUp() throws Exception {
		counterService = new CounterServiceImpl();
		Field filePath = counterService.getClass().getDeclaredField("TEXT_PATH");
		filePath.setAccessible(true);
		filePath.set(counterService, FILE_PATH);
		Field countList = counterService.getClass().getDeclaredField("countList");
		countList.setAccessible(true);
		((List<WordCount>)countList.get(counterService)).clear();
		Method initMethod = counterService.getClass().getDeclaredMethod("initCountList", null);
		initMethod.setAccessible(true);
		initMethod.invoke(counterService, null);
		
		countList.setAccessible(true);
		List<WordCount> wordList = ((List<WordCount>)countList.get(counterService));
		wordList.clear();
		wordList.add(new WordCount("abc", 6));
		wordList.add(new WordCount("yie", 5));
		wordList.add(new WordCount("oop", 4));
		wordList.add(new WordCount("xyz", 3));
		wordList.add(new WordCount("pqr", 2));
		wordList.add(new WordCount("pr", 1));
	}

	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Test method for 
	 * public String findTop(Integer limit)
	 */
	@Test
	public void testFindTop() {
		assertEquals("abc|6 yie|5", counterService.findTop(2));
		assertEquals("abc|6 yie|5 oop|4 xyz|3", counterService.findTop(4));
	}
	
	/**
	 * Test method for 
	 * public CountResult searchTextCounts(Integer limit)
	 */
	@Test
	public void testSearchTextCounts() {
		SearchTexts st1 = new SearchTexts();
		List<String> texts1 = Arrays.asList("pr", "xyz");
		st1.setSearchText(texts1);
		CountResult result1 = counterService.searchTextCounts(st1);
		assertEquals(2, result1.getCounts().size());
		assertEquals(new Integer(3), result1.getCounts().get(0).get("xyz"));
		assertEquals(new Integer(1), result1.getCounts().get(1).get("pr"));
		List<String> texts2 = Arrays.asList("pwr", "abc", "oop");
		SearchTexts st2 = new SearchTexts();
		st2.setSearchText(texts2);
		CountResult result2 = counterService.searchTextCounts(st2);
		assertEquals(3, result2.getCounts().size());
		assertEquals(new Integer(6), result2.getCounts().get(0).get("abc"));
		assertEquals(new Integer(4), result2.getCounts().get(1).get("oop"));
		assertEquals(new Integer(0), result2.getCounts().get(2).get("pwr"));
		assertNull(result2.getCounts().get(1).get("pwr"));
	}

}
