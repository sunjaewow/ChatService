package com.example.chatService.chat.service;

import com.example.chatService.chat.domain.ChatMessage3;
import com.example.chatService.chat.redis.RedisPublisher;
import com.example.chatService.chat.repository.ChatMessage3Repository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ChattingService {
    private final ChatMessage3Repository chatMessage3Repository;
    private final RedisPublisher redisPublisher;

    public void sendMessage(ChatMessage3 chatMessage3) {
        if (chatMessage3.getType() == ChatMessage3.MessageType.ENTER) {
            chatMessage3.setMessage(chatMessage3.getSenderName() + "님이 입장하셨습니다.");
        } else if (chatMessage3.getType() == ChatMessage3.MessageType.LEAVE) {
            chatMessage3.setMessage(chatMessage3.getMessage() + "님이 나가셨습니다.");
        }
        chatMessage3Repository.save(chatMessage3);
        redisPublisher.publish(chatMessage3);
    }
}
