package com.bit.nc4_final_project.entity;


import com.bit.nc4_final_project.dto.review.ReviewDTO;
import com.bit.nc4_final_project.entity.community.Community;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "T_COM_REVIEW")
@SequenceGenerator(
        name = "ReviewmentSeqGenerator",
        sequenceName = "T_REVIEW_SEQ",
        initialValue = 1,
        allocationSize = 1
)
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Review {
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "ReviewSeqGenerator"
    )
    @Column(name = "com_review_seq")
    private Integer seq;
    private String title;
    private String content;
    private String writer;
    private Integer rating;
    private LocalDateTime regDate;

    @ManyToOne
    @JoinColumn(name = "user_seq")
    private User user;

    // 몽고 DB연결되어져 있어서 매핑 보류
//    @ManyToOne
//    @JoinColumn(name = "id")
//    private Travel travel;

    @Transient
    private String searchCondition;
    @Transient
    private String searchKeyword;

    public ReviewDTO toDTO() {
        return ReviewDTO.builder()
                .seq(this.seq)
                .title(this.title)
                .content(this.content)
                .writer(this.writer)
                .regDate(LocalDateTime.now())
                .build();
    }


}
