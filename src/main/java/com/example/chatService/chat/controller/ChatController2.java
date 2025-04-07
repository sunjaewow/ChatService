package com.example.chatService.chat.controller;

import com.example.chatService.chat.domain.ChatMessage3;
import com.example.chatService.chat.domain.ChatRoom;
import com.example.chatService.chat.dto.ChatMessageDto;
import com.example.chatService.chat.dto.CreateChatDto;
import com.example.chatService.chat.dto.GetChatListResponseDto;
import com.example.chatService.chat.redis.RedisPublisher;
import com.example.chatService.chat.service.ChatService1;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
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
    private List<GetChatListResponseDto> getChatRoom(@RequestParam Long memberId) {
        return chatService1.getChat(memberId);
    }

    @PostMapping
    private ChatRoom createChatRoom(@RequestBody CreateChatDto createChatDto) {
        return chatService1.chatRoom(createChatDto);
    }

    @GetMapping("/{chatRoomId}")
    private ChatMessageDto getChatRoomMessages(@PathVariable Long chatRoomId,
                                               @RequestParam(defaultValue = "0") int page,
                                               @RequestParam Long memberId) {
        return chatService1.getChatMessages(chatRoomId, memberId, page);
    }

    @DeleteMapping("/{chatRoomId}")
    private ResponseEntity<?> deleteChatRoom(@PathVariable Long chatRoomId) {
        return chatService1.deleteChatRoom(chatRoomId);
    }
}
