package java.com.bit.nc4_final_project.entity.board;

import com.bit.nc4_final_project.entity.User;
import com.bit.nc4_final_project.entity.board.Board;
import com.bit.nc4_final_project.entity.board.BoardBookmarkId;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "T_COM_BOARD_BOOKMARK")
@AllArgsConstructor
@Builder
@IdClass(BoardBookmarkId.class)
public class BoardBookmark {
    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_seq")
    private User user;

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "com_board_seq")
    private Board board;
}
