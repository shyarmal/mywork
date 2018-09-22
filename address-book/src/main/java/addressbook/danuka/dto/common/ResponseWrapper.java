package addressbook.danuka.dto.common;

public class ResponseWrapper<T> {

	private T data;
	private Error error;
	
	public ResponseWrapper(T data, Error error) {
		this.error = error;
	}
	
	public ResponseWrapper(T data) {
		this.data = data;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public Error getError() {
		return error;
	}

	public void setError(Error error) {
		this.error = error;
	}
	
	
}
