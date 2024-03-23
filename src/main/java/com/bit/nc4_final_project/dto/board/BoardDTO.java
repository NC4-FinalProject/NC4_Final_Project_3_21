package com.bit.nc4_final_project.dto.board;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class BoardDTO {
    private Integer seq;
    private String content;
    private LocalDateTime regDate;
}
