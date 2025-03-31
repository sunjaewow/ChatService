package com.example.chatService.handler;

import com.example.chatService.dto.ChatMessage;
import com.example.chatService.dto.ChatRoom;
import com.example.chatService.service.ChatService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

@Slf4j
@Component
@RequiredArgsConstructor
//스프링에서 지원하는 소켓 핸들링 방식은 텍스트와 바이너리 두가지로 각각 Text, Binary로 지원한다.
//채팅을 위해서는 텍스트가 적합하므로 Text를 상속받는 핸들러를 작성해준다.
public class WebSockChatHandler extends TextWebSocketHandler {
    private final ObjectMapper objectMapper;
    private final ChatService chatService;

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        String payload = message.getPayload();
        log.info("payload {}", payload);

        ChatMessage chatMessage = objectMapper.readValue(payload, ChatMessage.class);
        ChatRoom room = chatService.findRoomById(chatMessage.getRoomId());
        room.handlerActions(session, chatMessage, chatService);

    }
}
