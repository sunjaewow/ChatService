package com.example.chatService.chat.controller;

import com.example.chatService.chat.dto.CreateMemberDto;
import com.example.chatService.chat.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {
    private final MemberService memberService;

    @PostMapping
    private ResponseEntity<?> createMember(@RequestBody CreateMemberDto createMemberDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(memberService.createMember(createMemberDto.getName()));
    }
}
