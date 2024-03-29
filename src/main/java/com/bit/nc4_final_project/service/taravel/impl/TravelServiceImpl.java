package com.bit.nc4_final_project.service.taravel.impl;

import com.bit.nc4_final_project.api.TourApiExplorer;
import com.bit.nc4_final_project.dto.travel.TravelDTO;
import com.bit.nc4_final_project.entity.travel.AreaCode;
import com.bit.nc4_final_project.entity.travel.SigunguCode;
import com.bit.nc4_final_project.entity.travel.Travel;
import com.bit.nc4_final_project.entity.travel.TravelDetail;
import com.bit.nc4_final_project.repository.travel.AreaCodeRepository;
import com.bit.nc4_final_project.repository.travel.TravelRepository;
import com.bit.nc4_final_project.service.taravel.TravelService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
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
    private final AreaCodeRepository areaCodeRepository;

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
    public List<AreaCode> getAreaCodes() {
        return areaCodeRepository.findAll();
    }

    @Override
    public List<SigunguCode> getSigunguCodes(String areaCode) {
        return areaCodeRepository.findAreaCodesByCode(areaCode).getSigunguCodes();
    }

    @Override
    public void saveAreaCodes() throws UnsupportedEncodingException {
        log.info("area code data save start");
        List<Object> areaCodes = tourApiExplorer.getAreaCodeList(null);

        List<AreaCode> areaCodesToSave = areaCodes.stream()
                .filter(obj -> obj instanceof AreaCode)
                .map(obj -> {
                    AreaCode areaCodeObj = (AreaCode) obj;
                    List<SigunguCode> sigunguCodes = null;
                    try {
                        sigunguCodes = tourApiExplorer.getAreaCodeList(areaCodeObj.getCode())
                                .stream()
                                .filter(sigunguCodeObj -> sigunguCodeObj instanceof SigunguCode)
                                .map(sigunguCodeObj -> (SigunguCode) sigunguCodeObj)
                                .collect(Collectors.toList());
                    } catch (UnsupportedEncodingException e) {
                        throw new RuntimeException(e);
                    }
                    areaCodeObj.setSigunguCodes(sigunguCodes);
                    return areaCodeObj;
                })
                .collect(Collectors.toList());

        if (!areaCodesToSave.isEmpty()) {
            areaCodeRepository.saveAll(areaCodesToSave);
            log.info("save");
        }

        log.info("end");
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
        List<Travel> allTravel = travelRepository.findAll();

        Map<String, List<Travel>> contentIdGroups = allTravel.stream()
                .collect(Collectors.groupingBy(Travel::getContentid));

        contentIdGroups.forEach((contentId, travels) -> {
            if (travels.size() > 1) {
                travelRepository.deleteAll(travels.subList(1, travels.size()));
            }
        });
    }

    @Override
    public Page<TravelDTO> searchAll(Pageable pageable, String searchArea, String searchSigungu, String searchKeyword, String sort) {
        Page<Travel> travelPage = travelRepository.findByAreaAndSigunguAndTitle(searchArea, searchSigungu, searchKeyword, sort, pageable);
        return travelPage.map(Travel::toDTO);
    }
}
