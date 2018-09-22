package addressbook.danuka.controller.advise;

import addressbook.danuka.dto.common.Error;
import addressbook.danuka.dto.common.ResponseWrapper;
import addressbook.danuka.exception.AddressBookException;
import addressbook.danuka.exception.ContactException;
import addressbook.danuka.translator.ResponseTranslator;

import javax.validation.ValidationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ErrorHandlingAdvise {
	
	@Autowired
	private ResponseTranslator responseTranslator;

	@ExceptionHandler(ValidationException.class)
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	@ResponseBody
	public ResponseEntity<ResponseWrapper<Error>> handleBadRequestError(ValidationException e) {
		 return buildResponse(HttpStatus.BAD_REQUEST, e.getMessage());
	}
	
	@ExceptionHandler(AddressBookException.class)
	@ResponseStatus(value = HttpStatus.CONFLICT)
	@ResponseBody
	public ResponseEntity<ResponseWrapper<Error>> handleAddressBookFailure(AddressBookException e) {
		return buildResponse(HttpStatus.CONFLICT, e.getMessage());
	}
	
	@ExceptionHandler(ContactException.class)
	@ResponseBody
	public ResponseEntity<ResponseWrapper<Error>> handleContactFailure(ContactException e) {
		if (e.getStatus().equals(ContactException.Status.NOT_FOUND)) {
			return buildResponse(HttpStatus.NOT_FOUND, e.getMessage()); 
		} else {
			return buildResponse(HttpStatus.CONFLICT, e.getMessage());
		}
	}
	
	
	@ExceptionHandler(RuntimeException.class)
	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	@ResponseBody
	public ResponseEntity<ResponseWrapper<Error>> handleRuntimeError(RuntimeException e) {
		return buildResponse(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
	}
	
	private ResponseEntity<ResponseWrapper<Error>> buildResponse(HttpStatus status, String message) {
		return responseTranslator.translate(new ResponseWrapper<Error>(
				 new Error(status.name(), message)), status);
	}
}
