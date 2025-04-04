package com.example.chatService.chat.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.bind.annotation.GetMapping;

@Getter
@Setter
public class CreateMemberDto {
    private String name;
}
