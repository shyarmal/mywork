package addressbook.danuka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

@SpringBootApplication
@ComponentScan(basePackages = {"addressbook.danuka"})
@EntityScan(value = "addressbook.danuka")
@EnableTransactionManagement
public class AddressBookApi {

	public static void main(String[] args) {
		SpringApplication.run(AddressBookApi.class, args);
	}

}
