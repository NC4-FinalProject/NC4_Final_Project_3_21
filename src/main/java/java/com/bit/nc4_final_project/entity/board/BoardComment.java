package java.com.bit.nc4_final_project.entity.board;

import com.bit.nc4_final_project.entity.board.Board;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "T_COM_COMMENT")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BoardComment {
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "CommentSeqGenerator"
    )
    @Column(name = "com_comment_seq")
    private Integer seq;
    private int likeCnt;
    private LocalDateTime regDate;

    @ManyToOne
    @JoinColumn(name = "com_board_seq")
    private Board board;

    @ManyToOne
    @JoinColumn(name = "parent_comment_seq")
    private BoardComment boardComment;
}
