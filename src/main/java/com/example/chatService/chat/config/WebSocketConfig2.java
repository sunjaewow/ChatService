package com.example.chatService.chat.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.ChannelRegistration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker//stomp기반 websocket 메시지 브로커를 활성화
@RequiredArgsConstructor
public class WebSocketConfig2 implements WebSocketMessageBrokerConfigurer {

    private final StompHandler stompHandler;//jwt토큰인증 방식이면 이걸로.
    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        config.enableSimpleBroker("/sub");//구독
        config.setApplicationDestinationPrefixes("/pub");//발행 prefix
    }

    @Override//cors
    public void registerStompEndpoints(StompEndpointRegistry registry) {

        registry.addEndpoint("/ws-stomp")
                .setAllowedOriginPatterns("*")
                .withSockJS();
    }

    @Override//stomphandler가 websocket 앞단에서 token을 체크할 수 있도록 인터셉터 설정
    public void configureClientInboundChannel(ChannelRegistration registration) {
        registration.interceptors(stompHandler);

    }
}
