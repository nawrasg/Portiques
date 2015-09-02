package fr.capgemini.sncf.rest.controllers.delete;

import java.net.UnknownHostException;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

import fr.capgemini.sncf.db.Update;
import fr.capgemini.sncf.rest.controllers.GatesController;

@Controller
@RequestMapping("/sncf")
public class GateDeleteController {
	
	@ResponseStatus(value = HttpStatus.ACCEPTED)
	@RequestMapping(value = "/gares/{cp}/gates/{gate}/trains")
	public void removeGateTrain(@PathVariable String gate) throws UnknownHostException{
		Update nUpdate = new Update();
		nUpdate.updateGateTrain(gate, -1);
		GatesController.loadGates();
	}
}
