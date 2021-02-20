package com.example.stusocket.service.chatroom;

import com.example.stusocket.payload.response.ChatRoomListResponse;
import com.example.stusocket.payload.response.ChatRoomResponse;
import org.springframework.data.domain.Pageable;

public interface ChatRoomService {
    ChatRoomListResponse getChatRooms(Pageable pageable);

    ChatRoomResponse createChatRoom(String name);

    ChatRoomResponse getChatRoom(String roomId);
}
