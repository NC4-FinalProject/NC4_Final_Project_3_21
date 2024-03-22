package com.bit.nc4_final_project.entity;

import com.bit.nc4_final_project.dto.travel.TravelDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

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
    private LocalDateTime createdtime;
    private LocalDateTime modifiedtime;
    private Integer viewCnt;

    public TravelDTO toDTO() {
        return TravelDTO.builder()
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
                .build();
    }
}
