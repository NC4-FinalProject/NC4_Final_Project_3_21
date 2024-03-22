package com.bit.nc4_final_project.factory.travel;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class TravelDetailI12Input extends TravelDetailInput {
    private LocalDateTime opendate;
    private String restdate;
    private String useseason;
    private String usetime;
    private Integer accomcount;
    private Integer expagerange;
}
