package com.bit.nc4_final_project.repository.chat;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.bit.nc4_final_project.entity.chat.ChatMessage;

public interface ChatMessageRepository extends MongoRepository <ChatMessage, String>{
    
}
