package com.example.stusocket.entity.chat;

import com.example.stusocket.entity.chatroom.ChatRoom;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "chat_tbl")
public class Chat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String content;

    private String sender;

    @ManyToOne
    @JsonManagedReference
    @JoinColumn(name = "chatRoomId")
    private ChatRoom chatRoom;
}
