package fr.capgemini.sncf.rest.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
@RequestMapping("/sncf")
public class DiversController {

	@RequestMapping(value="/ping")
	@ResponseStatus(HttpStatus.ACCEPTED)
	public void ping(){
		//TODO ?!
	}
}
