package addressbook.danuka.translator;

import java.util.Optional;

import org.springframework.stereotype.Component;

import addressbook.danuka.model.Contact;
import addressbook.danuka.dto.ContactDto;

@Component
public class ContactTranslator {
	
	public Contact translateToModel(ContactDto contact) {
		Contact model = new Contact();
		Optional.ofNullable(contact).ifPresent(con -> {
			model.setId(con.getId());
			model.setName(con.getName());
			model.setNumbers(con.getNumbers());
		});
		return model;
	}
	
	public ContactDto translateToDto(Contact contact) {
		ContactDto dto = new ContactDto();
		Optional.ofNullable(contact).ifPresent(con -> {
			dto.setId(con.getId());
			dto.setName(con.getName());
			dto.setNumbers(con.getNumbers());
		});
		return dto;
	}

}
