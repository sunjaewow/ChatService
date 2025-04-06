package com.example.chatService.chat.redis;

import com.example.chatService.chat.domain.ChatMessage3;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class RedisSubscriber {
    private final SimpMessagingTemplate messagingTemplate;

    public void sendMessage(String message) throws JsonProcessingException {
        try {
            ChatMessage3 chatMessage3 = new ObjectMapper().readValue(message, ChatMessage3.class);
            messagingTemplate.convertAndSend("/topic/chat/" + chatMessage3.getChatRoomId(), chatMessage3);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


