package addressbook.danuka.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import addressbook.danuka.dto.ContactDto;
import addressbook.danuka.dto.common.ResponseWrapper;
import addressbook.danuka.exception.ContactException;
import addressbook.danuka.service.ContactService;
import addressbook.danuka.translator.ResponseTranslator;

@Validated
@RequestMapping(value ="{/address-book}", produces = "application/json")
@RestController
public class ContactController {
	@Autowired
	private ContactService contactService;
	@Autowired
	private ResponseTranslator responseTranslator;
	
	@ResponseStatus(code = HttpStatus.CREATED)
	@PostMapping(value = "/{userId}/{addressBook}/contact")
	public ResponseEntity<ResponseWrapper<ContactDto>> addNewContact(@PathVariable String userId, 
			@PathVariable String addressBook, @RequestBody ContactDto contactDto) throws ContactException {
		contactService.saveContact(userId, addressBook, contactDto);
		return responseTranslator.translate(new ResponseWrapper<>(new ContactDto()), HttpStatus.CREATED);
	}
	
	@ResponseStatus(code = HttpStatus.ACCEPTED)
	@DeleteMapping(value = "/{userId}/{addressBook}/contact")
	public ResponseEntity<ResponseWrapper<ContactDto>> deleteContact(@PathVariable String userId, 
			@PathVariable String addressBook, @RequestParam String name) throws ContactException {
		contactService.deleteContact(userId, addressBook, name);
		return responseTranslator.translate(new ResponseWrapper<>(new ContactDto()), HttpStatus.ACCEPTED);
	}
	
	@ResponseStatus(code = HttpStatus.OK)
	@GetMapping(value = "/{userId}/{addressBook}/contact")
	public ResponseEntity<ResponseWrapper<List<ContactDto>>> getAddressBookContacts(@PathVariable String userId, 
			@PathVariable String addressBook) throws ContactException {
		List<ContactDto> contacts = contactService.findContacts(userId, addressBook);
		return responseTranslator.translate(new ResponseWrapper<>(contacts), HttpStatus.OK);
	}
	
	@ResponseStatus(code = HttpStatus.OK)
	@GetMapping(value = "/{userId}/contacts")
	public ResponseEntity<ResponseWrapper<List<ContactDto>>> getContactsFromAddressBooks(@RequestBody List<String> contacts) throws ContactException {	
		List<ContactDto> contactList = contactService.findContacts(contacts);
		return responseTranslator.translate(new ResponseWrapper<>(contactList), HttpStatus.OK);
	}
}
