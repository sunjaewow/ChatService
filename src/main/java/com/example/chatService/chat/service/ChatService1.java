package com.example.chatService.chat.service;

import com.example.chatService.chat.domain.ChatRoom;
import com.example.chatService.chat.domain.Member;
import com.example.chatService.chat.dto.CreateChatDto;
import com.example.chatService.chat.repository.ChatRepository;
import com.example.chatService.chat.repository.MemberRepository;
import com.example.chatService.stomp.dto.ChatRoom1;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ChatService1 {
    private final ChatRepository chatRepository;
    private final MemberRepository memberRepository;

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
}
