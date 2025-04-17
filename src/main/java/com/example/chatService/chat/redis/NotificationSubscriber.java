package com.example.chatService.chat.redis;

import com.example.chatService.chat.service.SseEmitterManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class NotificationSubscriber {

    private final SseEmitterManager sseEmitterManager;

    public void onMessage(String channel, String message) {
        log.info("Received notification - channel: {}, message: {}", channel, message);

        // channel = user:{userId}
        String userId = channel.split(":")[1]; // user:123 → 123
        sseEmitterManager.sendNotification(userId, message);
    }
}
