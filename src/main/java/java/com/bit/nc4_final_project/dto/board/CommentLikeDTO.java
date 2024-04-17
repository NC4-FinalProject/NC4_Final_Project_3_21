package java.com.bit.nc4_final_project.dto.board;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class CommentLikeDTO {
    private Integer user;
    private Integer boardComment;
}
