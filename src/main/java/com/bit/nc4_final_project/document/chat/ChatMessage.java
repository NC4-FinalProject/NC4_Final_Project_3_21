package com.bit.nc4_final_project.document.chat;

import com.bit.nc4_final_project.dto.chat.ChatMessageDTO;
import jakarta.persistence.Id;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

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
    private String img;
    private LocalDateTime sendDate;

    public ChatMessageDTO toDTO() {
        return ChatMessageDTO.builder()
                .id(this.id)
                .chatRoomId(this.chatRoomId)
                .sender(this.sender)
                .message(this.message)
                .img(this.img)
                .sendDate(this.sendDate)
                .build();
    }
}
