package com.example.stusocket.payload.response;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class ChatListResponse extends PageResponse {
    private List<ChatResponse> chatResponses;

    @Builder
    public ChatListResponse(int totalPages, Long totalElements, List<ChatResponse> chatResponses) {
        super(totalPages, totalElements);
        this.chatResponses = chatResponses;
    }

}
