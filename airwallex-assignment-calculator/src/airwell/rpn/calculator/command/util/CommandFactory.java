package airwell.rpn.calculator.command.util;

import java.util.Stack;

import airwell.rpn.calculator.Operator;
import airwell.rpn.calculator.command.AddCommand;
import airwell.rpn.calculator.command.ClearCommand;
import airwell.rpn.calculator.command.Command;
import airwell.rpn.calculator.command.DivideCommand;
import airwell.rpn.calculator.command.MinusCommand;
import airwell.rpn.calculator.command.MultiplyCommand;
import airwell.rpn.calculator.command.SqrtCommand;
import airwell.rpn.calculator.command.UndoCommand;

public class CommandFactory {

	public Command buildCommand(String operator, Stack<Double> numbers) {
		Command command = null;
		switch (operator) {
			case "clear":
				command = new ClearCommand(numbers);
				break;
			case "/":
				command = new DivideCommand(numbers);
				break;
			case "-":
				command = new MinusCommand(numbers);
				break;
			case "*":
				command = new MultiplyCommand(numbers);
				break;
			case "+":
				command = new AddCommand(numbers);
				break;
			case "sqrt":
				command = new SqrtCommand(numbers);
				break;
			case "undo":
				command = new UndoCommand(numbers);
				break;
			default: throw new IllegalArgumentException("Invalid operand found");
		}
		return command;
	}
}
