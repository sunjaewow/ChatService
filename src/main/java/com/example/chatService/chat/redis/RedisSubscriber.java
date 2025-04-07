package com.example.chatService.chat.redis;

import com.example.chatService.chat.domain.ChatMessage3;
import com.example.chatService.chat.domain.ChatRoom;
import com.example.chatService.chat.domain.Member;
import com.example.chatService.chat.dto.ChatRoomUpdateDto;
import com.example.chatService.chat.repository.ChatRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@Service
public class RedisSubscriber {
    private final SimpMessagingTemplate messagingTemplate;//spring이 제공하는 stomp메시지 전송 도구.
    private final ObjectMapper objectMapper;
    private final StringRedisTemplate redisTemplate;
    private final ChatRepository chatRepository;

    @Transactional
    public void sendMessage(String message) {//메시지를 받고 역직렬화 하고 roomID로 메시지를 뿌림.
        try {
            ChatMessage3 chatMessage3 = objectMapper.readValue(message, ChatMessage3.class);//역직렬화.
            messagingTemplate.convertAndSend("/sub/chat/" + chatMessage3.getChatRoomId(), chatMessage3);
            redisTemplate.opsForValue().set("chat:lastMessage" + chatMessage3.getChatRoomId(), chatMessage3.getMessage());
            redisTemplate.opsForValue().set("chat:lastMessageTime"+chatMessage3.getChatRoomId(),chatMessage3.getLocalDateTime().toString());
            ChatRoom chatRoom = chatRepository.findById(chatMessage3.getChatRoomId()).orElseThrow(() -> new RuntimeException("채팅방 없음"));
            Map<Long, Long> unReadMap = new HashMap<>();
            for (Member member : chatRoom.getMembers()) {
                if (!(member.getMemberId().equals(chatMessage3.getSenderId()))) {
                    String key = "unRead:" + chatMessage3.getChatRoomId() + ":" + member.getMemberId();
                    Long count = redisTemplate.opsForValue().increment(key);
                    unReadMap.put(member.getMemberId(), count);
                }
            }

            ChatRoomUpdateDto chatRoomUpdateDto=ChatRoomUpdateDto.builder()
                    .chatRoomId(chatRoom.getChatRoomId())
                    .lastMessage(chatMessage3.getMessage())
                    .localDateTime(chatMessage3.getLocalDateTime())
                    .unReadCount(unReadMap)
                    .build();


            messagingTemplate.convertAndSend("/sub/chat/update"+chatMessage3.getChatRoomId(),chatRoomUpdateDto);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}



