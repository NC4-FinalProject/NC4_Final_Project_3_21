package java.com.bit.nc4_final_project.dto.friend;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class FriendDTO {
    private Integer seq;
    private Integer fromUserId;
    private String agreeTime;
}
