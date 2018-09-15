package airwell.rpn.calculator.command;

import java.util.Stack;

import airwell.rpn.calculator.exception.EmptyNumberStackException;

public class ClearCommand extends Command {	
	
	public ClearCommand(Stack<Double> numbers) {
		super("CLEAR");
		this.numbers = numbers;
	}
	
	@Override
	public void execute() throws EmptyNumberStackException {
		while(numberStackSize > 0) {
			numbers.remove(0);	
			numberStackSize--;
		}
		super.execute();
	}

	@Override
	public void undo() {

	}

}
