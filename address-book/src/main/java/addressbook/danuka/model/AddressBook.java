package addressbook.danuka.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class AddressBook {

	@Id
	private String id;
	private String userId;
	private String name;
	@OneToMany(mappedBy = "addressBook")
	private List<Contact> contacts;
	
	public AddressBook() {

	}
	
	public AddressBook(String id, String userId, String name) {
		this.id = id;
		this.userId = userId;
		this.name = name;
	}

	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String getUserId() {
		return userId;
	}
	
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public List<Contact> getContacts() {
		return contacts;
	}
	
	public void setContacts(List<Contact> contacts) {
		this.contacts = contacts;
	}	

}
