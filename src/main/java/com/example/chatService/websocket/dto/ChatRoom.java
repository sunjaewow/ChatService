package com.example.chatService.websocket.dto;

import com.example.chatService.websocket.service.ChatService;
import lombok.Builder;
import lombok.Getter;
import org.springframework.web.socket.WebSocketSession;

import java.util.HashSet;
import java.util.Set;

@Getter
public class ChatRoom {

    private String roomId;

    private String name;

    private Set<WebSocketSession> sessions = new HashSet<>();

    @Builder
    public ChatRoom(String roomId) {
        this.roomId = roomId;
        this.name = name;
    }
    //만약 타입이 ENTER이면 세션에 추가후 메시지를 브로드캐스트
    public void handlerActions(WebSocketSession session, ChatMessage chatMessage, ChatService chatService) {
        if (chatMessage.getType().equals(ChatMessage.MessageType.ENTER)) {
            sessions.add(session);
            chatMessage.setMessage(chatMessage.getSender()+"님이 입장했습니다.");
        }
        sendMassage(chatMessage, chatService);
    }

    private <T> void sendMassage(T message, ChatService chatService) {
        sessions.parallelStream()// parallelStream을 사용하여 병렬 스트림으로 모든 세션에 대해 메시지를 전송합니다.
                .forEach(session -> chatService.sendMessage(session, message));//세션별 메시지를 전송

    }
}
