package com.bit.nc4_final_project.entity.board;

import com.bit.nc4_final_project.dto.board.BoardFileDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "T_COM_FILE")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BoardFile {
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "BoardFileSeqGenerator"
    )
    private Integer seq;

    private String name;
    @Transient
    private String status;
    @Transient
    private String newFileName;

    @ManyToOne
    @JoinColumn(name = "com_board_seq")
    private Board board;

    public BoardFileDTO toDTO() {
        return BoardFileDTO.builder()
                .seq(this.seq)
                .seq(this.board.getSeq())
                .name(this.name)
                .status(this.status)
                .newFileName(this.newFileName)
                .build();
    }
}
