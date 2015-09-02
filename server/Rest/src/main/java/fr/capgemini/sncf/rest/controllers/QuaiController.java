package fr.capgemini.sncf.rest.controllers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import fr.capgemini.sncf.types.Train;

@Controller
@RequestMapping("/sncf")
public class QuaiController {
	
	@RequestMapping(value="/gares/{gare}/quais/{quai}/trains", method = RequestMethod.GET)
	public @ResponseBody Object getGateTrains(@PathVariable int gare, @PathVariable int quai){
		Map<Integer, Train> nMap = new HashMap<Integer, Train>();
		for (Map.Entry<Integer, Train> nEntry : SncfController.mTrainList.entrySet()){
		    Train nTrain = nEntry.getValue();
		    if(nTrain.getQuaiNumber() == quai){
		    	nMap.put(nEntry.getKey(), nTrain);
		    }
		}
		return nMap.values();
	}

}
