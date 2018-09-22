package addressbook.danuka.service;

import addressbook.danuka.dto.AddressBookDto;
import addressbook.danuka.exception.AddressBookException;

public interface AddressBookService {
	
	public void createAddressBook(AddressBookDto addressBookDto) throws AddressBookException;

}
