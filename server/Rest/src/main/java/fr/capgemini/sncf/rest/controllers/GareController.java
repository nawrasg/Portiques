package fr.capgemini.sncf.rest.controllers;

import java.net.UnknownHostException;
import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mongodb.DBCursor;

import fr.capgemini.sncf.db.Request;
import fr.capgemini.sncf.types.Gare;

@Controller
@RequestMapping("/portiques")
public class GareController {
	
	@RequestMapping(value="/gares")
	public @ResponseBody Object get() throws UnknownHostException{
		Request nQuery = new Request();
		DBCursor nCursor = nQuery.get("gares");
		Set<Gare> nSet = new HashSet<Gare>();
		while(nCursor.hasNext()){
			Gare nGare = new Gare(nCursor.next());
			nSet.add(nGare);
		}
		return nSet;
	}

}
