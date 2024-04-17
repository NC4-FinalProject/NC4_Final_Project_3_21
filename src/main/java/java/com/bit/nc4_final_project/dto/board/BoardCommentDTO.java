package java.com.bit.nc4_final_project.dto.board;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class BoardCommentDTO {
    private Integer seq;
    private int like;
    private LocalDateTime regDate;
}
