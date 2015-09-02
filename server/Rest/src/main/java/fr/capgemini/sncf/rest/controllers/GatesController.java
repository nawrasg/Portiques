package fr.capgemini.sncf.rest.controllers;

import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mongodb.DBCursor;

import fr.capgemini.sncf.db.Request;
import fr.capgemini.sncf.types.Error;
import fr.capgemini.sncf.types.Gate;

@Controller
@RequestMapping("/sncf")
public class GatesController {
	private static Map<String, Gate> mGateList; //cache
	private JSONObject nJson;
	
	@Autowired
	private SimpMessagingTemplate mMessageTemplate; //Object to send messages through socket
	
	public GatesController() throws UnknownHostException, JSONException{
		mGateList = new HashMap<String, Gate>();
		loadGates();
		nJson = new JSONObject("{\"profil\":[\"interactiveChannel: Face\",\"audienceLevel : Contrat\",\"audienceIdClient : 123\",\"audienceIdContrat : 123\"],\"context\":[\"test:bla\"]}");
			
	}

	public static void loadGates() throws UnknownHostException {
		Request nQuery = new Request("localhost", "sncf");
		DBCursor nCursor = nQuery.get("gates");
		while(nCursor.hasNext()){
			Gate nGate = new Gate(nCursor.next());
			mGateList.put(nGate.getId(), nGate);
		}
	}
	
	@RequestMapping(value="/gares/{cp}/gates", method = RequestMethod.GET)
	public @ResponseBody Object getGates(@PathVariable int cp, HttpServletRequest client){
//		return mGateList.values();
		Set<Gate> nSet = new HashSet<Gate>();
		for (Map.Entry<String, Gate> nEntry : mGateList.entrySet()){
		    Gate nGate = nEntry.getValue();
		    if(nGate.getCp() == cp){
		    	nSet.add(nGate);
		    }
		}
		return nSet;
	}
	
	@RequestMapping(value="/gares/{gare}/gates/{gate}", method = RequestMethod.GET)
	public @ResponseBody Object getGate(@PathVariable int gare, @PathVariable String gate){
		if (mGateList.containsKey(gate)) {
			return mGateList.get(gate);
		}
		return new Error(404, "Requested gate not found!");
	}
	
	
}
