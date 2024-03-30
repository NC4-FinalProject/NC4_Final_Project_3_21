package com.bit.nc4_final_project.dto.chat;

import org.joda.time.LocalDateTime;

import com.bit.nc4_final_project.entity.chat.ChatMessage;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ChatMessageDTO {
    
    private String chatId;
    private String chatRoomNo;
    private String sender;
    private String message;
    private String sendDate;

    public ChatMessage toEntity() {
        return ChatMessage.builder()
                .chatId(this.chatId)
                .sender(this.sender)
                .message(this.message)
                .sendDate(LocalDateTime.parse(this.sendDate))
                .build();
    }
}
