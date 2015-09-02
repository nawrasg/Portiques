package fr.capgemini.sncf.rest.controllers;

import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mongodb.DBCursor;

import fr.capgemini.sncf.db.Request;
import fr.capgemini.sncf.types.Alarm;

@Controller
@RequestMapping("/sncf")
public class AlarmController {
	private static Map<String, Alarm> mAlarmList; //cache
	
	@Autowired
	private SimpMessagingTemplate mMessageTemplate; //Object to send messages through socket
	
	public AlarmController() throws UnknownHostException{
		loadAlarms();
	}

	public static void loadAlarms() throws UnknownHostException {
		mAlarmList = new HashMap<String, Alarm>();
		Request nQuery = new Request("localhost", "sncf");
//		DBCursor nCursor = nQuery.get("alarms", "dismiss", 0);
		DBCursor nCursor = nQuery.get("alarms");
		while(nCursor.hasNext()){
			Alarm nAlarm = new Alarm(nCursor.next());
			mAlarmList.put(nAlarm.getId(), nAlarm);
		}
	}
	
	@RequestMapping(value="/gares/{cp}/alarms")
	public @ResponseBody Object getGateAlarms(@PathVariable int cp){
		Map<String, Alarm> nMap = new HashMap<String, Alarm>();
		for (Map.Entry<String, Alarm> nEntry : mAlarmList.entrySet()){
		    Alarm nAlarm = nEntry.getValue();
		    if(nAlarm.getCity() == cp){
		    	nMap.put(nEntry.getKey(), nAlarm);
		    }
		}
		return nMap.values();
	}
}
