package addressbook.danuka.controller;

import java.util.ArrayList;

import org.easymock.EasyMockRunner;
import org.easymock.EasyMockSupport;
import org.easymock.Mock;
import org.easymock.TestSubject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import addressbook.danuka.dto.AddressBookDto;
import addressbook.danuka.dto.ContactDto;
import addressbook.danuka.dto.common.ResponseWrapper;
import addressbook.danuka.exception.AddressBookException;
import addressbook.danuka.service.AddressBookService;
import addressbook.danuka.translator.ResponseTranslator;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.easymock.EasyMock.expect;
import static org.hamcrest.core.Is.is;

@RunWith(EasyMockRunner.class)
public class AddressBookControllerTest extends EasyMockSupport {

	@TestSubject
	private AddressBookController addressBookController;
	@Mock
	private ResponseTranslator responseTranslator;
	@Mock
	private AddressBookService addressBookService;
	
	@Test
	public void shouldCreateAddressBookSuccessfully() throws AddressBookException {
		AddressBookDto addressBookDto = new AddressBookDto();
		addressBookDto.setContacts(new ArrayList<ContactDto>());
		addressBookDto.setName("favorites");
		addressBookDto.setUserId("xxx");
		ResponseWrapper<AddressBookDto> responseWrapper = new ResponseWrapper<>(addressBookDto);
		ResponseEntity<ResponseWrapper<AddressBookDto>> responseEntity = 
				new ResponseEntity<ResponseWrapper<AddressBookDto>>(responseWrapper , HttpStatus.CREATED);
		ResponseEntity<ResponseWrapper<AddressBookDto>> expected =
				new ResponseEntity<ResponseWrapper<AddressBookDto>>(new ResponseWrapper<>(null) , HttpStatus.CREATED);		
		
		addressBookService.createAddressBook(addressBookDto);
		expect(responseTranslator.translate(responseWrapper, HttpStatus.CREATED)).andReturn(responseEntity);
		
		verifyAll();
		ResponseEntity<ResponseWrapper<AddressBookDto>> response = addressBookController.createAddressBook(addressBookDto);
		replayAll();
		
		assertNotNull(response);
		assertThat(response, is(expected));
	}
}
