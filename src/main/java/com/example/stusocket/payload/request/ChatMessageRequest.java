package com.example.stusocket.payload.request;

import com.example.stusocket.entity.chatroom.ChatRoom;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChatMessageRequest {
    public enum MessageType {
        JOIN,
        TALK
    }
    private MessageType messageType;

    private String roomId;

    private String sender;

    private String message;


}
