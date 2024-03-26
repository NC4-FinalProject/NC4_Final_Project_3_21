package com.bit.nc4_final_project.entity.community;

import com.bit.nc4_final_project.entity.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "T_COM_TAG")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommunityTag {
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "TagSeqGenerator"
    )
    @Column(name = "com_tag_seq")
    private Integer seq;
    private String content;

    @ManyToOne
    @JoinColumn(name = "community_seq")
    private Community community;
}
