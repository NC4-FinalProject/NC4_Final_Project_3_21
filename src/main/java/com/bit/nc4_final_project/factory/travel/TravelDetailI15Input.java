package com.bit.nc4_final_project.factory.travel;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class TravelDetailI15Input extends TravelDetailInput {
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
