package com.example.chatService.chat.service;

import com.example.chatService.chat.domain.Member;
import com.example.chatService.chat.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public Member createMember(String name) {
        Member member = new Member();
        member.setName(name);
        return memberRepository.save(member);
    }
}
