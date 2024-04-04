package com.bit.nc4_final_project.dto.recruitment;


import com.bit.nc4_final_project.entity.Recruitment;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class RecruitmentDTO {
    private int seq;
    private String title;
    private String content;
    private String writer;
    private LocalDateTime regDate;
    private String searchCondition;
    private String searchKeyword;
    private String sort;

    public Recruitment toEntity() {
        return Recruitment.builder()
                .seq(this.seq)
                .title(this.title)
                .content(this.content)
                .writer(this.writer)
                .regDate(LocalDateTime.parse(this.regDate.toString()))
                .build();
    }
}
