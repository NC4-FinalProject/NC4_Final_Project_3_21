package com.bit.nc4_final_project.service.taravel;

import com.bit.nc4_final_project.dto.travel.TravelDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TravelService {
    void save();

    TravelDTO getTravelDTO(String travelId);

    void removeDuplicateContentIds();

    Page<TravelDTO> searchAll(Pageable pageable, List<String> searchArea, String searchKeyword);

}
