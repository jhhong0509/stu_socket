package com.example.stusocket.service.chat;

import com.example.stusocket.payload.request.ChatMessageRequest;
import com.example.stusocket.payload.response.ChatListResponse;
import org.springframework.data.domain.Pageable;

public interface ChatService {
    void sendMessage(ChatMessageRequest chatMessageRequest);

    ChatListResponse getChatContent(String roomId, Pageable pageable);
}
