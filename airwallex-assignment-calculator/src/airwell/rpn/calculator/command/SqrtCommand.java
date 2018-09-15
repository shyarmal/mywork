package airwell.rpn.calculator.command;

import java.util.Stack;

import airwell.rpn.calculator.exception.EmptyNumberStackException;

public class SqrtCommand extends Command {	
	
	private static final String NAME = "SQUARE ROOT";
	private Double number;
	
	public SqrtCommand(Stack<Double> numbers) {
		super(NAME);
		this.numbers = numbers;
	}
	
	@Override
	public void execute() throws EmptyNumberStackException {
		if (numbers.size() < 1) {
			throw new EmptyNumberStackException("Insufficient parameters", NAME);
		}
		number = numbers.pop();
		numbers.push(Math.sqrt(number));
		super.execute();
	}

	@Override
	public void undo() {
		numbers.pop();
		numbers.push(number);
	}

}
