package com.example.stusocket.controller;

import com.example.stusocket.payload.request.ChatMessageRequest;
import com.example.stusocket.service.chat.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("/chat")
public class ChatController {

    private final SimpMessageSendingOperations simpMessageSendingOperations;
    private final ChatService chatService;

    @MessageMapping("/message")
    public void message(ChatMessageRequest chatMessageRequest) {
        chatService.sendMessage(chatMessageRequest);
    }
}
