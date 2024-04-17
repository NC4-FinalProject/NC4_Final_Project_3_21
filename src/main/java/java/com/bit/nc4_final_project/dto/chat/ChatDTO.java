package java.com.bit.nc4_final_project.dto.chat;

import com.bit.nc4_final_project.entity.chat.Chat;
import lombok.*;
 
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class ChatDTO {
    private Integer seq;
    private String makerName;
    private String makerImg;
    private String partnerName;
    private String partnerImg;
    private String lastChat;
    private Integer unreadCnt;

    public Chat toEntity() {
        return Chat.builder()
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
