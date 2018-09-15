package airwell.rpn.calculator.command.util;

import java.util.Stack;

import airwell.rpn.calculator.command.Command;
import airwell.rpn.calculator.exception.EmptyNumberStackException;

public class CommandExecutor {

	private Stack<Command> commandStack = new Stack<Command>();
	private Stack<Command> excecutedCommandStack = new Stack<Command>();
	
	public void execute() {
		commandStack.forEach(command -> {
			try {
				if (!excecutedCommandStack.contains(command)) {
					command.execute();
					excecutedCommandStack.push(command);
				}					
			} catch (EmptyNumberStackException e) {
				String.format("Operation, %s skipped due to %s", e.getCommand(), e.getMessage());
				throw new RuntimeException(e);
			}
		});
	}
	
	public void registerCommand(Command command, boolean setPrevious) {
		registerCommand(command, commandStack, setPrevious);
	}
	
	private void registerCommand(Command command, Stack<Command> stack, boolean setPrevious) {
		if (setPrevious && command.getName().equals("UNDO")) {
			setPrevisouCommand(command, stack);
		}		
		stack.push(command);
	}
	
	private void setPrevisouCommand(Command command, Stack<Command> stack) {
		if (!stack.isEmpty()) {
			Command previousCommand = stack.peek();
			if (previousCommand.getName().equals("UNDO")) {
				Stack<Command> newStack = new Stack<Command>();
				newStack.addAll(stack.subList(0, stack.size() - 1));
				setPrevisouCommand(command, newStack);
				return;
			}
			command.setPreviousCommand(stack.peek());
		}		
	}
}
