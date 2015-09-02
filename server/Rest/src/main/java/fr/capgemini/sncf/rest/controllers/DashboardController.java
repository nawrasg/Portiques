package fr.capgemini.sncf.rest.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
@RequestMapping("/sncf/")
public class DashboardController {
	
	public static String mDashboard;
	
	@ResponseStatus(HttpStatus.ACCEPTED)
	@RequestMapping(value = "/app/dashboard")
	public @ResponseBody Object getDashboard(){
		return mDashboard;
	}

}
