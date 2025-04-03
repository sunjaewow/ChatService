package com.example.chatService.stomp.repository;

import com.example.chatService.stomp.dto.ChatRoom1;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
@Slf4j
public class ChatRepository1 {
    private Map<String, ChatRoom1> chatRoomMap = new LinkedHashMap<>();

    //전체 채팅방 조회
    public List<ChatRoom1> findAllRoom() {
        List chatRooms = new ArrayList<>(chatRoomMap.values());
        Collections.reverse(chatRooms);//최신순
        return chatRooms;
    }

    //roomId 기준으로 채팅방 찾기
    public ChatRoom1 findRoomById(String roomId) {
        return chatRoomMap.get(roomId);
    }

    //roomName으로 채팅방 만들기
    public ChatRoom1 createChatRoom(String roomName) {
        ChatRoom1 chatRoom1 = new ChatRoom1().create(roomName);
        chatRoomMap.put(chatRoom1.getRoomId(), chatRoom1);
        return chatRoom1;
    }

    //채팅방 인원수 +1
    public void plusUserCnt(String roomId) {
        ChatRoom1 chatRoom1 = chatRoomMap.get(roomId);
        chatRoom1.setUserCount(chatRoom1.getUserCount()+1);
    }

    //채팅방 인원수 -1
    public void minusUserCnt(String roomId) {
        ChatRoom1 chatRoom1 = chatRoomMap.get(roomId);
        chatRoom1.setUserCount(chatRoom1.getUserCount()-1);
    }

    //채팅방 유저 리스트에 유저 추가
    public String addUser(String roomId, String userName) {
        ChatRoom1 chatRoom1 = chatRoomMap.get(roomId);
        String userUUID = UUID.randomUUID().toString();

        chatRoom1.getUserList().put(userUUID, userName);

        return userUUID;
    }

    //채팅방 유저 리스트 삭제
    public void delUser(String roomId, String userUUID) {
        ChatRoom1 chatRoom1 = chatRoomMap.get(roomId);
        chatRoom1.getUserList().remove(userUUID);
    }

    //채팅방 userName 조회
    public String getUserName(String roomId, String userUUID) {
        ChatRoom1 chatRoom1 = chatRoomMap.get(roomId);
        return chatRoom1.getUserList().get(userUUID);
    }

    //채팅방 전체 userlist 조회
    public ArrayList<String> getUserList(String roomId) {
        ArrayList<String> list = new ArrayList<>();
        ChatRoom1 chatRoom1 = chatRoomMap.get(roomId);

        chatRoom1.getUserList().forEach((key, value) -> list.add(value));
        return list;
    }

}
