package addressbook.danuka.translator;

import org.junit.Test;

import addressbook.danuka.dto.AddressBookDto;
import addressbook.danuka.model.AddressBook;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

public class AddressBookTranslatorTest {

	private AddressBookTranslator addressBookTranslator;
	
	@Test
	public void shouldTranslateSuccessfully() {
		AddressBookDto addressBookDto = new AddressBookDto();
		addressBookDto.setId("id");
		addressBookDto.setName("name");
		addressBookDto.setUserId("user id");
		
		AddressBook addressBook = addressBookTranslator.translate(addressBookDto);
		
		assertThat(addressBook.getId(), is(addressBookDto.getId()));
		assertThat(addressBook.getName(), is(addressBookDto.getName()));
		assertThat(addressBook.getUserId(), is(addressBookDto.getUserId()));
	}
	
	@Test
	public void shouldTranslateNullDtoSuccessfully() {	
		AddressBook addressBook = addressBookTranslator.translate(null);
		
		assertNotNull(addressBook);
	}
}
