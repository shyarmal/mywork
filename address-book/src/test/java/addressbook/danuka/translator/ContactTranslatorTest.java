package addressbook.danuka.translator;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

import java.util.Collections;

import org.junit.Test;

import addressbook.danuka.dto.ContactDto;
import addressbook.danuka.model.Contact;

public class ContactTranslatorTest {

	private ContactTranslator contactTranslator;
	
	@Test
	public void shouldTranslateDtoSuccessfully() {	
		ContactDto dto = new ContactDto();
		dto.setId("id");
		dto.setName("name");
		dto.setNumbers(Collections.singletonList("7372834"));
		
		Contact contact = contactTranslator.translateToModel(dto);
		
		assertThat(contact.getId(), is(dto.getId()));
		assertThat(contact.getName(), is(dto.getName()));
		assertThat(contact.getNumbers(), is(dto.getNumbers()));
	}
	
	@Test
	public void shouldTranslateModelSuccessfully() {	
		Contact model = new Contact();
		model.setId("id");
		model.setName("name");
		model.setNumbers(Collections.singletonList("7372834"));
		
		ContactDto contact = contactTranslator.translateToDto(model);
		
		assertThat(contact.getId(), is(model.getId()));
		assertThat(contact.getName(), is(model.getName()));
		assertThat(contact.getNumbers(), is(model.getNumbers()));
	}
	
	@Test
	public void shouldTranslateNullDtoSuccessfully() {	
		Contact contact = contactTranslator.translateToModel(null);
		
		assertNotNull(contact);
	}
	
	@Test
	public void shouldTranslateNullModelSuccessfully() {	
		ContactDto contact = contactTranslator.translateToDto(null);
		
		assertNotNull(contact);
	}
}
