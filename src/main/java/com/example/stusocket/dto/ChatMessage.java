package com.example.stusocket.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChatMessage {
    public enum MessageType {
        ENTER,
        TALK
    }
    private MessageType messageType;

    private String roomId;

    private String sender;

    private String message;
}
