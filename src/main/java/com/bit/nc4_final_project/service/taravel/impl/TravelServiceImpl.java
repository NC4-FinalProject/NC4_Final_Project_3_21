package com.bit.nc4_final_project.service.taravel.impl;

import com.bit.nc4_final_project.api.TourApiExplorer;
import com.bit.nc4_final_project.dto.travel.TravelDTO;
import com.bit.nc4_final_project.entity.travel.Travel;
import com.bit.nc4_final_project.repository.travel.TravelRepository;
import com.bit.nc4_final_project.service.taravel.TravelService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class TravelServiceImpl implements TravelService {
    private TourApiExplorer tourApiExplorer;
    private final TravelRepository travelRepository;

    @Override
    public void save() {
        int totalCnt = tourApiExplorer.getTotalCnt();
        int totalPages = (int) Math.ceil((double) totalCnt / 200);
        System.out.println(">>>>>>>>>> total : " + totalCnt);

        for (int i = 1; i <= totalPages; i++) {
            List<Travel> travels = tourApiExplorer.getList(i, 200, totalCnt);

            for (Travel travel : travels) {
                travel.setDetail(tourApiExplorer.getDetailCommon(travel.getContentid()));
                System.out.println(travel.getContentid());
            }

            travelRepository.saveAll(travels);
            travels.clear();
        }
    }

    @Override
    public TravelDTO getTravelDTO(String travelId) {
        Optional<Travel> travel = travelRepository.findById(travelId);
        if (travel.isEmpty()) {
            log.warn("Travel with ID {} not found", travelId);
            return null;
        }
        return travel.get().toDTO();
    }
}
