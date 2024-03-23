package com.bit.nc4_final_project.dto.user;

import com.bit.nc4_final_project.entity.User;
import jakarta.persistence.Column;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
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
    private List<UserTagDTO> tags;

    public List<UserTagDTO> getTags() {
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
