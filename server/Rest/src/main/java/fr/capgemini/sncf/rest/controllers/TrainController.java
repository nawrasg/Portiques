package fr.capgemini.sncf.rest.controllers;

import java.net.UnknownHostException;
import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mongodb.DBCursor;

import fr.capgemini.sncf.db.Request;
import fr.capgemini.sncf.types.Train2;

@Controller
@RequestMapping("/sncf")
public class TrainController {

	@RequestMapping(value="/gares/{gare}/trains")
	public @ResponseBody Object getTrains(@PathVariable int gare) throws UnknownHostException{
		Set<Train2> nSet = new HashSet<Train2>();
		Request nQuery = new Request();
		DBCursor nResult = nQuery.get("trains");
		while(nResult.hasNext()){
			Train2 nTrain = new Train2(nResult.next());
			nSet.add(nTrain);
		}
		return nSet;
	}
}
