package com.bit.nc4_final_project.factory.travel.impl;

import com.bit.nc4_final_project.document.travel.TravelDetail;
import com.bit.nc4_final_project.factory.travel.TravelDetailFactory;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;

public class TravelDetail15Factory implements TravelDetailFactory {
    @Override
    public TravelDetail createTravelDetail(String homepage, String overview, JSONObject jsonTravel) throws JSONException {

        return TravelDetail.builder()
                .homepage(homepage)
                .overview(overview)
                .program(jsonTravel.getString("program"))
                .eventplace(jsonTravel.getString("eventplace"))
                .eventstartdate(jsonTravel.getString("eventstartdate"))
                .eventenddate(jsonTravel.getString("eventenddate"))
                .placeinfo(jsonTravel.getString("placeinfo"))
                .usetimefestival(jsonTravel.getString("usetimefestival"))
                .agelimit(jsonTravel.getString("agelimit"))
                .playtime(jsonTravel.getString("playtime"))
                .spendtimefestival(jsonTravel.getString("spendtimefestival"))
                .eventhomepage(jsonTravel.getString("eventhomepage"))
                .bookingplace(jsonTravel.getString("eventplace"))
                .build();
    }
}
