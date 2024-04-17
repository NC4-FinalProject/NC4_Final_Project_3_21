package java.com.bit.nc4_final_project.entity.board;

import com.bit.nc4_final_project.entity.User;
import com.bit.nc4_final_project.entity.board.BoardComment;
import com.bit.nc4_final_project.entity.board.CommentLikeId;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "T_COM_COMMENT_LIKE")
@AllArgsConstructor
@Builder
@IdClass(CommentLikeId.class)
public class CommentLike {
    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_seq")
    private User user;

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "com_comment_seq")
    private BoardComment boardComment;
}
