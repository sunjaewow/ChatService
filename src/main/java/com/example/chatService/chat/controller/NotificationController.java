package com.example.chatService.chat.controller;

import com.example.chatService.chat.service.SseEmitterManager;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/notifications")
public class NotificationController {

    private final SseEmitterManager sseEmitterManager;
    @GetMapping(value = "/subscribe/{userId}", produces = "text/event-stream")
    public SseEmitter subscribe(@PathVariable String userId) {
        return sseEmitterManager.subscribe(userId);
    }
}
