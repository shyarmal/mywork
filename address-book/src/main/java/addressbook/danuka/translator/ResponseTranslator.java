package addressbook.danuka.translator;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import addressbook.danuka.dto.common.ResponseWrapper;

@Component
public class ResponseTranslator {

	public <T> ResponseEntity<ResponseWrapper<T>> translate(ResponseWrapper<T> responseWrapper, HttpStatus status) {
		
		return new ResponseEntity<ResponseWrapper<T>>(responseWrapper, status);
	}
}
