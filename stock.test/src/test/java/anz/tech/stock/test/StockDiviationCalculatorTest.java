package anz.tech.stock.test;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import static junit.framework.Assert.assertEquals;

public class StockDiviationCalculatorTest {

	private StockDiviationCalculator stockDiviationCalculator = new StockDiviationCalculator();
	
	@Test
	public void test() {
		List<String> stockPrices = Arrays.asList("78.41", "85.18", "91.09", "90.57", 
				"91.02", "103.61", "105.88", "103.77", "110.13", "108.89", "105.09");
		
		Map<String, String> diviations = stockDiviationCalculator.calculateGainsAndLosses(stockPrices);
		assertEquals("-7.67", diviations.get("losses"));
		assertEquals("34.35", diviations.get("gains"));
	}
}












