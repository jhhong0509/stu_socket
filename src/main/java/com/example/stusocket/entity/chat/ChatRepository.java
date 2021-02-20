package com.example.stusocket.entity.chat;

import com.example.stusocket.entity.chatroom.ChatRoom;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChatRepository extends JpaRepository<Chat, Long> {

    Page<Chat> findAllByChatRoomOrderByIdDesc(ChatRoom chatRoom, Pageable pageable);

}
