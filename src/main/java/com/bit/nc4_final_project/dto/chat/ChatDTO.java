package com.bit.nc4_final_project.dto.chat;

import com.bit.nc4_final_project.dto.user.UserDTO;
import com.bit.nc4_final_project.entity.chat.Chat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class ChatDTO {
    private int seq;
    private int chatRoomNo;
    private String partnerName;
    private String partnerImg;
    private String lastChat;
    private int unreadCnt;

    public Chat toEntity() {
        return Chat.builder()
                .seq(this.seq)
                .chatRoomNo(this.chatRoomNo)
                .lastChat(this.lastChat)
                .unreadCnt(this.unreadCnt)
                .build();
    }
}
