package com.bit.nc4_final_project.entity.community;

import com.bit.nc4_final_project.dto.community.CommunityDTO;
import com.bit.nc4_final_project.entity.User;
import com.bit.nc4_final_project.entity.board.Board;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "T_COMMUNITY")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Community {
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "CommunitySeqGenerator"
    )
    @Column(name = "community_seq")
    private Integer seq;
    private String name;
    private int member;
    private LocalDateTime regDate;
    private int cnt;
    private String picture;
    private int capacity;

    @Transient
    private String searchCondition;
    @Transient
    private String searchKeyword;
    @OneToMany(mappedBy = "community", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Board> boardList;

    @ManyToOne
    @JoinColumn(name = "user_seq")
    private User user;


    private CommunityDTO toDTO() {
        return CommunityDTO.builder()
                .seq(this.seq)
                .name(this.name)
                .member(this.member)
                .regDate(this.regDate.toString())
                .cnt(this.cnt)
                .picture(this.picture)
                .capacity(this.capacity)
//                .boardFileDTOList(
//                        this.boardFileList.stream().map(
//                                boardFile -> boardFile.toDTO()
//                        ).toList()
//                )
                .build();
    }
}
