package fr.capgemini.sncf.types;

import org.apache.camel.Exchange;
import org.springframework.stereotype.Component;

@Component
public class NawrasBean {
	
	public NawrasBean(){
		
				System.out.println("Hello");

	}
	
	public void process(Exchange exchange) throws Exception {
		System.out.println(exchange.toString());
	}
	
	

}
