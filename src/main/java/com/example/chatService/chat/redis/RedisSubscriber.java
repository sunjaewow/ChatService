package com.example.chatService.chat.redis;

import com.example.chatService.chat.domain.ChatMessage3;
import com.example.chatService.chat.repository.ChatMessage3Repository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class RedisSubscriber {
    private final SimpMessagingTemplate messagingTemplate;
    private final ChatMessage3Repository chatMessage3Repository;

    public void sendMessage(String message) throws JsonProcessingException {
        try {
            ChatMessage3 chatMessage3 = new ObjectMapper().readValue(message, ChatMessage3.class);
            chatMessage3Repository.save(chatMessage3);
            messagingTemplate.convertAndSend("/topic/chat/" + chatMessage3.getChatRoomId(), chatMessage3);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


