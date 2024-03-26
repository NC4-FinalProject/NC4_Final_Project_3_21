package com.bit.nc4_final_project.dto.user;

import com.bit.nc4_final_project.entity.User;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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

    public List<String> getTags() {
        if (tags == null) {
            tags = new ArrayList<>();
        }
        return tags;
    }

    public User toEntity() {
        return User.builder()
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
                .build();

    }
}
