package com.bit.nc4_final_project.dto.chat;

import lombok.*;
import org.joda.time.LocalDateTime;

import com.bit.nc4_final_project.entity.chat.ChatMessage;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ChatMessageDTO {

    private String messageId;
    private String chatRoomId;
    private String sender;
    private String message;
    private String sendDate;

    public ChatMessage toEntity() {
        return ChatMessage.builder()
                .messageId(this.messageId)
                .chatRoomId(this.chatRoomId)
                .sender(this.sender)
                .message(this.message)
                .sendDate(LocalDateTime.parse(this.sendDate))
                .build();
    }
}
