package airwell.rpn.calculator.command;

import java.util.Stack;

import airwell.rpn.calculator.exception.EmptyNumberStackException;

public abstract class Command {

	protected Command previousCommand;
	protected Stack<Double> numbers;
	protected static int numberStackSize = 0;
	protected String name = null;
	
	protected Command(String name) {
		this.name = name;
	}
	
	public void execute() throws EmptyNumberStackException {
		numberStackSize = numbers.size();
	}
	
	public abstract void undo();

	public void setPreviousCommand(Command previousCommand) {
		this.previousCommand = previousCommand;
	}
	
	public String getName() {
		return this.name;
	}
}
