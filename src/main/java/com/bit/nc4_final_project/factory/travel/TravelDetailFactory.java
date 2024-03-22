package com.bit.nc4_final_project.factory.travel;

import com.bit.nc4_final_project.entity.TravelDetail;

public interface TravelDetailFactory {
    TravelDetail createTravelDetail(TravelDetailInput input);
}
