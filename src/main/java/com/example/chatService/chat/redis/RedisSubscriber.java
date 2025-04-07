package com.example.chatService.chat.redis;

import com.example.chatService.chat.domain.ChatMessage3;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class RedisSubscriber {
    private final SimpMessagingTemplate messagingTemplate;//spring이 제공하는 stomp메시지 전송 도구.
    private final ObjectMapper objectMapper;

    public void sendMessage(String message) {//메시지를 받고 역직렬화 하고 roomID로 메시지를 뿌림.
        try {
            ChatMessage3 chatMessage3 = objectMapper.readValue(message, ChatMessage3.class);//역직렬화.
            messagingTemplate.convertAndSend("/sub/chat/" + chatMessage3.getChatRoomId(), chatMessage3);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}



