package java.com.bit.nc4_final_project.entity.board;


import com.bit.nc4_final_project.dto.board.BoardDTO;
import com.bit.nc4_final_project.entity.User;
import com.bit.nc4_final_project.entity.board.BoardFile;
import com.bit.nc4_final_project.entity.community.Community;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "T_COM_BOARD")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@SequenceGenerator(
        name = "BoardSeqGenerator",
        sequenceName = "T_COM_BOARD_SEQ",
        initialValue = 1,
        allocationSize = 1
)
public class Board {
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "BoardSeqGenerator"
    )
    @Column(name = "com_board_seq")
    private Integer seq;
    private String content;
    private LocalDateTime regDate;


    @OneToMany(mappedBy = "board", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<BoardFile> boardFileList;

    @ManyToOne
    @JoinColumn(name = "user_seq")
    private User user;

    @ManyToOne
    @JoinColumn(name = "com_seq")
    @JsonBackReference
    private Community community;

    public BoardDTO toDTO() {
        return BoardDTO.builder()
                .seq(this.seq)
                .content(this.content)
                .regDate(this.regDate.toString())
                .boardFileDTOList(
                        this.boardFileList.stream().map(
                                boardFile -> boardFile.toDTO()
                        ).toList()
                )
                .build();
    }

    public void addBoardFileList(BoardFile boardFile) {
        this.boardFileList.add(boardFile);
    }
}
