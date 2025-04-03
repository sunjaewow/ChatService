package com.example.chatService.stomp.repository;


import com.example.chatService.stomp.dto.ChatContent;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TestRepository extends MongoRepository<ChatContent, String> {
    ChatContent findChatContentByName(String name);
}
