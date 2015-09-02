package fr.capgemini.sncf.rest;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.stereotype.Component;

@Component
public class CamelProcessor implements Processor{
	private String foo = "bar";

	public void process(Exchange arg0) throws Exception {
		
	}

}
