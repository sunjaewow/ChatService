package com.example.chatService.chat.service;

import com.example.chatService.chat.domain.ChatMessage3;
import com.example.chatService.chat.domain.ChatRoom;
import com.example.chatService.chat.domain.Member;
import com.example.chatService.chat.dto.CreateChatDto;
import com.example.chatService.chat.repository.ChatMessage3Repository;
import com.example.chatService.chat.repository.ChatRepository;
import com.example.chatService.chat.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ChatService1 {
    private final ChatRepository chatRepository;
    private final MemberRepository memberRepository;
    private final ChatMessage3Repository chatMessage3Repository;
    private final RedisTemplate<String, Object> redisTemplate;
    private final ChannelTopic channelTopic;

    public void sendMessage(ChatMessage3 chatMessage3) {
        if (chatMessage3.getType() == ChatMessage3.MessageType.ENTER) {
            chatMessage3.setMessage(chatMessage3.getSenderName() + "님이 입장하셨습니다.");
        } else if (chatMessage3.getType() == ChatMessage3.MessageType.LEAVE) {
            chatMessage3.setMessage(chatMessage3.getMessage() + "님이 나가셨습니다.");
        }
        chatMessage3Repository.save(chatMessage3);
        redisTemplate.convertAndSend(channelTopic.getTopic(), chatMessage3);
    }

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
