package com.bit.nc4_final_project.dto.community;

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
    private String picture;
    private String description;
    private Integer userSeq;
    // List<CommunityTagDTO> 타입의 필드를 추가하여 CommunityTag 엔터티의 리스트를 담을 수 있게 합니다.
    private List<CommunityTagDTO> tagFileDTOList;


    public Community toEntity() {
        return Community.builder()
                .seq(this.seq)
                .name(this.name)
                .member(this.member)
                .regDate(LocalDateTime.parse(this.regDate))
                .picture(this.picture)
                .build();
    }
}
