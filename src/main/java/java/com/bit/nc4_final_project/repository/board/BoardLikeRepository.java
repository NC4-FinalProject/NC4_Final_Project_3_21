package java.com.bit.nc4_final_project.repository.board;

import com.bit.nc4_final_project.entity.board.BoardLike;
import com.bit.nc4_final_project.entity.board.BoardLikeId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardLikeRepository extends JpaRepository<BoardLike, BoardLikeId> {
}
