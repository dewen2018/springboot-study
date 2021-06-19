//package com.dewen;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.messaging.simp.config.MessageBrokerRegistry;
//import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
//import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
//import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurationSupport;
//import org.springframework.web.socket.server.standard.ServerEndpointExporter;
//
//@Configuration
////@EnableWebSocketMessageBroker
//public class WebSocketConfig{ //extends WebSocketMessageBrokerConfigurationSupport {
//
//	@Bean
//	public ServerEndpointExporter serverEndpointExporter() {
//		System.out.println("我被注入了");
//		return new ServerEndpointExporter();
//	}
//
////	@Override
////	public void registerStompEndpoints(StompEndpointRegistry registry) {
////		registry.addEndpoint("/websocket/queue").setAllowedOrigins("*").withSockJS();
////
////	}
////
////	@Override
////	public void configureMessageBroker(MessageBrokerRegistry config) {
////		config.enableSimpleBroker("/topic", "/user");// topic用来广播，user用来实现p2p
////	}
//}
