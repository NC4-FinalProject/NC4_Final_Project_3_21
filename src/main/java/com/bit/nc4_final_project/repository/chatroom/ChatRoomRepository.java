package com.bit.nc4_final_project.repository.chatroom;

import com.bit.nc4_final_project.document.chat.ChatMessage;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ChatRoomRepository extends MongoRepository<ChatMessage, String> {
    List<ChatMessage> findAllByChatRoomId(String chatRoomId);
}
