package addressbook.danuka.service.impl;

import static org.easymock.EasyMock.expect;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

import java.util.Collections;
import java.util.List;

import org.easymock.EasyMockRunner;
import org.easymock.EasyMockSupport;
import org.easymock.Mock;
import org.easymock.TestSubject;
import org.junit.Test;
import org.junit.runner.RunWith;

import addressbook.danuka.dto.ContactDto;
import addressbook.danuka.exception.ContactException;
import addressbook.danuka.model.AddressBook;
import addressbook.danuka.model.Contact;
import addressbook.danuka.repository.AddressBookRepository;
import addressbook.danuka.repository.ContactRepository;
import addressbook.danuka.translator.ContactTranslator;

@RunWith(EasyMockRunner.class)
public class DefaultContactServiceTest extends EasyMockSupport {

	@TestSubject
	private DefaultContactService defaultContactService;
	@Mock
	private ContactRepository contactRepository;
	@Mock
	private AddressBookRepository addressBookRepository;
	@Mock
	private ContactTranslator contactTranslator;
	
	private static final String USER_ID = "USER";
	private static final String ADDRESS_BOOK_NAME = "ADDRESS BOOK";
	private static final String CONTACT_NAME = "Mr. X";
	
	@Test
	public void shouldCreateContactSuccessfully() throws ContactException {
		ContactDto contactDto = new ContactDto();
		AddressBook addressBook = new AddressBook();
		Contact contact = new Contact();
		contact.setAddressBook(addressBook);
		
		expect(addressBookRepository.findByUserIdAndName(USER_ID, ADDRESS_BOOK_NAME)).andReturn(addressBook);
		expect(contactTranslator.translateToModel(contactDto)).andReturn(contact);
		contactRepository.save(contact);
		
		verifyAll();
		defaultContactService.saveContact(USER_ID, ADDRESS_BOOK_NAME, contactDto);
		replayAll();
	}
	
	@Test
	public void shouldDeleteContactSuccessfully() throws ContactException {
		ContactDto contactDto = new ContactDto();
		AddressBook addressBook = new AddressBook();
		Contact contact = new Contact();
		contact.setAddressBook(addressBook);
		
		expect(addressBookRepository.findByUserIdAndName(USER_ID, ADDRESS_BOOK_NAME)).andReturn(addressBook);
		expect(contactTranslator.translateToModel(contactDto)).andReturn(contact);
		contactRepository.delete(contact);
		
		verifyAll();
		defaultContactService.deleteContact(USER_ID, ADDRESS_BOOK_NAME, CONTACT_NAME);
		replayAll();
	}
	
	@Test(expected = ContactException.class)
	public void shouldThrowCreateContactErrors() throws ContactException {
		ContactDto contactDto = new ContactDto();
		AddressBook addressBook = new AddressBook();
		Contact contact = new Contact();
		contact.setAddressBook(addressBook);
		
		expect(addressBookRepository.findByUserIdAndName(USER_ID, ADDRESS_BOOK_NAME)).andReturn(addressBook);
		expect(contactTranslator.translateToModel(contactDto)).andReturn(contact);
		expect(contactRepository.save(contact)).andThrow(new Exception()); 
		
		verifyAll();
		defaultContactService.saveContact(USER_ID, ADDRESS_BOOK_NAME, contactDto);
		replayAll();
	}
	
	@Test(expected = ContactException.class)
	public void shouldThrowDeleteContactErrors() throws ContactException {
		ContactDto contactDto = new ContactDto();
		AddressBook addressBook = new AddressBook();
		
		expect(addressBookRepository.findByUserIdAndName(USER_ID, ADDRESS_BOOK_NAME)).andReturn(addressBook);
		expect(contactTranslator.translateToModel(contactDto)).andThrow(new Exception()); 

		verifyAll();
		defaultContactService.deleteContact(USER_ID, ADDRESS_BOOK_NAME, CONTACT_NAME);
		replayAll();
	}
	
	@Test
	public void shouldRetrieveContactSuccessfully() throws ContactException {
		ContactDto contactDto = new ContactDto();
		AddressBook addressBook = new AddressBook();
		Contact contact = new Contact();
		contact.setAddressBook(addressBook);
		
		expect(addressBookRepository.findByUserIdAndName(USER_ID, ADDRESS_BOOK_NAME)).andReturn(addressBook);
		expect(contactTranslator.translateToDto(contact)).andReturn(contactDto);
		expect(contactRepository.findByAddressBookAndName(addressBook, CONTACT_NAME)).andReturn(contact);
		
		verifyAll();
		ContactDto result = defaultContactService.findContact(USER_ID, ADDRESS_BOOK_NAME, CONTACT_NAME);
		replayAll();
		
		assertThat(result, is(contactDto));
	}
	
	@Test
	public void shouldRetrieveContactsSuccessfully() throws ContactException {
		ContactDto contactDto = new ContactDto();
		AddressBook addressBook = new AddressBook();
		List<Contact> contacts = Collections.singletonList(new Contact());
		List<ContactDto> dtoList = Collections.singletonList(contactDto);
		
		expect(addressBookRepository.findByUserIdAndName(USER_ID, ADDRESS_BOOK_NAME)).andReturn(addressBook);
		expect(contactTranslator.translateToDto(new Contact())).andReturn(contactDto).times(1);
		expect(contactRepository.findByAddressBook(addressBook)).andReturn(contacts);
		
		verifyAll();
		List<ContactDto> result = defaultContactService.findContacts(USER_ID, ADDRESS_BOOK_NAME);
		replayAll();
		
		assertThat(result, is(dtoList));
	}
	
	@Test
	public void shouldRetrieveContactsFromAddressBooksSuccessfully() throws ContactException {
		ContactDto contactDto = new ContactDto();
		AddressBook addressBook = new AddressBook();
		List<Contact> contacts = Collections.singletonList(new Contact());
		List<ContactDto> dtoList = Collections.singletonList(contactDto);
		
		expect(addressBookRepository.findByUserIdAndName(USER_ID, ADDRESS_BOOK_NAME)).andReturn(addressBook);
		expect(contactTranslator.translateToDto(new Contact())).andReturn(contactDto).times(1);
		expect(contactRepository.findByNames(Collections.singletonList(CONTACT_NAME))).andReturn(contacts);
		
		verifyAll();
		List<ContactDto> result = defaultContactService.findContacts(Collections.singletonList(CONTACT_NAME));
		replayAll();
		
		assertThat(result, is(dtoList));
	}
	
	@Test(expected = ContactException.class)
	public void shouldThrowErrorsFromRetrieveContacts() throws ContactException {
		AddressBook addressBook = new AddressBook();
		
		expect(addressBookRepository.findByUserIdAndName(USER_ID, ADDRESS_BOOK_NAME)).andReturn(addressBook);
		expect(contactTranslator.translateToDto(new Contact())).andThrow(new Exception());
		
		verifyAll();
		defaultContactService.findContacts(USER_ID, ADDRESS_BOOK_NAME);
		replayAll();
	}
	
	@Test(expected = ContactException.class)
	public void shouldThrowErrorsFromRetrieveContactsFromAddressBooks() throws ContactException {
		ContactDto contactDto = new ContactDto();
		AddressBook addressBook = new AddressBook();
		
		expect(addressBookRepository.findByUserIdAndName(USER_ID, ADDRESS_BOOK_NAME)).andReturn(addressBook);
		expect(contactTranslator.translateToDto(new Contact())).andReturn(contactDto).andThrow(new Exception());
		
		verifyAll();
		defaultContactService.findContacts(Collections.singletonList(CONTACT_NAME));
		replayAll();
		
	}
}
