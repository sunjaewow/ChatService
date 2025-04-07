package com.example.chatService.chat.service;

import com.example.chatService.chat.domain.ChatMessage3;
import com.example.chatService.chat.domain.ChatRoom;
import com.example.chatService.chat.domain.Member;
import com.example.chatService.chat.dto.ChatMessageDto;
import com.example.chatService.chat.dto.CreateChatDto;
import com.example.chatService.chat.dto.GetChatListResponseDto;
import com.example.chatService.chat.repository.ChatMessage3Repository;
import com.example.chatService.chat.repository.ChatRepository;
import com.example.chatService.chat.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor

public class ChatService1 {
    private final ChatRepository chatRepository;
    private final MemberRepository memberRepository;
    private final ChatMessage3Repository chatMessage3Repository;
    private final StringRedisTemplate redisTemplate;

    @Transactional
    public ChatRoom chatRoom(CreateChatDto createChatDto) {
        List<Member> members = memberRepository.findAllById(createChatDto.getMembers());
        ChatRoom chatRoom = new ChatRoom();
        chatRoom.setName(createChatDto.getName());
        chatRoom.setMembers(members);
        return chatRepository.save(chatRoom);
    }

    @Transactional()//redis에서 꺼내와야할듯.
    public List<GetChatListResponseDto> getChat(Long memberId) {
        List<ChatRoom> chatRoomList = chatRepository.findAllByMemberId(memberId);
        List<GetChatListResponseDto> listResponseDtos = new ArrayList<>();
        for (ChatRoom chatRoom : chatRoomList) {
            Long chatRoomId = chatRoom.getChatRoomId();
            String lastMessage =  redisTemplate.opsForValue().get("chat:lastMessage" + chatRoomId);
            String lastMessageTime = redisTemplate.opsForValue().get("chat:lastMessageTime" + chatRoomId);
            String unReadCount =  redisTemplate.opsForValue().get("unRead:" + chatRoomId + ":" + memberId);
            Long count = unReadCount != null ? Long.parseLong(unReadCount) : 0L;
            GetChatListResponseDto dto = GetChatListResponseDto.builder()
                    .chatRoom(chatRoom)
                    .lastMessage(lastMessage)
                    .lastMessageLocalDateTime(lastMessageTime)
                    .unReadCount(count)
                    .build();
            listResponseDtos.add(dto);
        }
        return listResponseDtos;
    }



    @Transactional
    public ChatMessageDto getChatMessages(Long chatRoomId,Long memberId, int page) {
        chatRepository.findById(chatRoomId).orElseThrow(() -> new RuntimeException("채팅방 없음."));
        Pageable pageable = PageRequest.of(page, 20, Sort.by(Sort.Direction.DESC,"localDateTime"));//페이징 처리.
        Page<ChatMessage3> pageResult = chatMessage3Repository.findByChatRoomId(chatRoomId, pageable);
        String key = "unRead:" + chatRoomId + ":" + memberId;
        redisTemplate.delete(key);
        return new ChatMessageDto(chatRoomId, pageResult.getContent());
    }

    @Transactional
    public ResponseEntity<?> deleteChatRoom(Long chatRoomId) {
        Optional<ChatRoom> chatRoom = chatRepository.findById(chatRoomId);
        if (chatRoom.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("채탕빙 없음");
        }

        chatRepository.deleteById(chatRoomId);
        chatMessage3Repository.deleteByChatRoomId(chatRoomId);
        return ResponseEntity.ok().build();
    }
}
