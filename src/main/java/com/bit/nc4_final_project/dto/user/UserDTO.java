package com.bit.nc4_final_project.dto.user;

import com.bit.nc4_final_project.entity.User;
import com.bit.nc4_final_project.entity.UserTag;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class UserDTO {
    private Integer seq;
    private String id;
    private String pw;
    private String nickname;
    private String location;
    private String birth;
    private String tel;
    private String role;
    private String regDate;
    private boolean isActive;
    private String lastLoginDate;
    private String token;
    private List<String> tags;
    private String profileImageUrl;
    public List<String> getTags() {
        if (tags == null) {
            tags = new ArrayList<>();
        }
        return tags;
    }

    public User toEntity() {
        User user = User.builder()
                .seq(this.seq)
                .id(this.id)
                .pw(this.pw)
                .nickname(this.nickname)
                .location(this.location)
                .birth(LocalDateTime.parse(this.birth))
                .tel(this.tel)
                .role(this.role)
                .regDate(LocalDateTime.now())
                .isActive(this.isActive)
                .lastLoginDate(LocalDateTime.parse(this.lastLoginDate))
                .profileImageUrl(this.profileImageUrl)
                .build();

        if (this.tags != null) {
            List<UserTag> userTags = this.tags.stream()
                    .map(tag -> UserTag.builder().content(tag).user(user).build())
                    .collect(Collectors.toList());
            user.getUserTags().addAll(userTags);
        }

        return user;
    }
}
