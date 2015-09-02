package fr.capgemini.sncf.rest.controllers;


import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class NotificationController {

	@MessageMapping("/sncf")
    @SendTo("/topic/update")
    public int greeting() throws Exception {
        return 200;
    }
}
