package addressbook.danuka.dto;

import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

public class ContactDto {

	private String id;
	@NotNull
	@NotBlank
	private String name;
	@Size(min =1)
	private List<String> numbers;
	
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

}
