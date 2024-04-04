package com.bit.nc4_final_project.entity.community;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "T_COM_TAG")
@Getter
@Setter
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
    @JoinColumn(name = "com_seq")
    @JsonBackReference
    private Community community;


}
