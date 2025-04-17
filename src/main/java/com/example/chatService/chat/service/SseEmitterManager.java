package com.example.chatService.chat.service;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@Component
public class SseEmitterManager {

    private static final Long DEFAULT_TIMEOUT = 60L * 60 * 1000L; // 1시간
    private final Map<String, SseEmitter> emitters = new ConcurrentHashMap<>();

    public SseEmitter subscribe(String userId) {
        SseEmitter emitter = new SseEmitter(DEFAULT_TIMEOUT);

        emitters.put(userId, emitter);

        emitter.onCompletion(() -> emitters.remove(userId));
        emitter.onTimeout(() -> emitters.remove(userId));

        log.info("User subscribed with id: {}", userId);

        try {
            emitter.send(SseEmitter.event().name("connect").data("connected"));
        } catch (IOException e) {
            log.error("Error sending initial connect event", e);
        }

        return emitter;
    }

    public void sendNotification(String userId, String message) {
        SseEmitter emitter = emitters.get(userId);
        if (emitter != null) {
            try {
                emitter.send(SseEmitter.event().name("notification").data(message));
                log.info("Notification sent to user: {}", userId);
            } catch (IOException e) {
                emitters.remove(userId);
                log.error("Error sending notification to user: {}, emitter removed", userId, e);
            }
        } else {
            log.warn("No active SSE emitter for user: {}", userId);
        }
    }
}
