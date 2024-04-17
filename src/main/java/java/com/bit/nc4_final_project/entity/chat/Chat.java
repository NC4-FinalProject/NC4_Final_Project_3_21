package java.com.bit.nc4_final_project.entity.chat;

import com.bit.nc4_final_project.dto.chat.ChatDTO;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "T_CHAT")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class Chat {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ChatSeqGenerator")
    @Column(name = "chat_seq")
    private Integer seq;
    private String makerName;
    private String makerImg;
    private String partnerName;
    private String partnerImg;
    private String lastChat;
    private Integer unreadCnt;

    public ChatDTO toDTO() {
        return ChatDTO.builder()
                .seq(this.seq)
                .makerName(this.makerName)
                .makerImg(this.makerImg)
                .partnerName(this.partnerName)
                .partnerImg(this.partnerImg)
                .lastChat(this.lastChat)
                .unreadCnt(this.unreadCnt)
                .build();
    }
}
