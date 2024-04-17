package com.bit.nc4_final_project.factory.travel;

import com.bit.nc4_final_project.document.travel.TravelDetail;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;

public interface TravelDetailFactory {
    TravelDetail createTravelDetail(String homepage, String overview, JSONObject jsonTravel) throws JSONException;
}
