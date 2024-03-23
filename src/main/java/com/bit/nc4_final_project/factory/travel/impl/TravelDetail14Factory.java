package com.bit.nc4_final_project.factory.travel.impl;

import com.bit.nc4_final_project.dto.travel.TravelDetailDTO;
import com.bit.nc4_final_project.dto.travel.TravelDetailI14DTO;
import com.bit.nc4_final_project.entity.TravelDetail;
import com.bit.nc4_final_project.factory.travel.TravelDetailFactory;

public class TravelDetail14Factory implements TravelDetailFactory {
    @Override
    public TravelDetail createTravelDetail(TravelDetailDTO input) {
        if (!(input instanceof TravelDetailI14DTO input14)) {
            throw new IllegalArgumentException("Expected input of type TravelDetailI28DTO");
        }

        return TravelDetail.builder()
                .homepage(input14.getHomepage())
                .overview(input14.getOverview())
                .restdateculture(input14.getRestdateculture())
                .usefee(input14.getUsefee())
                .spendtime(input14.getSpendtime())
                .accomcountculture(input14.getAccomcountculture())
                .build();
    }
}
