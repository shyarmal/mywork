package airwell.rpn.calculator.command;

import java.util.Stack;

import airwell.rpn.calculator.exception.EmptyNumberStackException;

public class MultiplyCommand extends TwoOperandCommand {	
	
	private static final String NAME = "MULTIPLY";
	
	public MultiplyCommand(Stack<Double> numbers) {
		super(NAME);
		this.numbers = numbers;
	}
	
	@Override
	public void execute() throws EmptyNumberStackException {
		initNumbers(numbers, NAME);
		numbers.push(number1 * number2);
		super.execute();
	}

}
