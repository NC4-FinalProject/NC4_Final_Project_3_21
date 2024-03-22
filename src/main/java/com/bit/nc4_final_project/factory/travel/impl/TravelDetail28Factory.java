package com.bit.nc4_final_project.factory.travel.impl;

import com.bit.nc4_final_project.entity.TravelDetail;
import com.bit.nc4_final_project.factory.travel.TravelDetailFactory;
import com.bit.nc4_final_project.factory.travel.TravelDetailI28Input;
import com.bit.nc4_final_project.factory.travel.TravelDetailInput;

public class TravelDetail28Factory implements TravelDetailFactory {
    @Override
    public TravelDetail createTravelDetail(TravelDetailInput input) {
        if (!(input instanceof TravelDetailI28Input input28)) {
            throw new IllegalArgumentException("Expected input of type TravelDetail28Input");
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
