package com.example.chatService.chat.redis;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class NotificationPublisher {

    private final RedisTemplate<String, Object> notificationRedisTemplate;

    public void publish(String topic, Object message) {
        log.info("Publishing notification: topic={}, message={}", topic, message);
        notificationRedisTemplate.convertAndSend(topic, message);
    }
}
