package com.bit.nc4_final_project.factory.travel.impl;

import com.bit.nc4_final_project.dto.travel.TravelDetailDTO;
import com.bit.nc4_final_project.dto.travel.TravelDetailI28DTO;
import com.bit.nc4_final_project.entity.TravelDetail;
import com.bit.nc4_final_project.factory.travel.TravelDetailFactory;

public class TravelDetail28Factory implements TravelDetailFactory {
    @Override
    public TravelDetail createTravelDetail(TravelDetailDTO input) {
        if (!(input instanceof TravelDetailI28DTO input28)) {
            throw new IllegalArgumentException("Expected input of type TravelDetailI28DTO");
        }

        return TravelDetail.builder()
                .homepage(input28.getHomepage())
                .overview(input28.getOverview())
                .openperiod(input28.getOpenperiod())
                .restdateleports(input28.getRestdateleports())
                .accomcountleports(input28.getAccomcountleports())
                .usefeeleports(input28.getUsefeeleports())
                .usetimeleports(input28.getUsetimeleports())
                .expagerangeleports(input28.getExpagerangeleports())
                .build();
    }
}