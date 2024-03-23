package com.bit.nc4_final_project.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "T_USER_TAG")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserTag {
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "TagSeqGenerator"
    )
    @Column(name = "user_tag_seq")
    private Integer seq;
    private String content;
}
