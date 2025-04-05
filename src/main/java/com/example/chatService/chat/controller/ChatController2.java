package com.example.chatService.chat.controller;

import com.example.chatService.chat.domain.ChatMessage3;
import com.example.chatService.chat.domain.ChatRoom;
import com.example.chatService.chat.dto.CreateChatDto;
import com.example.chatService.chat.service.ChatService1;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/chat")
public class ChatController2 {

    private final ChatService1 chatService1;


    @GetMapping
    private List<ChatRoom> getChatRoom(@RequestParam Long memberId) {
        return chatService1.getChat(memberId);
    }

    @PostMapping
    private ChatRoom createChatRoom(@RequestBody CreateChatDto createChatDto) {
        return chatService1.chatRoom(createChatDto);
    }

    @MessageMapping("/chat.send")
    @SendTo("/topic/public")
    public ChatMessage3 sendMessage(@Payload ChatMessage3 chatMessage3) {
        chatService1.sendMessage(chatMessage3);
        return chatMessage3;
    }
}
