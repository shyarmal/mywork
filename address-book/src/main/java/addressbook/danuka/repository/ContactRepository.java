package addressbook.danuka.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import addressbook.danuka.model.AddressBook;
import addressbook.danuka.model.Contact;

@Repository
public interface ContactRepository extends JpaRepository<Contact, String>{

	Contact findByAddressBookAndName(AddressBook addressBook, String name);
	
	List<Contact> findByNames(List<String> names);

	List<Contact> findByAddressBook(AddressBook addressBook);
}
