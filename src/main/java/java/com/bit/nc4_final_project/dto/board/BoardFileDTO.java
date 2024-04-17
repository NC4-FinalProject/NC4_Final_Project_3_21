package java.com.bit.nc4_final_project.dto.board;

import com.bit.nc4_final_project.entity.board.Board;
import com.bit.nc4_final_project.entity.board.BoardFile;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Transient;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class BoardFileDTO {
    private Integer seq;

    private String name;
    @Transient
    private String status;
    @Transient
    private String newFileName;
    @ManyToOne
    @JoinColumn(name = "com_board_seq")
    private Board board;

    public BoardFile toEntity(Board board) {
        return BoardFile.builder()
                .seq(this.seq)
                .board(board)
                .name(this.name)
                .status(this.status)
                .newFileName(this.newFileName)
                .build();
    }
}
