package airwell.rpn.calculator.command;

import java.util.Optional;
import java.util.Stack;

import airwell.rpn.calculator.exception.EmptyNumberStackException;

public class UndoCommand extends Command {
	
	public UndoCommand(Stack<Double> numbers) {
		super("UNDO");
		this.numbers = numbers;
	}
	
	@Override
	public void execute() throws EmptyNumberStackException {
		Optional<Command> command = Optional.ofNullable(previousCommand);
		if(command.isPresent() && !(command.get() instanceof UndoCommand)) {
			command.get().undo();
		} else {
			numbers.pop();
		}
		super.execute();
	}

	@Override
	public void undo() {
		// TODO Auto-generated method stub
		
	}

}
