package fr.capgemini.sncf.rest;

import org.springframework.boot.autoconfigure.web.WebMvcAutoConfiguration.WebMvcAutoConfigurationAdapter;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;

import fr.capgemini.sncf.interceptors.AuthInterceptor;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages="fr.capgemini.sncf.rest")
public class RestAdapter extends WebMvcAutoConfigurationAdapter{

	/**
	 * Used to activate the user request interceptor.
	 * 
	 */
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		// TODO Auto-generated method stub
		super.addInterceptors(registry);
		registry.addInterceptor(new AuthInterceptor());
	}
}
