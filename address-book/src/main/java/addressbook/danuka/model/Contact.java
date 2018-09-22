package addressbook.danuka.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Contact {

	@Id
	private String id;
	private String name;
	private List<String> numbers;
	@ManyToOne(optional = false)
	private AddressBook addressBook;
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public List<String> getNumbers() {
		return numbers;
	}
	
	public void setNumbers(List<String> numbers) {
		this.numbers = numbers;
	}

	public AddressBook getAddressBook() {
		return addressBook;
	}

	public void setAddressBook(AddressBook addressBook) {
		this.addressBook = addressBook;
	}

	
}
