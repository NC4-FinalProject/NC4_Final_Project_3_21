package com.bit.nc4_final_project.dto.travel;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class TravelDetailI12DTO {
    private LocalDateTime opendate;
    private String restdate;
    private String useseason;
    private String usetime;
    private Integer accomcount;
    private Integer expagerange;

}