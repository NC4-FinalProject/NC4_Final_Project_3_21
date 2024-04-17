package java.com.bit.nc4_final_project.dto.board;

import com.bit.nc4_final_project.dto.board.BoardFileDTO;
import com.bit.nc4_final_project.entity.User;
import com.bit.nc4_final_project.entity.board.Board;
import com.bit.nc4_final_project.entity.community.Community;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class BoardDTO {
    private Integer seq;
    private String content;
    private String regDate;
    private List<BoardFileDTO> boardFileDTOList;
    private User user;
    private Community community;


    public Board toEntity() {
        return Board.builder()
                .seq(this.seq)
                .content(this.content)
                .regDate(LocalDateTime.parse(this.regDate))
                .boardFileList(new ArrayList<>())
                .user(user)
                .community(community)
                .build();
    }
}
