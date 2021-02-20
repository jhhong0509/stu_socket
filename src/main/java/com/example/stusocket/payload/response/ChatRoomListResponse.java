package com.example.stusocket.payload.response;

import lombok.Builder;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class ChatRoomListResponse extends PageResponse{
    private List<ChatRoomResponse> chatRoomResponses;

    @Builder
    public ChatRoomListResponse(Long totalElements, int totalPages, List<ChatRoomResponse> chatRoomResponses) {
        super(totalPages, totalElements);
        this.chatRoomResponses = chatRoomResponses;
    }
}
