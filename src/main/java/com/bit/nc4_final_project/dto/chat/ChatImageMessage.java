package com.bit.nc4_final_project.dto.chat;

import lombok.*;

import java.time.LocalDateTime;
import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class ChatImageMessage {
    private String chatRoomId;
    private String sender;
    private LocalDateTime sendDate;

    public Map<String, String> toMap() {
        return Map.of(
                "chatRoomId", this.chatRoomId,
                "sender", this.sender,
                "sendDate", this.sendDate.toString()
        );
    }
}
