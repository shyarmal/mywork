package addressbook.danuka.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import addressbook.danuka.dto.AddressBookDto;
import addressbook.danuka.dto.common.ResponseWrapper;
import addressbook.danuka.exception.AddressBookException;
import addressbook.danuka.service.AddressBookService;
import addressbook.danuka.translator.ResponseTranslator;

@Validated
@RequestMapping(value ="{/address-book}", produces = "application/json")
@RestController
public class AddressBookController {
	
	@Autowired
	private AddressBookService addressBookService;
	@Autowired
	private ResponseTranslator responseTranslator;
	
	@ResponseStatus(code = HttpStatus.CREATED)
	@PostMapping(value ="/")
	public ResponseEntity<ResponseWrapper<AddressBookDto>> createAddressBook(@Valid @RequestBody AddressBookDto addressBookDto) throws AddressBookException {
		addressBookService.createAddressBook(addressBookDto);
		return responseTranslator.translate(new ResponseWrapper<AddressBookDto>(null), HttpStatus.CREATED);
	}

}
