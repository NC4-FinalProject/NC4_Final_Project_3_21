package com.bit.nc4_final_project.dto.travel;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class TravelDTO {
    private String id;
    private String contentid;
    private String contenttypeid;
    private String title;
    private String zipCode;
    private String addr1;
    private String addr2;
    private String tel;
    private String cat1;
    private String cat2;
    private String cat3;
    private String firstimage;
    private String firstimage2;
    private String booktour;
    private String cpyrhtDivCd;
    private String mapx;
    private String mapy;
    private String mlevel;
    private LocalDateTime createdtime;
    private LocalDateTime modifiedtime;
    private Integer viewCnt;
}
