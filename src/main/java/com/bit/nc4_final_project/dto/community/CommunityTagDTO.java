package com.bit.nc4_final_project.dto.community;

import com.bit.nc4_final_project.entity.community.Community;
import com.bit.nc4_final_project.entity.community.CommunityTag;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class CommunityTagDTO {
    private Integer seq;
    private String content;

    public CommunityTag toEntity(Community community) {
        return CommunityTag.builder()
                .seq(this.seq)
                .content(this.content)
                .community(community)
                .build();
    }
}
