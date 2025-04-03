//package com.example.chatService.websocket.config;
//
//import lombok.RequiredArgsConstructor;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.socket.WebSocketHandler;
//import org.springframework.web.socket.config.annotation.EnableWebSocket;
//import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
//import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
//
//@RequiredArgsConstructor
//@Configuration
//@EnableWebSocket//spring WebSocket을 활성화 시킨다. 설정처리를 할 수 있다.
////소켓처리를 하는방법이기 때문에 핸들러를 등록해야한다.
////핸들러를 등록할때 접속하기 위한 경로ws를 함께 설정해주고, 다른곳에서 접속이 가능하도록 cors 설정을한다.
//public class WebSocketConfig implements WebSocketConfigurer {//핸들러를 구현
//    private final WebSocketHandler webSocketHandler;
//    // WebSocket연결을 처리하는 핵심 인터페이스.
//    // 웹소켓 핸들러는 클라이언트가 연결할 때, 메시지를 수신하고 응답을 보내는 등의 작업을 처리함.
//    @Override//핸들러 등록
//    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
//        registry.addHandler(webSocketHandler, "/ws").setAllowedOrigins("*");
//    }
//}
