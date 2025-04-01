package com.example.chatService.stomp.controller;

import com.example.chatService.stomp.dto.ChatDTO1;
import com.example.chatService.stomp.repository.ChatRepository1;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
public class ChatController1 {

    private final SimpMessageSendingOperations template;
    private final ChatRepository1 repository1;

    @MessageMapping("/enterUser")
    public void enterUser(@Payload ChatDTO1 chatDTO1, SimpMessageHeaderAccessor headerAccessor) {
        repository1.plusUserCnt(chatDTO1.getRoomId());
        String userUUID = repository1.addUser(chatDTO1.getRoomId(), chatDTO1.getSender());

        headerAccessor.getSessionAttributes().put("userUUID", userUUID);
        headerAccessor.getSessionAttributes().put("roomId", chatDTO1.getRoomId());

        chatDTO1.setMessage(chatDTO1.getSender() + "님 입장!!");
        template.convertAndSend("/sub/chat/room/" + chatDTO1.getRoomId(), chatDTO1);
    }

    @MessageMapping("/sendMessage")
    public void sedMessage(@Payload ChatDTO1 chatDTO1) {
        log.info("CHAT {}", chatDTO1);
        chatDTO1.setMessage(chatDTO1.getMessage());
        template.convertAndSend("/sub/chat/room/" + chatDTO1.getRoomId(), chatDTO1);
    }
}
