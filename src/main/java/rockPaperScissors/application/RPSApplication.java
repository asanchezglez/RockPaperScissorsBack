package rockPaperScissors.application;

import java.time.Duration;
import java.util.Arrays;
import java.util.Locale;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import rockPaperScissors.controller.GameController;

@SpringBootApplication
public class RPSApplication implements WebMvcConfigurer{

	public static void main(String[] args) {
		ApplicationContext ctx = SpringApplication.run(RPSApplication.class, args);
	}
	
	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
	 
	    RestTemplate restTemplate = builder
	            .setConnectTimeout(Duration.ofMillis(3000))
	            .setReadTimeout(Duration.ofMillis(3000))
	            .build();
	    restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
	    return restTemplate;
	}

	
	@Bean
	public GameController validatorController() {
		return new GameController();
	}
	
	

}
