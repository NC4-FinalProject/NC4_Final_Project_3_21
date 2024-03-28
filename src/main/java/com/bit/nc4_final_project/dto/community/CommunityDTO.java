package com.bit.nc4_final_project.dto.community;

import com.bit.nc4_final_project.entity.community.Community;
import com.bit.nc4_final_project.entity.community.CommunityTag;
import lombok.*;

import java.time.LocalDateTime;

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
    private CommunityTag communityTag;


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
