package com.example.chatService.chat.service;

import com.example.chatService.chat.domain.ChatMessage3;
import com.example.chatService.chat.redis.NotificationPublisher;
import com.example.chatService.chat.redis.RedisPublisher;
import com.example.chatService.chat.repository.ChatMessage3Repository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ChattingService {

    private final ChatMessage3Repository chatMessage3Repository;
    private final RedisPublisher redisPublisher;
    private final NotificationPublisher notificationPublisher;
    private final ChatService1 chatService1; // 참여자 조회용

    public void sendMessage(ChatMessage3 chatMessage3) {
        if (chatMessage3.getType() == ChatMessage3.MessageType.ENTER) {
            chatMessage3.setMessage(chatMessage3.getSenderName() + "님이 입장하셨습니다.");
        } else if (chatMessage3.getType() == ChatMessage3.MessageType.LEAVE) {
            chatMessage3.setMessage(chatMessage3.getMessage() + "님이 나가셨습니다.");
        }

        // 1. 채팅 메시지 저장
        chatMessage3Repository.save(chatMessage3);

        // 2. 채팅 메시지 실시간 전송 (WebSocket or Redis Pub/Sub)
        redisPublisher.publish(chatMessage3);

        // 3. 🔔 알림 전송 (보낸 사람 제외한 참여자에게)
        sendChatNotification(chatMessage3);
    }

    private void sendChatNotification(ChatMessage3 chatMessage3) {
        Long senderId = chatMessage3.getSenderId();
        Long chatRoomId = chatMessage3.getChatRoomId();

        // 참여자 목록 가져오기 (너가 어떻게 저장했느냐에 따라 구현 다름)
        List<Long> participantIds = chatService1.getParticipantIds(chatRoomId);

        for (Long userId : participantIds) {
            if (!userId.equals(senderId)) {
                String message = "💬 " + chatMessage3.getSenderName() + "님이 메시지를 보냈습니다.";
                notificationPublisher.publish("user:" + userId, message);
            }
        }
    }
}

