package airwell.rpn.calculator.exception;

public class EmptyNumberStackException extends Exception {
	
	private String message;
	private String command;

	public EmptyNumberStackException(String message, String command) {
		this.message = message;
		this.command = command;
	}

	public String getMessage() {
		return message;
	}

	public String getCommand() {
		return command;
	}
	
}
