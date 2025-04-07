package com.example.chatService.chat.dto;


import lombok.*;

import java.time.LocalDateTime;
import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ChatRoomUpdateDto {

    private Long chatRoomId;
    private String lastMessage;
    private LocalDateTime localDateTime;
    private Map<Long, Long> unReadCount;

}
