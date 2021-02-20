package com.example.stusocket.service.chatroom;

import com.example.stusocket.entity.chatroom.ChatRoom;
import com.example.stusocket.entity.chatroom.ChatRoomRepository;
import com.example.stusocket.exception.ChatRoomNotFoundException;
import com.example.stusocket.payload.response.ChatRoomListResponse;
import com.example.stusocket.payload.response.ChatRoomResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class ChatRoomServiceImpl implements ChatRoomService {

    private final ChatRoomRepository chatRoomRepository;

    @Override
    public ChatRoomListResponse getChatRooms(Pageable pageable) {
        Page<ChatRoom> chatRooms = chatRoomRepository.findAllBy(pageable);
        List<ChatRoomResponse> chatRoomResponses = new ArrayList<>();

        for(ChatRoom chatRoom : chatRooms) {
            chatRoomResponses.add(
                    ChatRoomResponse.builder()
                            .name(chatRoom.getName())
                            .roomId(chatRoom.getId())
                            .build()
            );
        }

        return ChatRoomListResponse.builder()
                .chatRoomResponses(chatRoomResponses)
                .totalElements(chatRooms.getTotalElements())
                .totalPages(chatRooms.getTotalPages())
                .build();
    }

    @Override
    public ChatRoomResponse createChatRoom(String name) {
        ChatRoom chatRoom = chatRoomRepository.save(
                ChatRoom.builder()
                        .name(name)
                        .build()
        );

        return new ChatRoomResponse(chatRoom.getId(), chatRoom.getName());
    }

    @Override
    public ChatRoomResponse getChatRoom(String roomId) {
        return chatRoomRepository.findById(roomId)
                .map(chatRoom -> new ChatRoomResponse(chatRoom.getId(), chatRoom.getName()))
                .orElseThrow(ChatRoomNotFoundException::new);
    }
}
