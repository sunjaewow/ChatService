package com.example.chatService.chat.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "chatroom")
@Setter
@Getter
@NoArgsConstructor
public class ChatRoom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long chatRoomId;

    private String name;

    @CreationTimestamp
    private LocalDateTime regdate;

    @ManyToMany
    @JoinTable(name = "member_chat",
            joinColumns = @JoinColumn(name = "chatRoom_id"),
    inverseJoinColumns = @JoinColumn(name = "member_id")
    )
    private List<Member> members=new ArrayList<>();
}
