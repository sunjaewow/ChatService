package com.example.chatService.chat.redis;

import com.example.chatService.chat.domain.ChatMessage3;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RedisPublisher {

    private final ChannelTopic channelTopic;

    private final RedisTemplate<String, Object> redisTemplate;

    public void publish(ChatMessage3 chatMessage3) {
        redisTemplate.convertAndSend(channelTopic.getTopic(), chatMessage3);

    }
}
