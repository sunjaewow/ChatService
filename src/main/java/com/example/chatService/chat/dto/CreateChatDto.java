package com.example.chatService.chat.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CreateChatDto {

    private String name;

    private List<Long> members;
}
