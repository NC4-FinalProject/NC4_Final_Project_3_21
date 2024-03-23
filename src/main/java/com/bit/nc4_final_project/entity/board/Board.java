package com.bit.nc4_final_project.entity.board;


import com.bit.nc4_final_project.entity.User;
import com.bit.nc4_final_project.entity.community.Community;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "T_COM_BOARD")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
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

    @ManyToOne
    @JoinColumn(name = "user_seq")
    private User user;

    @ManyToOne
    @JoinColumn(name = "community_seq")
    private Community community;
}
