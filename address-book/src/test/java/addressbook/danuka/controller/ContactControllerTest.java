package addressbook.danuka.controller;

import java.util.Collections;
import java.util.List;

import org.easymock.EasyMockRunner;
import org.easymock.EasyMockSupport;
import org.easymock.Mock;
import org.easymock.TestSubject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import addressbook.danuka.dto.ContactDto;
import addressbook.danuka.dto.common.ResponseWrapper;
import addressbook.danuka.exception.ContactException;
import addressbook.danuka.service.ContactService;
import addressbook.danuka.translator.ResponseTranslator;

import static org.easymock.EasyMock.expect;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

@RunWith(EasyMockRunner.class)
public class ContactControllerTest extends EasyMockSupport {

	@TestSubject
	private ContactController contactController;	
	@Mock
	private ContactService contactService;
	@Mock
	private ResponseTranslator responseTranslator;
	
	private static final String USER_ID = "USER";
	private static final String ADDRESS_BOOK_NAME = "ADDRESS BOOK";
	private static final String CONTACT_NAME = "Mr. X";
	
	@Test
	public void shouldCreateContactSuccessfully() throws ContactException {
		ContactDto contactDto = new ContactDto();
		contactDto.setName("Mr. X");
		contactDto.setNumbers(Collections.singletonList("47388993"));
		
		ResponseWrapper<ContactDto> responseWrapper = new ResponseWrapper<ContactDto>(contactDto);
		ResponseEntity<ResponseWrapper<ContactDto>> responseEntity = 
				new ResponseEntity<ResponseWrapper<ContactDto>>(responseWrapper , HttpStatus.CREATED);
		ResponseEntity<ResponseWrapper<ContactDto>> expected =
				new ResponseEntity<ResponseWrapper<ContactDto>>(new ResponseWrapper<>(null) , HttpStatus.CREATED);
		
		contactService.saveContact(USER_ID, ADDRESS_BOOK_NAME, contactDto);		
		expect(responseTranslator.translate(responseWrapper, HttpStatus.CREATED)).andReturn(responseEntity);
		
		verifyAll();
		ResponseEntity<ResponseWrapper<ContactDto>> response = 
				contactController.addNewContact(USER_ID, ADDRESS_BOOK_NAME, contactDto);
		replayAll();
		
		assertNotNull(response);
		assertThat(response, is(expected));
	}
	
	@Test
	public void shouldDeleteContactSuccessfully() throws ContactException {
		ContactDto contactDto = new ContactDto();
		contactDto.setName(CONTACT_NAME);
		contactDto.setNumbers(Collections.singletonList("47388993"));
		
		ResponseWrapper<ContactDto> responseWrapper = new ResponseWrapper<ContactDto>(contactDto);
		ResponseEntity<ResponseWrapper<ContactDto>> responseEntity = 
				new ResponseEntity<ResponseWrapper<ContactDto>>(responseWrapper , HttpStatus.ACCEPTED);
		ResponseEntity<ResponseWrapper<ContactDto>> expected =
				new ResponseEntity<ResponseWrapper<ContactDto>>(new ResponseWrapper<>(null) , HttpStatus.CREATED);
		
		contactService.deleteContact(USER_ID, ADDRESS_BOOK_NAME, CONTACT_NAME);		
		expect(responseTranslator.translate(responseWrapper, HttpStatus.ACCEPTED)).andReturn(responseEntity);
		
		verifyAll();
		ResponseEntity<ResponseWrapper<ContactDto>> response = 
				contactController.deleteContact(USER_ID, ADDRESS_BOOK_NAME, CONTACT_NAME);
		replayAll();
		
		assertNotNull(response);
		assertThat(response, is(expected));
	}
	
	@Test
	public void shouldRetrieveAddressBookContactsSuccessfully() throws ContactException {
		ContactDto contactDto = new ContactDto();
		contactDto.setName(CONTACT_NAME);
		contactDto.setNumbers(Collections.singletonList("47388993"));
		List<ContactDto> contactList = Collections.singletonList(contactDto);
		
		ResponseWrapper<List<ContactDto>> responseWrapper = new ResponseWrapper<List<ContactDto>>(contactList);
		ResponseEntity<ResponseWrapper<List<ContactDto>>> responseEntity = 
				new ResponseEntity<ResponseWrapper<List<ContactDto>>>(responseWrapper , HttpStatus.OK);
		
		contactService.findContact(USER_ID, ADDRESS_BOOK_NAME, CONTACT_NAME);		
		expect(responseTranslator.translate(responseWrapper, HttpStatus.ACCEPTED)).andReturn(responseEntity);
		
		verifyAll();
		ResponseEntity<ResponseWrapper<List<ContactDto>>> response = 
				contactController.getAddressBookContacts(USER_ID, ADDRESS_BOOK_NAME);
		replayAll();
		
		assertNotNull(response);
		assertThat(response, is(responseEntity));
	}
	
	@Test
	public void shouldRetrieveContactsFromAddressBooksSuccessfully() throws ContactException {
		ContactDto contactDto = new ContactDto();
		contactDto.setName(CONTACT_NAME);
		contactDto.setNumbers(Collections.singletonList("47388993"));
		List<ContactDto> contactList = Collections.singletonList(contactDto);
		
		ResponseWrapper<List<ContactDto>> responseWrapper = new ResponseWrapper<List<ContactDto>>(contactList);
		ResponseEntity<ResponseWrapper<List<ContactDto>>> responseEntity = 
				new ResponseEntity<ResponseWrapper<List<ContactDto>>>(responseWrapper , HttpStatus.OK);
		
		contactService.findContacts(Collections.singletonList(CONTACT_NAME));		
		expect(responseTranslator.translate(responseWrapper, HttpStatus.ACCEPTED)).andReturn(responseEntity);
		
		verifyAll();
		ResponseEntity<ResponseWrapper<List<ContactDto>>> response = 
				contactController.getContactsFromAddressBooks(Collections.singletonList(CONTACT_NAME));
		replayAll();
		
		assertNotNull(response);
		assertThat(response, is(responseEntity));
	}
}
