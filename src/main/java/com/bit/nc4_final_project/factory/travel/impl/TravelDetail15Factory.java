package com.bit.nc4_final_project.factory.travel.impl;

import com.bit.nc4_final_project.entity.TravelDetail;
import com.bit.nc4_final_project.factory.travel.TravelDetailFactory;
import com.bit.nc4_final_project.factory.travel.TravelDetailI15Input;
import com.bit.nc4_final_project.factory.travel.TravelDetailInput;

public class TravelDetail15Factory implements TravelDetailFactory {
    @Override
    public TravelDetail createTravelDetail(TravelDetailInput input) {
        if (!(input instanceof TravelDetailI15Input input15)) {
            throw new IllegalArgumentException("Expected input of type TravelDetail15Input");
        }

        return TravelDetail.builder()
                .homepage(input15.getHomepage())
                .overview(input15.getOverview())
                .program(input15.getProgram())
                .eventplace(input15.getEventplace())
                .eventstartdate(input15.getEventstartdate())
                .eventenddate(input15.getEventenddate())
                .placeinfo(input15.getPlaceinfo())
                .usetimefestival(input15.getUsetimefestival())
                .agelimit(input15.getAgelimit())
                .playtime(input15.getPlaytime())
                .spendtimefestival(input15.getSpendtimefestival())
                .eventhomepage(input15.getEventhomepage())
                .bookingplace(input15.getBookingplace())
                .build();
    }
}
