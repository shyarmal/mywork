package airwell.rpn.calculator;

import java.util.Scanner;
import java.util.Stack;
import java.util.stream.Collectors;

import airwell.rpn.calculator.command.util.CommandExecutor;
import airwell.rpn.calculator.command.util.CommandFactory;
import airwell.rpn.calculator.util.Util;

public class Calculator {

	private Stack<Double> numbers = new Stack<Double>();
	private CommandExecutor commandExecutor = new CommandExecutor();
	private CommandFactory commandFactory = new CommandFactory();
	private boolean previousCommandFlag = false;
	
	public void processInput(String input) {
		String[] tokens = input.split("\\s+");
		for (String token : tokens) {
			if (token.matches("\\d+")) {
				numbers.push(Double.valueOf(token));
				previousCommandFlag = false;
			} else {
				commandExecutor.registerCommand(
						commandFactory.buildCommand(token, numbers), previousCommandFlag);
				commandExecutor.execute();
				previousCommandFlag = true;
			}
		} 
	}
	
	public String resultToString() {
		return numbers.stream().map(Util::stipFractionsIfOk).collect(Collectors.joining(" "));
	}
	
	public void clear( ) {
		numbers.clear();
	}
	
	public static void main(String[] args) {
		Calculator calculator = new Calculator();
		System.out.println("Enter input: ");
		Scanner scanner = new Scanner(System.in);
		while(scanner.hasNext()) {
			calculator.processInput(scanner.nextLine());
			System.out.println(calculator.resultToString());
		}		
	}
}
