package fr.capgemini.sncf.rest.controllers.put;

import java.net.UnknownHostException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import fr.capgemini.sncf.db.Update;
import fr.capgemini.sncf.rest.controllers.GatesController;

@RestController
@RequestMapping("/sncf")
public class GatePutController {

	@RequestMapping(value = "/gares/{cp}/gates/{gate}/trains/{train}", method = RequestMethod.PUT)
	@ResponseStatus(value = HttpStatus.ACCEPTED)
	public void setGateTrain(@PathVariable String gate, @PathVariable int train) throws UnknownHostException{
		Update nUpdate = new Update();
		nUpdate.updateGateTrain(gate, train);
		GatesController.loadGates();
	}
}
