package com.example.chatService.chat.service;

import com.example.chatService.chat.domain.ChatMessage3;
import com.example.chatService.chat.domain.ChatRoom;
import com.example.chatService.chat.domain.Member;
import com.example.chatService.chat.dto.ChatMessageDto;
import com.example.chatService.chat.dto.CreateChatDto;
import com.example.chatService.chat.repository.ChatMessage3Repository;
import com.example.chatService.chat.repository.ChatRepository;
import com.example.chatService.chat.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
@RequiredArgsConstructor

public class ChatService1 {
    private final ChatRepository chatRepository;
    private final MemberRepository memberRepository;
    private final ChatMessage3Repository chatMessage3Repository;

    public ChatRoom chatRoom(CreateChatDto createChatDto) {
        List<Member> members = memberRepository.findAllById(createChatDto.getMembers());
        ChatRoom chatRoom = new ChatRoom();
        chatRoom.setName(createChatDto.getName());
        chatRoom.setMembers(members);
        return chatRepository.save(chatRoom);
    }

    public List<ChatRoom> getChat(Long memberId) {
        return chatRepository.findAllByMemberId(memberId);
    }

    public ChatMessageDto getChatMessages(Long chatRoomId,int page) {
        chatRepository.findById(chatRoomId).orElseThrow(() -> new RuntimeException("채팅방 없음."));
        Pageable pageable = PageRequest.of(page, 20, Sort.by(Sort.Direction.DESC,"localDateTime"));//페이징 처리.
        Page<ChatMessage3> pageResult = chatMessage3Repository.findByChatRoomId(chatRoomId, pageable);
        return new ChatMessageDto(chatRoomId, pageResult.getContent());
    }
}
