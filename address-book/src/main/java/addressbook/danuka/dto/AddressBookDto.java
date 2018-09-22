package addressbook.danuka.dto;

import java.util.List;

import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotBlank;

public class AddressBookDto {

	private String id;
	@NotNull
	@NotBlank
	private String userId;
	@NotNull
	@NotBlank
	private String name;
	private List<ContactDto> contacts;
	
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
	
	public List<ContactDto> getContacts() {
		return contacts;
	}
	
	public void setContacts(List<ContactDto> contacts) {
		this.contacts = contacts;
	}	

}
