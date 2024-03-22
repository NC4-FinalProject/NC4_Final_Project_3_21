package com.bit.nc4_final_project.dto.travel;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class TravelDetailI15DTO {
    private String program;
    private String eventplace;
    private LocalDateTime eventstartdate;
    private LocalDateTime eventenddate;
    private String placeinfo;
    private String usetimefestival;
    private Integer agelimit;
    private String playtime;
    private String spendtimefestival;
    private String eventhomepage;
    private String bookingplace;
    
}