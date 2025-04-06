//package com.example.chatService.stomp.config;
//
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.messaging.simp.config.MessageBrokerRegistry;
//import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
//import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
//import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;
//
//@Slf4j
//@Configuration
//@EnableWebSocketMessageBroker
//@RequiredArgsConstructor
//public class WebSocketConfig1 implements WebSocketMessageBrokerConfigurer {//상속받아 STOMP로 메시지 처리 방법을 구성한다.
//
//    @Override
//    public void configureMessageBroker(MessageBrokerRegistry config) {//메시지를 중간에서 라우팅할 때 사용하는 메시지 브로커를 구성한다.
//        //메시지 구독 url(topic을 구독)(/sub/1)
//        config.enableSimpleBroker("/sub");
//        //메시지 발행 url
//        config.setApplicationDestinationPrefixes("/pub");
//    }
//
//    @Override//endpoint와 cors설정을 해준다.
//    public void registerStompEndpoints(StompEndpointRegistry registry) {
//
//        registry.addEndpoint("/ws")
//                .setAllowedOrigins("*")
//                .withSockJS();//클라이언트가 webSocket통신이 안될 때 대체 프로토콜
//    }
//}
