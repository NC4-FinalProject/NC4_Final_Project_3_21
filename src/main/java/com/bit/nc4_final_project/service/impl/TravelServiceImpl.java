package com.bit.nc4_final_project.service.impl;

import com.bit.nc4_final_project.api.TourApiExplorer;
import com.bit.nc4_final_project.repository.TravelRepository;
import com.bit.nc4_final_project.service.TravelService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TravelServiceImpl implements TravelService {
    private TourApiExplorer apiExplorer;
    private final TravelRepository travelRepository;

    @Override
    public void save() {

    }
}
