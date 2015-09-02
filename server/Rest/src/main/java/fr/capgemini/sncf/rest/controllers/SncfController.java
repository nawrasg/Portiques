package fr.capgemini.sncf.rest.controllers;

import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.mongodb.DBCursor;

import fr.capgemini.sncf.db.Request;
import fr.capgemini.sncf.db.Update;
import fr.capgemini.sncf.types.Error;
import fr.capgemini.sncf.types.Train;

@Controller
@RequestMapping("/sncf/trains")
public class SncfController {
	public static Map<Integer, Train> mTrainList; //cache
	
	@Autowired
	private SimpMessagingTemplate mMessageTemplate; //Object to send messages through socket

	public SncfController() throws UnknownHostException {
		mTrainList  = new HashMap<Integer, Train>();
		loadTrains();
	}
	
	/**
	 * Get all trains available in cache.
	 * 
	 * @param client
	 * @return JSON Array
	 */
	@RequestMapping(method = RequestMethod.GET)
	public @ResponseBody Object getDetailsTrains(HttpServletRequest client){
		return mTrainList.values();
	}
	
	/**
	 * Get train details
	 * 
	 * @param gare
	 * @param train
	 * @return
	 */
	@RequestMapping(value="/gares/{cp}/trains/{train}")
	public @ResponseBody Object getTrainDetails(@PathVariable Integer gare, @PathVariable Integer train){
		if (mTrainList.containsKey(train)) {
			return mTrainList.get(train);
		}
		return new Error(404, "Requested train not found!");
	}

	/**
	 * DEPRECATED
	 * Get a train details.
	 * 
	 * @param trainId : the train number.
	 * @param client
	 * @return JSON Object : Train if found, Error if not.
	 */
	@Deprecated
	@RequestMapping(value="/{trainId}", method = RequestMethod.GET)
	public @ResponseBody Object getDetailsTrain(@PathVariable Integer trainId, HttpServletRequest client) {
		if (mTrainList.containsKey(trainId)) {
			return mTrainList.get(trainId);
		}
		return new Error(404, "Request train doesn't exist!");
	}
	
	/**
	 * Request update server cache and notify subscribed clients to update their data.
	 * 
	 * @param value : the train travellers (for simulation).
	 * @return JSON Object : updated train n°8667.
	 * @throws Exception
	 */
	@RequestMapping(value="/{value}", method = RequestMethod.PUT)
	@ResponseStatus(value = HttpStatus.ACCEPTED)
	public @ResponseBody Object updateTrainList(@PathVariable Integer value) throws Exception{
		updateDB(value); //simulate a new value
		
		loadTrains(); //load cache from database
		
		JSONObject nResponse = 	new JSONObject();
		nResponse.put("content", "update");
		mMessageTemplate.convertAndSend("/topic/update", nResponse.toString()); //send notifications through websockets
		
		return mTrainList.get(8667);
	}
	
	/**
	 * Load trains data from database.
	 * 
	 * @throws UnknownHostException
	 */
	public static void loadTrains() throws UnknownHostException{
		Request nQuery = new Request("localhost", "sncf");
		DBCursor nCursor = nQuery.get("gt35000");
		while(nCursor.hasNext()){
			Train nTrain = new Train(nCursor.next());
			mTrainList.put(nTrain.getTrainId(), nTrain);
		}
	}
	
	/**
	 * Update the number of travellers for train n°8667 in the database.
	 * 	
	 * @param value
	 * @throws UnknownHostException
	 */
	private void updateDB(Integer value) throws UnknownHostException{
		Update nUpdate = new Update("localhost", "sncf");
		nUpdate.updateValue(value);
	}

}
