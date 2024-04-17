package com.bit.nc4_final_project.dto.community;

import com.bit.nc4_final_project.dto.board.BoardDTO;
import com.bit.nc4_final_project.dto.user.UserDTO;
import com.bit.nc4_final_project.entity.User;
import com.bit.nc4_final_project.entity.community.Community;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
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
    private UserDTO user;
    // List<CommunityTagDTO> 타입의 필드를 추가하여 CommunityTag 엔터티의 리스트를 담을 수 있게 합니다.
    private List<CommunityTagDTO> tags;


    public Community toEntity(User user) {
        return Community.builder()
                .seq(this.seq)
                .name(this.name)
                .member(this.member)
                .description(this.description)
                .regDate(LocalDateTime.parse(this.regDate))
                .picture(this.picture)
                .communityTags(new ArrayList<>())
                .user(user)
                .build();
    }
}
