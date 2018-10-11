package anz.tech.stock.test;

import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

public class StockDiviationCalculator {
	
	private DecimalFormat df = new DecimalFormat("###.##;-#");
	
	public Map<String, String> calculateGainsAndLosses(List<String> stockPrices) {
		List<Double> stockPricesInDecimals = stockPrices.stream().map(Double::valueOf).collect(Collectors.toList());
		Map<String, String> lossesAndGains = new HashMap<String, String>();
		
		double totalGains = 0;
		double totalLosses = 0;
		for (int i = 1; i <  stockPricesInDecimals.size(); i++) {
			if (stockPricesInDecimals.get(i) > stockPricesInDecimals.get(i -1)) {
				totalGains += stockPricesInDecimals.get(i) - stockPricesInDecimals.get(i - 1);
			} else if (stockPricesInDecimals.get(i) < stockPricesInDecimals.get(i -1)) {
				totalLosses += stockPricesInDecimals.get(i) - stockPricesInDecimals.get(i - 1);
			}
		}
		
		lossesAndGains.put("losses", df.format(totalLosses));
		lossesAndGains.put("gains", df.format(totalGains));
		return lossesAndGains;
	}
	
    public static void main( String[] args ) {
    	List<String> inputPrices = new LinkedList<String>();
    	Scanner scanner = new Scanner(System.in);
    	System.out.println("key in stoke price and enter: \n");
    	while(scanner.hasNext()) {
    		System.out.println("key in stoke price and enter. Key in 'run' and enter to evaluate: \n");
    		String input = scanner.nextLine().trim();
    		if (input.equalsIgnoreCase("run")) {
    			break;
    		}
    		inputPrices.add(input);
    	}
    	Map<String, String> result = new StockDiviationCalculator().calculateGainsAndLosses(inputPrices);
    	System.out.println(String.format("Losses: %s, Gains: %s", result.get("losses"), result.get("gains")));
    }
    
}
