package com.bit.nc4_final_project.dto.travel;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

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
    private String createdtime;
    private String modifiedtime;
    private Integer viewCnt;
    @Setter
    private boolean isBookmark;
    private Integer bookmarkCnt;
    private String areaCode;
    private String areaName;
    private String sigunguCode;
    private String sigunguName;
    private TravelDetailDTO detail;
    @Setter
    private PetTravelDTO petTravel;

    private String searchArea;
    private String searchSigungu;
    private String searchKeyword;
    private String sort;
}
