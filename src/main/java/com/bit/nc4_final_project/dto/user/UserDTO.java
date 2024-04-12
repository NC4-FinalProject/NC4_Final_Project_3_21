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
    private String userId;
    private String userPw;
    private String userName;
//    private String location;
    private String userBirth;
    private String userTel;
    private String role;
    private String userRegDate;
    private boolean isActive;
    private String lastLoginDate;
    private String token;
//    private List<String> tags;
    private String profileImageUrl;

//    public List<String> getTags() {
//        if (tags == null) {
//            tags = new ArrayList<>();
//        }
//        return tags;
//    }

    public User toEntity() {
        return User.builder()
                .seq(this.seq)
                .userId(this.userId)
                .userPw(this.userPw)
                .userName(this.userName)
//                .location(this.location)
                .userBirth(this.userBirth == null ? null : LocalDateTime.parse(this.userBirth))
                .userTel(this.userTel == null ? null : this.userTel)
                .role(this.role)
                .userRegDate(LocalDateTime.parse(this.userRegDate))
                .isActive(this.isActive)
                .lastLoginDate(LocalDateTime.parse(this.lastLoginDate))
                .profileImageUrl(this.profileImageUrl == null ? null : this.profileImageUrl)
                .build();

//        if (this.tags != null) {
//            List<UserTag> userTags = this.tags.stream()
//                    .map(tag -> UserTag.builder().content(tag).user(user).build())
//                    .collect(Collectors.toList());
//            user.getUserTags().addAll(userTags);
//        }
    }
}
