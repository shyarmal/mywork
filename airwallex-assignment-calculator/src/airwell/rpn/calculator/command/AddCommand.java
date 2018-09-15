package airwell.rpn.calculator.command;

import java.util.Stack;

import airwell.rpn.calculator.exception.EmptyNumberStackException;

public class AddCommand extends TwoOperandCommand {		
	
	private static final String NAME = "ADD";
	
	public AddCommand(Stack<Double> numbers) {
		super(NAME);
		this.numbers = numbers;
	}

	@Override
	public void execute() throws EmptyNumberStackException {
		initNumbers(numbers, NAME);
		numbers.push(number1 + number2);
		super.execute();
	}


}
