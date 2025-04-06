package com.example.chatService.chat.controller;

import com.example.chatService.chat.domain.ChatMessage3;
import com.example.chatService.chat.service.ChatService1;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;

@RequiredArgsConstructor
@Controller
public class ChattingController {

    private final ChatService1 chatService1;

    @MessageMapping("/chat/message")
    public void message(ChatMessage3 message) {
        chatService1.sendMessage(message); //RedisPublisher 호출

    }
}

