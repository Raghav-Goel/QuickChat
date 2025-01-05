package com.chat.quickChat.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        //On this endpoint the client will be able to connect with server.
        registry.addEndpoint("/chat").setAllowedOrigins("*");
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        /*
        Anyone subscribed to a WebSocket channel with the prefix /topic
        will receive messages sent to endpoints with the prefix /app.
        */
        registry.enableSimpleBroker("/topic");//on this we will receive messages
        registry.setApplicationDestinationPrefixes("/app");//on this we have to send the messages
    }
}
