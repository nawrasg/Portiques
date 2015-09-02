package fr.capgemini.sncf.rest.controllers.put;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;

import fr.capgemini.sncf.rest.controllers.DashboardController;

@Controller
@RequestMapping("/sncf/")
public class DashboardPutController {
	@Autowired
	private SimpMessagingTemplate mMessageTemplate;
	
	@ResponseStatus(HttpStatus.ACCEPTED)
	@RequestMapping(value = "/app/dashboard", method = RequestMethod.PUT)
	public void setDashboard(@RequestBody String body) throws JSONException{
		DashboardController.mDashboard = body;

		JSONObject nResponse = 	new JSONObject();
		nResponse.put("content", "update");
		mMessageTemplate.convertAndSend("/topic/dashboard", nResponse.toString());
	}

}
