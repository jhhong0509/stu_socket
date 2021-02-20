package com.example.stusocket.payload.response;

import lombok.*;

import java.util.UUID;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChatRoomResponse {
    private String roomId;

    private String name;
}
