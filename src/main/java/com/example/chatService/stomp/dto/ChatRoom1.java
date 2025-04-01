package com.example.chatService.stomp.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.UUID;

//세션을 가질 필요 없음.
@Getter
@Setter
public class ChatRoom1 {
    private String roomId;//채팅방 아이디
    private String roomName;//채팅방 이름
    private long userCount;//채팅방 인원수
    private HashMap<String, String> userList = new HashMap<String, String>();

    public ChatRoom1 create(String roomName) {
        ChatRoom1 chatRoom = new ChatRoom1();
        chatRoom.roomId = UUID.randomUUID().toString();
        chatRoom.roomName = roomName;
        return chatRoom;
    }
}
