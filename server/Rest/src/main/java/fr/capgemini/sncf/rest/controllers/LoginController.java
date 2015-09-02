package fr.capgemini.sncf.rest.controllers;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
@RequestMapping("/portiques")
public class LoginController {
	
	public LoginController() {
		// TODO Auto-generated constructor stub
	}
	
	@RequestMapping(value="/auth", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.ACCEPTED)
	public @ResponseBody Object auth(HttpServletRequest client, HttpServletResponse response){
		HashMap<String, String> nMap = new HashMap<String, String>();
		nMap.put("user", "Nawras");
		nMap.put("api", "ABC123");
		nMap.put("type", "0");
		return nMap;
	}
}
