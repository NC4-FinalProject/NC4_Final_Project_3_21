package com.bit.nc4_final_project.dto.travel;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class TravelDetailI12DTO extends TravelDetailDTO {
    private LocalDateTime opendate;
    private String restdate;
    private String useseason;
    private String usetime;
    private Integer accomcount;
    private Integer expagerange;

}