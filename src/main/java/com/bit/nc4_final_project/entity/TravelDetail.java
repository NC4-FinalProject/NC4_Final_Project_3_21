package com.bit.nc4_final_project.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "travel_detail")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TravelDetail {
    private String contentid;
    private String contenttypeid;
    private String homepage;
    private String overview;
    private LocalDateTime opendate;
    private String restdate;
    private String useseason;
    private String usetime;
    private Integer accomcount;
    private Integer expagerange;
    private String restdateculture;
    private Integer usefee;
    private String spendtime;
    private String accomcountculture;
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
    private String openperiod;
    private String restdateleports;
    private Integer accomcountleports;
    private Integer usefeeleports;
    private String usetimeleports;
    private Integer expagerangeleports;
}
