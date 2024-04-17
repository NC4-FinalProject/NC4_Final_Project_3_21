package java.com.bit.nc4_final_project.entity.board;

import com.bit.nc4_final_project.entity.User;
import com.bit.nc4_final_project.entity.board.Board;
import com.bit.nc4_final_project.entity.board.BoardLikeId;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "T_COM_BOARD_LIKE")
@AllArgsConstructor
@Builder
@IdClass(BoardLikeId.class)
public class BoardLike {
    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "com_board_seq")
    private Board board;

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_seq")
    private User user;
}
