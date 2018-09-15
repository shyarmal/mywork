package airwell.rpn.calculator.command;

import java.util.Stack;

import airwell.rpn.calculator.exception.EmptyNumberStackException;

public abstract class TwoOperandCommand extends Command {

	protected TwoOperandCommand(String name) {
		super(name);
	}

	protected Double number1;
	protected Double number2;
	
	@Override
	public void undo() {
		numbers.pop();
		numbers.push(number1);
		numbers.push(number2);
	}
	
	protected void initNumbers(Stack<Double> numbers, String command) throws EmptyNumberStackException {
		if (numbers.size() < 2) {
			throw new EmptyNumberStackException("Insufficient parameters", command);
		}
		number2 = numbers.pop();
		number1 = numbers.pop();
	}
	
}
