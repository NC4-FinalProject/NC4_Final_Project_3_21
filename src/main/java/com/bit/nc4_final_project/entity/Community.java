package com.bit.nc4_final_project.entity;

import com.bit.nc4_final_project.dto.community.CommunityDTO;
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
    private int cnt;
    private String picture;
    private int capacity;


    private CommunityDTO toDTO() {
        return CommunityDTO.builder()
                .seq(this.seq)
                .name(this.name)
                .member(this.member)
                .regDate(this.regDate.toString())
                .cnt(this.cnt)
                .picture(this.picture)
                .capacity(this.capacity)
                .build();
    }
}
