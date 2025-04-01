package com.example.chatService.stomp.controller;

import com.example.chatService.stomp.dto.ChatRoom1;
import com.example.chatService.stomp.repository.ChatRepository1;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/chatroom")
public class ChatRoomController {
    private final ChatRepository1 chatRepository1;

    //채팅 리스트 반환
    @GetMapping
    public ResponseEntity<Object> goChatRoom() {
        List<ChatRoom1> chatRoom1s = chatRepository1.findAllRoom();
        return ResponseEntity.ok(chatRoom1s);
    }

    //채팅방 생성
    @PostMapping
    public ResponseEntity<Object> createRoom(@RequestParam String name) {
        ChatRoom1 chatRoom = chatRepository1.createChatRoom(name);
        return ResponseEntity.ok(chatRoom);
    }

    @GetMapping("/userlist")
    public ArrayList<String> userList(String roomId) {
        return chatRepository1.getUserList(roomId);
    }
}
