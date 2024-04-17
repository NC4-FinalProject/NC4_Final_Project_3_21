package java.com.bit.nc4_final_project.repository.board;

import com.bit.nc4_final_project.entity.board.BoardBookmark;
import com.bit.nc4_final_project.entity.board.BoardBookmarkId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardBookmarkRepository extends JpaRepository<BoardBookmark, BoardBookmarkId> {
}
