package com.bit.nc4_final_project.repository.board;

import com.bit.nc4_final_project.entity.board.Board;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Board, Integer> {
}
