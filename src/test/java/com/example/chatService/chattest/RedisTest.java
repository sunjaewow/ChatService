package com.example.chatService.chattest;

import com.example.chatService.chat.redis.RedisProperties;
import org.junit.jupiter.api.Test;

public class RedisTest {

    @Test
    private void test() {
        RedisProperties redisProperties = new RedisProperties();
        System.out.println(redisProperties.getHost());
    }
}
