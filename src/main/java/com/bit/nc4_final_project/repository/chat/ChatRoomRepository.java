package com.bit.nc4_final_project.repository.chat;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.bit.nc4_final_project.entity.chat.ChatMessage;

import java.util.List;

public interface ChatRoomRepository extends MongoRepository <ChatMessage, String>, ChatRepositoryCustom {

    List<ChatMessage> findAllByChatRoomId(String chatRoomId);
}
