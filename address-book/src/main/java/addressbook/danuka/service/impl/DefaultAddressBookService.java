package addressbook.danuka.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import addressbook.danuka.dto.AddressBookDto;
import addressbook.danuka.exception.AddressBookException;
import addressbook.danuka.repository.AddressBookRepository;
import addressbook.danuka.service.AddressBookService;
import addressbook.danuka.translator.AddressBookTranslator;

@Service
public class DefaultAddressBookService implements AddressBookService {
	
	@Autowired
	private AddressBookRepository addressBookRepository;
	@Autowired
	private AddressBookTranslator addressBookTranslator;
	
	@Override
	public void createAddressBook(AddressBookDto addressBookDto) throws AddressBookException {
		try {
			addressBookRepository.save(addressBookTranslator.translate(addressBookDto));
		} catch (Exception e) {
			throw new AddressBookException("Failed to create address book", e);
		}
	}
	
}
