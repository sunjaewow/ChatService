package com.example.chatService.stomp.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "chat")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ChatContent {
    @Id
    private String id;
    private String name;
    private Long age;

    public ChatContent(String name, Long age) {
        this.name = name;
        this.age = age;
    }
}
