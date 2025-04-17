package com.example.chatService.chat.repository;

import org.springframework.stereotype.Repository;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class SseEmitterRepository {

    private final Map<String, SseEmitter> emitterMap = new ConcurrentHashMap<>();

    public SseEmitter save(String eventId, SseEmitter sseEmitter) {
        emitterMap.put(eventId, sseEmitter);
        return sseEmitter;
    }

    public Optional<SseEmitter> findById(String memberId) {
        return Optional.ofNullable(emitterMap.get(memberId));
    }

    public void deleteById(String eventId) {
        emitterMap.remove(eventId);
    }
}
