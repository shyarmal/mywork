package addressbook.danuka.exception;

public class ContactException extends Exception {

	private Status status;
	
	public enum Status {
		NOT_FOUND, OPERATION_ERROR
	}
	
	public ContactException() {
		super();
	}

	public ContactException(String message, Throwable cause, Status status) {
		super(message, cause);
		this.status = status;
	}

	public ContactException(String message, Status status) {
		super(message);
		this.status = status;
	}

	public Status getStatus() {
		return status;
	}
}
