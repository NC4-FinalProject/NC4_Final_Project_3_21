package com.bit.nc4_final_project.entity.board;

import com.bit.nc4_final_project.entity.User;
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
    private Integer id;
    private String path;
    private String name;
    private String field;

    @ManyToOne
    @JoinColumn(name = "com_board_seq")
    private Board board;
}
