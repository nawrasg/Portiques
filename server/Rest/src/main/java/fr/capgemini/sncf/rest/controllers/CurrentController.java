package fr.capgemini.sncf.rest.controllers;

import java.net.UnknownHostException;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.mongodb.DBCursor;
import com.mongodb.DBObject;

import fr.capgemini.sncf.db.Request;

@Controller
@RequestMapping("/sncf")
public class CurrentController {
	
	@ResponseStatus(HttpStatus.ACCEPTED)
	@RequestMapping(value="/consumption")
	public @ResponseBody Object getConsumption() throws UnknownHostException{
		Request nQuery = new Request();
		DBCursor nResult = nQuery.get("current");
		DBObject nItem = nResult.next();
		return nItem;
	}
}
