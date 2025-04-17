package com.example.chatService.chat.service;

import com.example.chatService.chat.repository.SseEmitterRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@Service
@RequiredArgsConstructor
@Slf4j
public class NotificationService {

    private final SseEmitterRepository sseEmitterRepository;

    public SseEmitter subscribe(String userName) {
        SseEmitter sseEmitter = new SseEmitter(60 * 60 * 1000L);
        sseEmitterRepository.save(userName, sseEmitter);

        sseEmitter.onTimeout(sseEmitter::complete);
        sseEmitter.onError((e) -> sseEmitter.complete());
        sseEmitter.onCompletion(() -> sseEmitterRepository.deleteById(userName));

        return sseEmitter;
    }



}
