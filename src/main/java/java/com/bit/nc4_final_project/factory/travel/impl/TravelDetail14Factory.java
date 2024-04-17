package java.com.bit.nc4_final_project.factory.travel.impl;

import com.bit.nc4_final_project.document.travel.TravelDetail;
import com.bit.nc4_final_project.factory.travel.TravelDetailFactory;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;

public class TravelDetail14Factory implements TravelDetailFactory {
    @Override
    public TravelDetail createTravelDetail(String homepage, String overview, JSONObject jsonTravel) throws JSONException {
        return TravelDetail.builder()
                .homepage(homepage)
                .overview(overview)
                .restdateculture(jsonTravel.getString("restdateculture"))
                .usefee(jsonTravel.getString("usefee"))
                .spendtime(jsonTravel.getString("spendtime"))
                .accomcountculture(jsonTravel.getString("accomcountculture"))
                .build();
    }
}
