package com.bit.nc4_final_project.entity.chat;

import com.bit.nc4_final_project.dto.chat.ChatDTO;
import com.bit.nc4_final_project.entity.User;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "T_CHAT")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Chat {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ChatSeqGenerator")
    @Column(name = "chat_seq")
    private Integer seq;

    private String makerId;
    private String partnerId;
    private String partnerImg;
    private String lastChat;
    private Integer unreadCnt;

    public ChatDTO toDTO() {
        return ChatDTO.builder()
                .seq(this.seq)
                .makerId(this.makerId)
                .partnerId(this.partnerId)
                .partnerImg(this.partnerImg)
                .lastChat(this.lastChat)
                .unreadCnt(this.unreadCnt)
                .build();
    }
}
