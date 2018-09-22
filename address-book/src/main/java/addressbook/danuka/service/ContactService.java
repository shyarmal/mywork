package addressbook.danuka.service;

import java.util.List;

import addressbook.danuka.dto.ContactDto;
import addressbook.danuka.exception.ContactException;

public interface ContactService {

	public void deleteContact(String userId, String addressBook, String name) throws ContactException;
	
	public ContactDto findContact(String userId, String addressBook, String name) throws ContactException;
	
	public List<ContactDto> findContacts(List<String> names) throws ContactException;
	
	public List<ContactDto> findContacts(String userId, String addressBook) throws ContactException;
	
	public void saveContact(String userId, String addressBook, ContactDto contactDto) throws ContactException;
}
