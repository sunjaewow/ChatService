package com.example.chatService.stomp.controller;

import com.example.chatService.stomp.dto.ChatContent;
import com.example.chatService.stomp.repository.TestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class TestController {

    private final TestRepository testRepository;

    @GetMapping("/save")
    public ChatContent test(@RequestParam("name") String name, @RequestParam("age") Long age) {
        ChatContent content = new ChatContent(name, age);

        return testRepository.save(content);
    }
    @GetMapping("/find")
    public ChatContent test(@RequestParam("name") String name) {

        return testRepository.findChatContentByName(name);
    }
}
