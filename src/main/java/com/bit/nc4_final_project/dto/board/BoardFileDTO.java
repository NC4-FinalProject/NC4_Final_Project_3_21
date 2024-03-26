package com.bit.nc4_final_project.dto.board;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class BoardFileDTO {
    private Integer id;
    private String path;
    private String name;
    private String field;
}
