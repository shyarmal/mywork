package assignment.trunc.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * 
 * @author Danuka
 * Used only for test cases to provide the web application context.
 *
 */
@EnableWebMvc
@Configuration
@ComponentScan("assignment.trunc")
public class WebConfig extends WebMvcConfigurerAdapter {


}
