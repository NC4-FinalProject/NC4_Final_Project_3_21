package com.bit.nc4_final_project.entity.community;

import com.bit.nc4_final_project.dto.community.CommunityDTO;
import com.bit.nc4_final_project.entity.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "T_COMMUNITY")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Community {
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "CommunitySeqGenerator"
    )
    @Column(name = "community_seq")
    private Integer seq;
    private String name;
    private int member;
    private LocalDateTime regDate;
    private String picture;
    private String description;

    @ManyToOne
    @JoinColumn(name = "user_seq")
    private User user;

    @ManyToOne
    @JoinColumn(name = "com_tag_seq")
    private CommunityTag communityTag;


    private CommunityDTO toDTO() {
        return CommunityDTO.builder()
                .seq(this.seq)
                .name(this.name)
                .member(this.member)
                .regDate(this.regDate.toString())
                .picture(this.picture)
                .description(this.description)
                .userSeq(this.user.getSeq())
                .communityTag(this.communityTag.getCommunity().getCommunityTag())
                .build();
    }
}
