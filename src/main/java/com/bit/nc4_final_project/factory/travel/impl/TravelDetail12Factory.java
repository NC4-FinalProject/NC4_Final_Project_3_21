package com.bit.nc4_final_project.factory.travel.impl;

import com.bit.nc4_final_project.entity.TravelDetail;
import com.bit.nc4_final_project.factory.travel.TravelDetailFactory;
import com.bit.nc4_final_project.factory.travel.TravelDetailI12Input;
import com.bit.nc4_final_project.factory.travel.TravelDetailInput;

public class TravelDetail12Factory implements TravelDetailFactory {
    @Override
    public TravelDetail createTravelDetail(TravelDetailInput input) {
        if (!(input instanceof TravelDetailI12Input input12)) {
            throw new IllegalArgumentException("Expected input of type TravelDetail12Input");
        }

        return TravelDetail.builder()
                .homepage(input12.getHomepage())
                .overview(input12.getOverview())
                .opendate(input12.getOpendate())
                .restdate(input12.getRestdate())
                .useseason(input12.getUseseason())
                .usetime(input12.getUsetime())
                .accomcount(input12.getAccomcount())
                .expagerange(input12.getExpagerange())
                .build();
    }
}
