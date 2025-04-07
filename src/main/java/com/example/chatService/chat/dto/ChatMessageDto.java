package com.example.chatService.chat.dto;

import com.example.chatService.chat.domain.ChatMessage3;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class ChatMessageDto {

    private Long chatRoomId;

    private List<ChatMessage3> message3List;
}
