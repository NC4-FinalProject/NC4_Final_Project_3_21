package com.bit.nc4_final_project.service.taravel.impl;

import com.bit.nc4_final_project.api.TourApiExplorer;
import com.bit.nc4_final_project.dto.travel.TravelDTO;
import com.bit.nc4_final_project.entity.travel.Travel;
import com.bit.nc4_final_project.entity.travel.TravelDetail;
import com.bit.nc4_final_project.repository.travel.TravelRepository;
import com.bit.nc4_final_project.service.taravel.TravelService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class TravelServiceImpl implements TravelService {
    private final TourApiExplorer tourApiExplorer;
    private final TravelRepository travelRepository;

    @Override
    public void save() {
        log.info("travel data save start");
        int totalCnt = tourApiExplorer.getTotalCnt();
        int totalPages = (int) Math.ceil((double) totalCnt / 199);
        log.info("totalPages : " + totalPages);

        for (int i = 20; i <= 21; i++) {
            List<Travel> travels = tourApiExplorer.getList(i, 199, totalCnt);
            log.info("getList: " + i);
            for (Travel travel : travels) {
                Optional<TravelDetail> detail = tourApiExplorer.getDetailCommon(travel.getContentid());
                detail.ifPresent(travel::setDetail);
            }

            travelRepository.saveAll(travels);
            log.info("save 199 pieces");
        }
        log.info("end");
        // for (int i = 1; i <= totalPages; i++) {
        //     List<Travel> travels = tourApiExplorer.getList(i, 200, totalCnt);
        //     log.info("getList");
        //     for (Travel travel : travels) {
        //         Optional<TravelDetail> detail = tourApiExplorer.getDetailCommon(travel.getContentid());
        //         detail.ifPresent(travel::setDetail);
        //     }
        //
        //     travelRepository.saveAll(travels);
        //     log.info("save 200 pieces");
        // }
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

    public void removeDuplicateContentIds() {
        // 모든 여행 정보 조회
        List<Travel> allTravel = travelRepository.findAll();

        // contentid를 기준으로 그룹화
        Map<String, List<Travel>> contentIdGroups = allTravel.stream()
                .collect(Collectors.groupingBy(Travel::getContentid));

        // 중복된 contentid를 가진 그룹에 대해서 하나만 남기고 나머지 삭제
        contentIdGroups.forEach((contentId, travels) -> {
            if (travels.size() > 1) {
                // 첫 번째 요소를 제외한 나머지 요소 삭제
                travelRepository.deleteAll(travels.subList(1, travels.size()));
            }
        });
    }

    @Override
    public Page<TravelDTO> searchAll(Pageable pageable, List<String> searchArea, String searchKeyword) {
        Page<Travel> travelPage = travelRepository.findByAreaOrKeyword(searchArea.get(0), searchKeyword, pageable);

        return travelPage.map(Travel::toDTO);
    }
}
