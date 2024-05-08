package com.bit.nc4_final_project.document.travel;

import com.bit.nc4_final_project.dto.travel.*;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "travel")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Travel {
    @Id
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
    @Setter
    private Integer viewCnt;
    private String areaCode;
    private String sigunguCode;

    @Setter
    @Field("detail")
    private TravelDetail detail;

    public TravelDTO toDTO(int bookmarkCnt, String areaName, String sigunguName) {
        TravelDTO.TravelDTOBuilder builder = TravelDTO.builder()
                .id(this.id)
                .contentid(this.contentid)
                .contenttypeid(this.contenttypeid)
                .title(this.title)
                .zipCode(this.zipCode)
                .addr1(this.addr1)
                .addr2(this.addr2)
                .tel(this.tel)
                .cat1(this.cat1)
                .cat2(this.cat2)
                .cat3(this.cat3)
                .firstimage(this.firstimage)
                .firstimage2(this.firstimage2)
                .booktour(this.booktour)
                .cpyrhtDivCd(this.cpyrhtDivCd)
                .mapx(this.mapx)
                .mapy(this.mapy)
                .mlevel(this.mlevel)
                .createdtime(this.createdtime)
                .modifiedtime(this.modifiedtime)
                .viewCnt(this.viewCnt)
                .bookmarkCnt(bookmarkCnt)
                .areaCode(this.areaCode)
                .areaName(areaName)
                .sigunguCode(this.sigunguCode)
                .sigunguName(sigunguName);

        if (this.detail != null) {
            builder.detail(toTravelDetailDTO(this.detail));
        }

        return builder.build();
    }

    private TravelDetailDTO toTravelDetailDTO(TravelDetail detail) {
        return switch (this.contenttypeid) {
            case "12" -> toTravelDetailI12DTO(detail);
            case "14" -> toTravelDetailI14DTO(detail);
            case "15" -> toTravelDetailI15DTO(detail);
            case "28" -> toTravelDetailI28DTO(detail);
            default -> throw new IllegalArgumentException("Unsupported contenttypeid: " + this.contenttypeid);
        };
    }

    private TravelDetailI12DTO toTravelDetailI12DTO(TravelDetail detail) {
        return TravelDetailI12DTO.builder()
                .opendate(detail.getOpendate())
                .restdate(detail.getRestdate())
                .useseason(detail.getUseseason())
                .usetime(detail.getUsetime())
                .accomcount(detail.getAccomcount())
                .expagerange(detail.getExpagerange())
                .build();
    }

    private TravelDetailI14DTO toTravelDetailI14DTO(TravelDetail detail) {
        return TravelDetailI14DTO.builder()
                .restdateculture(detail.getRestdateculture())
                .usefee(detail.getUsefee())
                .spendtime(detail.getSpendtime())
                .accomcountculture(detail.getAccomcountculture())
                .build();
    }

    private TravelDetailI15DTO toTravelDetailI15DTO(TravelDetail detail) {
        return TravelDetailI15DTO.builder()
                .program(detail.getProgram())
                .eventplace(detail.getEventplace())
                .eventstartdate(detail.getEventstartdate())
                .eventenddate(detail.getEventenddate())
                .placeinfo(detail.getPlaceinfo())
                .usetimefestival(detail.getUsetimefestival())
                .agelimit(detail.getAgelimit())
                .playtime(detail.getPlaytime())
                .spendtimefestival(detail.getSpendtimefestival())
                .eventhomepage(detail.getEventhomepage())
                .bookingplace(detail.getBookingplace())
                .build();
    }

    private TravelDetailI28DTO toTravelDetailI28DTO(TravelDetail detail) {
        return TravelDetailI28DTO.builder()
                .openperiod(detail.getOpenperiod())
                .restdateleports(detail.getRestdateleports())
                .accomcountleports(detail.getAccomcountleports())
                .usefeeleports(detail.getUsefeeleports())
                .usetimeleports(detail.getUsetimeleports())
                .expagerangeleports(detail.getExpagerangeleports())
                .build();
    }


}