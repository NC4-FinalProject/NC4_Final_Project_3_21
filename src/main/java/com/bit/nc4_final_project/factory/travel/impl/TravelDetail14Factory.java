package com.bit.nc4_final_project.factory.travel.impl;

import com.bit.nc4_final_project.entity.TravelDetail;
import com.bit.nc4_final_project.factory.travel.TravelDetailFactory;
import com.bit.nc4_final_project.factory.travel.TravelDetailI14Input;
import com.bit.nc4_final_project.factory.travel.TravelDetailInput;

public class TravelDetail14Factory implements TravelDetailFactory {
    @Override
    public TravelDetail createTravelDetail(TravelDetailInput input) {
        if (!(input instanceof TravelDetailI14Input input14)) {
            throw new IllegalArgumentException("Expected input of type TravelDetail14Input");
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
