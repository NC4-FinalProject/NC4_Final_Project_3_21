package com.bit.nc4_final_project.dto.community;

import com.bit.nc4_final_project.dto.board.BoardFileDTO;
import com.bit.nc4_final_project.entity.community.Community;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class CommunityDTO {
    private Integer seq;
    private String name;
    private int member;
    private String regDate;
    private int cnt;
    private String picture;
    private int capacity;

    private List<BoardFileDTO> boardFileDTOList;
    private String searchCondition;
    private String searchKeyword;


    public Community toEntity() {
        return Community.builder()
                .seq(this.seq)
                .name(this.name)
                .member(this.member)
                .regDate(LocalDateTime.parse(this.regDate))
                .cnt(this.cnt)
                .picture(this.picture)
                .capacity(this.capacity)
                .build();
    }
}
