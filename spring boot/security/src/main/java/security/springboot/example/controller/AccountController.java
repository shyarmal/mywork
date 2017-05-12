package security.springboot.example.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccountController {

	@RequestMapping(value = "/accounts/details/public/{userId}")
	public String displayPublicAccountDetails (@PathVariable int userId) {
		return "public account details " + userId;
	}
	
	@RequestMapping(value = "/accounts/details/private/{userId}")
	public String displayPrivateAccountDetails (@PathVariable int userId) {
		return "private account details " + userId;
	}
	
	@RequestMapping(value = "/accounts/details/private/admin/{userId}")
	public String displayPrivateAdminAccountDetails (@PathVariable int userId) {
		return "private admin account details " + userId;
	}
}
