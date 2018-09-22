package addressbook.danuka.service.impl;

import org.easymock.EasyMockRunner;
import org.easymock.EasyMockSupport;
import org.easymock.Mock;
import org.easymock.TestSubject;
import org.junit.Test;
import org.junit.runner.RunWith;

import addressbook.danuka.dto.AddressBookDto;
import addressbook.danuka.exception.AddressBookException;
import addressbook.danuka.model.AddressBook;
import addressbook.danuka.repository.AddressBookRepository;
import addressbook.danuka.translator.AddressBookTranslator;

import static org.easymock.EasyMock.expect;

@RunWith(EasyMockRunner.class)
public class DefaultAddressBookServiceTest extends EasyMockSupport {
	
	@TestSubject
	private DefaultAddressBookService defaultAddressBookService;
	@Mock
	private AddressBookRepository addressBookRepository;
	@Mock
	private AddressBookTranslator addressBookTranslator;
	
	@Test
	public void shouldCreateAddressBookSuccessfully() throws AddressBookException {
		AddressBookDto dto = new AddressBookDto();
		AddressBook model = new AddressBook();
		
		expect(addressBookTranslator.translate(dto)).andReturn(model);
		expect(addressBookRepository.save(model));
		
		verifyAll();
		defaultAddressBookService.createAddressBook(dto);
		replayAll();
	}
}
