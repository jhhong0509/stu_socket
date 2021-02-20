package com.example.stusocket.controller;

import com.example.stusocket.payload.response.ChatRoomListResponse;
import com.example.stusocket.payload.response.ChatRoomResponse;
import com.example.stusocket.entity.chatroom.ChatRoom;
import com.example.stusocket.entity.chatroom.ChatRoomRepository;
import com.example.stusocket.service.chatroom.ChatRoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

// @RestController 는 JSON 형태로 객체 데이터를 반환하는 역할을 하고, @Controller 는 주로 View 를 반환하기 위해 사용된다.
@RestController
@RequiredArgsConstructor
@RequestMapping("/chat")
public class ChatRoomController {

    private final ChatRoomService chatRoomService;

    @GetMapping("/rooms")
    public ChatRoomListResponse room(Pageable pageable) {
        return chatRoomService.getChatRooms(pageable);
    }

    @PostMapping("/room")
    @ResponseStatus(HttpStatus.CREATED)
    public ChatRoomResponse createRoom(@RequestParam String name) {
        return chatRoomService.createChatRoom(name);
    }

    @GetMapping("/room/{roomId}")
    @ResponseBody
    public ChatRoomResponse roomInfo(@PathVariable String roomId) throws ChangeSetPersister.NotFoundException {
        return chatRoomService.getChatRoom(roomId);
    }
}
