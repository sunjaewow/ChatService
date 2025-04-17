package com.example.chatService.chat.repository;

import com.example.chatService.chat.domain.ChatRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ChatRoomRepository extends JpaRepository<ChatRoom, Long> {

    @Query("select c from ChatRoom c join c.members m where m.memberId=:memberId")
    List<ChatRoom> findAllByMemberId(@Param("memberId") Long memberId);
}
