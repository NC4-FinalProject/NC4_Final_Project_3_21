package com.bit.nc4_final_project.dto.travel;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class TravelDetailI12DTO extends TravelDetailDTO {
    private String opendate;
    private String restdate;
    private String useseason;
    private String usetime;
    private String accomcount;
    private String expagerange;

}