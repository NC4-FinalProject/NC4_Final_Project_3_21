package com.bit.nc4_final_project.entity;

import com.bit.nc4_final_project.dto.user.UserDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "T_USER")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
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
    //    private String location;
    private LocalDateTime userBirth;
    private String userTel;
    private String role;
    private LocalDateTime userRegDate;
    private boolean isActive;
    private LocalDateTime lastLoginDate;
    private String profileImageUrl;


//   @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
//   @Builder.Default
//   @JsonManagedReference
//   private List<UserTag> userTags = new ArrayList<>();
//
//    public void addUserTag(UserTag userTag) {
//       userTags.add(userTag);
//        userTag.setUser(this);
//    }

    public UserDTO toDTO() {
        return UserDTO.builder()
                .seq(this.seq)
                .userId(this.userId)
                .userPw(this.userPw)
                .userName(this.userName)
//                .location(this.location)
                .userBirth(this.userBirth.toString())
                .userTel(this.userTel)
                .role(this.role)
                .userRegDate(this.userRegDate.toString())
                .isActive(this.isActive)
                .lastLoginDate(this.lastLoginDate.toString())
                .profileImageUrl(this.profileImageUrl)
//                .tags(this.userTags.stream().map(UserTag::getContent).collect(Collectors.toList()))
                .build();

    }

    public void setProfileImageUrl(String fileUrl) {
    }
}
