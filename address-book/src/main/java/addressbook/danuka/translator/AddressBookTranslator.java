package addressbook.danuka.translator;

import java.util.Optional;

import org.springframework.stereotype.Component;
import addressbook.danuka.dto.AddressBookDto;
import addressbook.danuka.model.AddressBook;

@Component
public class AddressBookTranslator {

	public AddressBook translate(AddressBookDto addressBookDto) {
		final AddressBook addressBook = new AddressBook();
		Optional.ofNullable(addressBookDto).ifPresent(dto -> {
			addressBook.setId(dto.getId());
			addressBook.setName(dto.getName());
			addressBook.setUserId(dto.getUserId());
		});
		return addressBook;
	}
}
