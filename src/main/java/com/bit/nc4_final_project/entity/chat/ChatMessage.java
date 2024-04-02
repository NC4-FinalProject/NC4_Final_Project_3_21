package com.bit.nc4_final_project.entity.chat;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.*;
import org.joda.time.LocalDateTime;
import org.springframework.data.mongodb.core.mapping.Document;

import com.bit.nc4_final_project.dto.chat.ChatMessageDTO;

import jakarta.persistence.Id;

import java.util.Date;

@Document(collection = "chat")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ChatMessage {
    
    @Id
    private String id;

    private String chatRoomId;
    private String sender;
    private String message;
//    private Date sendDate;

    public ChatMessageDTO toDTO() {
        return ChatMessageDTO.builder()
                .id(this.id)
                .chatRoomId(this.chatRoomId)
                .sender(this.sender)
                .message(this.message)
//                .sendDate(this.sendDate.toString())
                .build();
    }
}
