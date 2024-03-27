package com.bit.nc4_final_project.dto.chat;

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
    
    private int chatRoomId;
    private String receiver;
    private String sender;
    private String chatMessage;
    private Object data;

}
