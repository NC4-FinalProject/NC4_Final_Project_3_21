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

    private String id;
    private String chatRoomId;
    private String sender;
    private String message;
    private String sendDate;

    public ChatMessage toEntity() {
        return ChatMessage.builder()
                .id(this.id)
                .chatRoomId(this.chatRoomId)
                .sender(this.sender)
                .message(this.message)
                .sendDate(LocalDateTime.parse(this.sendDate))
                .build();
    }
}
