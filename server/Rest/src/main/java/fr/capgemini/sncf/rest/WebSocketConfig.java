package fr.capgemini.sncf.rest;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.AbstractWebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig extends AbstractWebSocketMessageBrokerConfigurer{

	/**
	 * Add an end point for websockets.
	 * 
	 */
	public void registerStompEndpoints(StompEndpointRegistry arg0) {
		arg0.addEndpoint("/sncf").withSockJS();
		
	}
	
	/**
	 * Add a subscription topic for websockets.
	 * 
	 */
	@Override
	public void configureMessageBroker(MessageBrokerRegistry config) {
		config.enableSimpleBroker("/topic");
		config.setApplicationDestinationPrefixes("/app");
	}

}
