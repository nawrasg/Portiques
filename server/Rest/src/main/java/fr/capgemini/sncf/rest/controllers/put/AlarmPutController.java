package fr.capgemini.sncf.rest.controllers.put;

import java.net.UnknownHostException;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;

import fr.capgemini.sncf.db.Update;
import fr.capgemini.sncf.rest.controllers.AlarmController;

@Controller
@RequestMapping("/sncf")
public class AlarmPutController {

	@RequestMapping(value="/gares/{cp}/alarms/{alarm}", method = RequestMethod.PUT)
	@ResponseStatus(value = HttpStatus.ACCEPTED)
	public void dismissAlarm(@PathVariable int cp, @PathVariable String alarm) throws UnknownHostException{
		Update nUpdate = new Update();
		nUpdate.removeAlarm(alarm);
		AlarmController.loadAlarms();
	}
}
