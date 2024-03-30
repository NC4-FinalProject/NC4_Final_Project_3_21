package com.bit.nc4_final_project.entity.chat;

import org.joda.time.LocalDateTime;
import org.springframework.data.mongodb.core.mapping.Document;

import com.bit.nc4_final_project.dto.chat.ChatMessageDTO;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Document(collection = "chat")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ChatMessage {
    
    @Id
    private String chatId;
    private String chatRoomNo;
    private String sender;
    private String message;
    private LocalDateTime sendDate;

    public ChatMessageDTO toDTO() {
        return ChatMessageDTO.builder()
                .chatId(this.chatId)
                .sender(this.sender)
                .message(this.message)
                .sendDate(this.sendDate.toString())
                .build();
    }
}
