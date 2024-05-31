package com.bit.nc4_final_project.entity;

import com.bit.nc4_final_project.dto.user.UserDTO;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "T_USER")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@SequenceGenerator(
        name = "UserSeqGenerator",
        sequenceName = "T_USER_SEQ",
        initialValue = 1,
        allocationSize = 1
)
public class User {
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "UserSeqGenerator"
    )
    @Column(name = "user_seq")
    private Integer seq;
    @Column(unique = true)
    private String userId;
    private String userPw;
    private String userName;
    // private String location;
    private String areaCode;
    private String areaName;
    private String sigunguCode;
    private String sigunguName;
    @Column(nullable = true)
    private LocalDateTime userBirth;
    @Column(nullable = true)
    private String userTel;
    private String role;
    private LocalDateTime userRegDate;
    private boolean isActive;
    private LocalDateTime lastLoginDate;
    @Column(nullable = true)
    private String profileImageUrl;


   @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
   @Builder.Default
   @JsonManagedReference
   private List<UserTag> userTags = new ArrayList<>();

    public void addUserTag(UserTag userTag) {
       userTags.add(userTag);
        userTag.setUser(this);
    }

    public void removeUserTag(UserTag userTag) {
        userTags.remove(userTag);
        userTag.setUser(null);
    }

    public UserDTO toDTO() {
        return UserDTO.builder()
                .seq(this.seq)
                .userId(this.userId)
                .userPw(this.userPw)
                .userName(this.userName)
//                .location(this.location)
                .areaCode(this.areaCode == null ? null : this.areaCode)
                .areaName(this.areaName == null ? null : this.areaName)
                .sigunguCode(this.sigunguCode == null ? null : this.sigunguCode)
                .sigunguName(this.sigunguName == null ? null : this.sigunguName)
                .userBirth(this.userBirth == null ? null : this.userBirth.toString())
                .userTel(this.userTel == null ? null : this.userTel)
                .role(this.role)
                .userRegDate(this.userRegDate.toString())
                .isActive(this.isActive)
                .lastLoginDate(this.lastLoginDate.toString())
                .profileImageUrl(this.profileImageUrl == null ? null : this.profileImageUrl)
                .tags(this.userTags.stream().map(UserTag::getContent).collect(Collectors.toList()))
                .build();

    }



    public void setUserPw(String userPw) {
        this.userPw = userPw;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setUserTel(String userTel) {
        this.userTel = userTel;
    }

    public void setProfileImageUrl(String fileUrl) {
        this.profileImageUrl = fileUrl;
    }

    public void setTags(List<UserTag> newTags) {
        // 현재 태그 목록을 삭제합니다.
        for (UserTag tag : new ArrayList<>(userTags)) {
            removeUserTag(tag);
        }

        // 새로운 태그 목록을 추가합니다.
        if (newTags != null) {
            for (UserTag newTag : newTags) {
                addUserTag(newTag);
            }
        }
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public void setSigunguCode(String sigunguCode) {
        this.sigunguCode = sigunguCode;
    }

    public void setSigunguName(String sigunguName) {
        this.sigunguName = sigunguName;
    }
}
