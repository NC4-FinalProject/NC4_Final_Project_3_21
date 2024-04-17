package com.bit.nc4_final_project.factory.travel.impl;

import com.bit.nc4_final_project.document.travel.TravelDetail;
import com.bit.nc4_final_project.factory.travel.TravelDetailFactory;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;

public class TravelDetail28Factory implements TravelDetailFactory {
    @Override
    public TravelDetail createTravelDetail(String homepage, String overview, JSONObject jsonTravel) throws JSONException {

        return TravelDetail.builder()
                .homepage(homepage)
                .overview(overview)
                .openperiod(jsonTravel.getString("openperiod"))
                .restdateleports(jsonTravel.getString("restdateleports"))
                .accomcountleports(jsonTravel.getString("accomcountleports"))
                .usefeeleports(jsonTravel.getString("usefeeleports"))
                .usetimeleports(jsonTravel.getString("usetimeleports"))
                .expagerangeleports(jsonTravel.getString("expagerangeleports"))
                .build();
    }
}