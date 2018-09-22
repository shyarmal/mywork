package addressbook.danuka.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import addressbook.danuka.model.AddressBook;

public interface AddressBookRepository extends JpaRepository<AddressBook, String> {
	
	public AddressBook findByUserIdAndName(String userId, String addressBookName);

}
