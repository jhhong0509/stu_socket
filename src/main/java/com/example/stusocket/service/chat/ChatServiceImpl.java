package com.example.stusocket.service.chat;

import com.example.stusocket.entity.chat.Chat;
import com.example.stusocket.entity.chat.ChatRepository;
import com.example.stusocket.entity.chatroom.ChatRoom;
import com.example.stusocket.entity.chatroom.ChatRoomRepository;
import com.example.stusocket.exception.ChatRoomNotFoundException;
import com.example.stusocket.payload.request.ChatMessageRequest;
import com.example.stusocket.payload.response.ChatListResponse;
import com.example.stusocket.payload.response.ChatResponse;
import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ChatServiceImpl implements ChatService{

    private final SimpMessageSendingOperations simpMessageSendingOperations;
    private final ChatRepository chatRepository;
    private final ChatRoomRepository chatRoomRepository;

    @Override
    public void sendMessage(ChatMessageRequest chatMessageRequest) {
        if(chatMessageRequest.getMessageType().equals(ChatMessageRequest.MessageType.JOIN)) {
            chatMessageRequest.setMessage(chatMessageRequest.getSender()+"님이 입장하셨습니다.");
        }

        chatRoomRepository.findById(chatMessageRequest.getRoomId())
                .map(room -> chatRepository.save(
                        Chat.builder()
                                .chatRoom(room)
                                .sender(chatMessageRequest.getSender())
                                .content(chatMessageRequest.getMessage())
                                .build()
                ))
                .orElseThrow(ChatRoomNotFoundException::new);

        simpMessageSendingOperations.convertAndSend("/sub/chat/room/" + chatMessageRequest.getRoomId(), chatMessageRequest);
    }

    @Override
    public ChatListResponse getChatContent(String roomId, Pageable pageable) {
        ChatRoom chatRoom = chatRoomRepository.findById(roomId)
                .orElseThrow(ChatRoomNotFoundException::new);
        Page<Chat> chats = chatRepository.findAllByChatRoomOrderByIdDesc(chatRoom, pageable);
        List<ChatResponse> chatResponseList = new ArrayList<>();

        for(Chat chat : chats) {
            chatResponseList.add(
                    ChatResponse.builder()
                            .isMine(true)
                            .message(chat.getContent())
                            .sender(chat.getSender())
                            .build()
            );
        }

        return ChatListResponse.builder()
                .chatResponses(chatResponseList)
                .totalElements(chats.getTotalElements())
                .totalPages(chats.getTotalPages())
                .build();
    }

}
