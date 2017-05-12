package security.springboot.example.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	private static final String USER = "USER_ROLE";
	private static final String ADMIN = "ADMIN_ROLE";

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
						.antMatchers("/accounts/details/private/*").hasRole(USER)
						.antMatchers("/accounts/details/private/**").hasRole(ADMIN)
						.and().formLogin();
	}
	
	@Autowired
	public void configureGlobal (AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication()
			.withUser("Danuka").password("123").roles(USER)
			.and()
			.withUser("Shyarmal").password("password").roles(USER, ADMIN);
	}
}
