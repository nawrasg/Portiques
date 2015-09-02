package fr.capgemini.sncf.rest;

import java.io.IOException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.context.embedded.ErrorPage;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;

@SpringBootApplication
public class Sncf {

	/**
	 * Entry method to load Spring app.
	 * 
	 * @param args
	 * @throws IOException 
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) throws IOException, InterruptedException {
		SpringApplication.run(Sncf.class, args);
	}
		
	/**
	 * Add custom error page to replace the default ones.
	 * @return
	 */
	@Bean
	public EmbeddedServletContainerCustomizer containerCustomizer() {
	 
	    return new EmbeddedServletContainerCustomizer() {
	        public void customize(ConfigurableEmbeddedServletContainer container) {
	        	ErrorPage error400Page = new ErrorPage(HttpStatus.BAD_REQUEST, "/400.html");
	            ErrorPage error404Page = new ErrorPage(HttpStatus.NOT_FOUND, "/404.html");
	 
//	            container.addErrorPages(error400Page, error404Page);
	        }
	    };
	}

}
