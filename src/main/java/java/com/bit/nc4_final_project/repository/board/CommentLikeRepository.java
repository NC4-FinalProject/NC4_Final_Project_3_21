package java.com.bit.nc4_final_project.repository.board;

import com.bit.nc4_final_project.entity.board.CommentLike;
import com.bit.nc4_final_project.entity.board.CommentLikeId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentLikeRepository extends JpaRepository<CommentLike, CommentLikeId> {
}
