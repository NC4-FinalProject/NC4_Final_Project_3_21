package com.bit.nc4_final_project.dto.chat;

import com.bit.nc4_final_project.document.chat.ChatMessage;
import lombok.*;

import java.time.LocalDateTime;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ChatMessageDTO {

    private String id;
    private String chatRoomId;
    private String sender;
    private String message;
    private String img;
    private LocalDateTime sendDate;

    public ChatMessage toEntity() {
        return ChatMessage.builder()
                .id(this.id)
                .chatRoomId(this.chatRoomId)
                .sender(this.sender)
                .message(this.message)
                .img(this.img)
                .sendDate(this.sendDate)
                .build();
    }
}
