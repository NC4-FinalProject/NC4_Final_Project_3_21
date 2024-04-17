package java.com.bit.nc4_final_project.repository.board;

import com.bit.nc4_final_project.entity.board.BoardComment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardCommentRepository extends JpaRepository<BoardComment, Integer> {
}
