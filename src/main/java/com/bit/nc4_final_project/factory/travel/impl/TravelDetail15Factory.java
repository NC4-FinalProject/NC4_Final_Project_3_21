package com.bit.nc4_final_project.factory.travel.impl;

import com.bit.nc4_final_project.dto.travel.TravelDetailDTO;
import com.bit.nc4_final_project.dto.travel.TravelDetailI15DTO;
import com.bit.nc4_final_project.entity.TravelDetail;
import com.bit.nc4_final_project.factory.travel.TravelDetailFactory;

public class TravelDetail15Factory implements TravelDetailFactory {
    @Override
    public TravelDetail createTravelDetail(TravelDetailDTO input) {
        if (!(input instanceof TravelDetailI15DTO input15)) {
            throw new IllegalArgumentException("Expected input of type TravelDetailI28DTO");
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
