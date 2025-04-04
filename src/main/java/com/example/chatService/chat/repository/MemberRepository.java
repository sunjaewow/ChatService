package com.example.chatService.chat.repository;

import com.example.chatService.chat.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {

}
