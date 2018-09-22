package addressbook.danuka.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import addressbook.danuka.dto.ContactDto;
import addressbook.danuka.exception.ContactException;
import addressbook.danuka.model.AddressBook;
import addressbook.danuka.model.Contact;
import addressbook.danuka.repository.AddressBookRepository;
import addressbook.danuka.repository.ContactRepository;
import addressbook.danuka.service.ContactService;
import addressbook.danuka.translator.ContactTranslator;


@Service
public class DefaultContactService implements ContactService {
	
	@Autowired
	private ContactRepository contactRepository;
	@Autowired
	private AddressBookRepository addressBookRepository;
	@Autowired
	private ContactTranslator contactTranslator;
	
	@Override
	public void deleteContact(String userId, String addressBookName, String name) throws ContactException {
		try {
			ContactDto contactDto = findContact(userId, addressBookName, name);
			contactRepository.delete(contactTranslator.translateToModel(contactDto));	
		} catch (Exception e) {
			throw new ContactException("Failed to delete contact", ContactException.Status.OPERATION_ERROR);
		}		
		
	}
	
	@Override
	public ContactDto findContact(String userId, String addressBookName, String name)  throws ContactException {
		try {
			AddressBook addressBook = addressBookRepository.findByUserIdAndName(userId, addressBookName);
			Contact contact = Optional
					.ofNullable(contactRepository.findByAddressBookAndName(addressBook, name))
					.orElseThrow(() -> new ContactException("No such contact found", 
							ContactException.Status.OPERATION_ERROR));
			return contactTranslator.translateToDto(contact);
		} catch (ContactException e) {
			throw e;
		} catch (Exception e) {
			throw new ContactException("Failed to find contact", e, ContactException.Status.OPERATION_ERROR);
		}
	}
	
	@Override
	public List<ContactDto> findContacts(List<String> names)  throws ContactException {
		try {
			List<Contact> contacts = contactRepository.findByNames(names);
			if (contacts.size() == 0)
				throw new ContactException("Failed to find contacts", ContactException.Status.NOT_FOUND);
			return contacts.stream().map(contact -> contactTranslator.translateToDto(contact))
					.collect(Collectors.toList());
		} catch (ContactException e) {
			throw e;
		} catch (Exception e) {
			throw new ContactException("Failed to find contacts", ContactException.Status.OPERATION_ERROR);
		}
	}

	@Override
	public void saveContact(String userId, String addressBookName, ContactDto contactDto) throws ContactException {
		try {
			AddressBook addressBook = addressBookRepository.findByUserIdAndName(userId, addressBookName);
			Contact contact = contactTranslator.translateToModel(contactDto);
			contact.setAddressBook(addressBook);
			contactRepository.save(contact);
		} catch (Exception e) {
			throw new ContactException("Failed to save contacts", ContactException.Status.OPERATION_ERROR);
		}
	}

	@Override
	public List<ContactDto> findContacts(String userId, String addressBookName) throws ContactException {
		try {
			AddressBook addressBook = addressBookRepository.findByUserIdAndName(userId, addressBookName);
			List<Contact> contacts = contactRepository.findByAddressBook(addressBook);
			if (contacts.size() == 0)
				throw new ContactException("Failed to find contacts", ContactException.Status.NOT_FOUND);
			return contacts.stream().map(contact -> contactTranslator.translateToDto(contact))
					.collect(Collectors.toList());
		} catch (ContactException e) {
			throw e;
		} catch (Exception e) {
			throw new ContactException("Failed to find contacts", ContactException.Status.OPERATION_ERROR);
		}
	}

	
	
}
