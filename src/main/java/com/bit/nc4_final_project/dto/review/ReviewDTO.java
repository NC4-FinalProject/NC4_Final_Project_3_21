package com.bit.nc4_final_project.dto.review;


import com.bit.nc4_final_project.entity.Recruitment;
import com.bit.nc4_final_project.entity.Review;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class ReviewDTO {
    private int seq;
    private String title;
    private String content;
    private String writer;
    private int rating;
    private LocalDateTime regDate;
    private String searchCondition;
    private String searchKeyword;

    public Review toEntity() {
        return Review.builder()
                .seq(this.seq)
                .title(this.title)
                .content(this.content)
                .writer(this.writer)
                .rating(this.rating)
                .regDate(LocalDateTime.now())
                .build();
    }
}
