package com.example.chatService.chat.repository;

import com.example.chatService.chat.domain.ChatMessage3;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChatMessage3Repository extends MongoRepository<ChatMessage3, String> {
    List<ChatMessage3> findByChatRoomId(Long chatRoomId);
}
