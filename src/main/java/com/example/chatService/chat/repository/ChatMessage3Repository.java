package com.example.chatService.chat.repository;

import com.example.chatService.chat.domain.ChatMessage3;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ChatMessage3Repository extends MongoRepository<ChatMessage3, String> {
    Page<ChatMessage3> findByChatRoomId(Long chatRoomId, Pageable pageable);
}
