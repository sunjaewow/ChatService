package com.example.chatService.chat.dto;

import com.example.chatService.chat.domain.ChatRoom;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GetChatListResponseDto {

    private ChatRoom chatRoom;

    private String lastMessage;

    private Long unReadCount;

    private LocalDateTime lastMessageLocalDateTime;
}
