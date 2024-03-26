package com.bit.nc4_final_project.service.taravel;

import com.bit.nc4_final_project.dto.travel.TravelDTO;

public interface TravelService {
    void save();

    TravelDTO getTravelDTO(String travelId);
}
