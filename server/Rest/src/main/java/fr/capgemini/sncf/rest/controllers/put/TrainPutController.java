package fr.capgemini.sncf.rest.controllers.put;

import java.net.UnknownHostException;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

import fr.capgemini.sncf.db.Update;
import fr.capgemini.sncf.rest.controllers.SncfController;

@Controller
@RequestMapping("/sncf/")
public class TrainPutController {
	@Autowired
	private SimpMessagingTemplate mMessageTemplate; //Object to send messages through socket

	@RequestMapping(value = "gares/{gare}/trains/{train}/{status}")
	@ResponseStatus(value = HttpStatus.ACCEPTED)
	private void updateTrainStatus(@PathVariable int gare, @PathVariable int train, @PathVariable String status) throws UnknownHostException, JSONException{
		Update nStatusUpdate = new Update();
		if(status.equals("go")){
			nStatusUpdate.updateStatus(train, 1);
		}else{
			nStatusUpdate.updateStatus(train, 0);
		}
		
		SncfController.loadTrains();
		
		JSONObject nResponse = 	new JSONObject();
		nResponse.put("content", "update");
		mMessageTemplate.convertAndSend("/topic/update", nResponse.toString()); //send notifications through websockets
	}
	
	@RequestMapping(value = "trains/{train}/composted/{nb}")
	@ResponseStatus(value = HttpStatus.ACCEPTED)
	private void updateTickets(@PathVariable int train, @PathVariable int nb) throws UnknownHostException, JSONException{
		Update nUpdate = new Update();
		nUpdate.updateTrainTickets(train, nb);
		JSONObject nResponse = 	new JSONObject();
		nResponse.put("content", "update");
		mMessageTemplate.convertAndSend("/topic/trains", nResponse.toString()); //send notifications through websockets
	}
}
