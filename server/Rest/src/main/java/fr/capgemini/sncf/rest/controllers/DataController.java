package fr.capgemini.sncf.rest.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/sncf")
public class DataController {

	@RequestMapping(value="/gares/{gare}/data")
	public @ResponseBody Object getData(@PathVariable int gare){
		
		return null;
	}
}
