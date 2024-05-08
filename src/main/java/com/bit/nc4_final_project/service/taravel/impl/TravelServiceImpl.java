package com.bit.nc4_final_project.service.taravel.impl;

import com.bit.nc4_final_project.api.TourApiExplorer;
import com.bit.nc4_final_project.document.travel.*;
import com.bit.nc4_final_project.dto.travel.BookmarkDTO;
import com.bit.nc4_final_project.dto.travel.TravelDTO;
import com.bit.nc4_final_project.entity.travel.Bookmark;
import com.bit.nc4_final_project.repository.travel.BookmarkRepository;
import com.bit.nc4_final_project.repository.travel.mongo.AreaCodeRepository;
import com.bit.nc4_final_project.repository.travel.mongo.PetTravelRepository;
import com.bit.nc4_final_project.repository.travel.mongo.TravelRepository;
import com.bit.nc4_final_project.repository.user.UserRepository;
import com.bit.nc4_final_project.service.taravel.TravelService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.UnsupportedEncodingException;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class TravelServiceImpl implements TravelService {
    private final TourApiExplorer tourApiExplorer;
    private final AreaCodeRepository areaCodeRepository;
    private final TravelRepository travelRepository;
    private final PetTravelRepository petTravelRepository;
    private final BookmarkRepository bookmarkRepository;
    private final UserRepository userRepository;

    @Transactional
    @Override
    public void save() {
        log.info("travel data save start");
        int totalCnt = tourApiExplorer.getTotalCnt();
        int totalPages = (int) Math.ceil((double) totalCnt / 199);
        log.info("totalPages : " + totalPages);

        for (int i = 1; i <= 4; i++) {
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
    }

    @Override
    public List<AreaCode> getAreaCodes() {
        return areaCodeRepository.findAll();
    }

    @Override
    public List<SigunguCode> getSigunguCodes(String areaCode) {
        return areaCodeRepository.findAreaCodesByCode(areaCode).getSigunguCodes();
    }

    @Transactional
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
    public AreaCode getAreaCode(String areaCode) {
        return areaCodeRepository.findAreaCodesByCode(areaCode);
    }

    @Override
    public String getSigunguName(AreaCode areaCode, String sigunguCode) {
        List<SigunguCode> sigunguCodes = areaCode.getSigunguCodes();
        for (SigunguCode sigungu : sigunguCodes) {
            if (sigungu.getCode().equals(sigunguCode)) {
                return sigungu.getName();
            }
        }
        return null;
    }

    @Override
    public TravelDTO getTravelDTO(String id, Integer userSeq) {
        Travel travel = travelRepository.findById(id).orElse(null);

        if (travel == null) {
            return null;
        }

        TravelDTO travelDTO = createTravelDTO(travel);
        boolean isBookmarked = userSeq != null && bookmarkRepository.findByTravelIdAndUserSeq(id, userSeq) != null;
        travelDTO.setBookmark(isBookmarked);

        PetTravel petTravel = petTravelRepository.findByContentid(travel.getContentid());
        travelDTO.setPetTravel(petTravel != null ? petTravel.toDTO() : null);
        return travelDTO;
    }

    private TravelDTO createTravelDTO(Travel travel) {
        String areaName = "";
        String sigunguName = "";

        if (!travel.getAreaCode().isEmpty()) {
            AreaCode areaCode = getAreaCode(travel.getAreaCode());
            if (areaCode != null) {
                areaName = areaCode.getName();
                sigunguName = getSigunguName(areaCode, travel.getSigunguCode());
            }
        }

        int bookmarkCnt = bookmarkRepository.findTotalCntByTravelId(travel.getId());
        return travel.toDTO(bookmarkCnt, areaName, sigunguName);
    }

    @Transactional
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
    public List<TravelDTO> searchAllCarousel(String searchArea, String searchSigungu, String searchKeyword, String sort) {
        List<Travel> travels = travelRepository.findAllCarousel(searchArea, searchSigungu, searchKeyword, sort);

        if ("bookmark".equals(sort)) {
            List<String> travelsIds = travels.stream().map(Travel::getId).toList();
            List<String> orderTravelsIds = bookmarkRepository.findTop12BYTravelId(travelsIds);

            List<Travel> orderedTravels = new ArrayList<>(travels.stream()
                    .filter(travel -> orderTravelsIds.contains(travel.getId()))
                    .toList());

            if (orderedTravels.size() < 12) {
                List<Travel> remainingTravels = travels.stream()
                        .filter(travel -> !orderTravelsIds.contains(travel.getId()))
                        .collect(Collectors.toList());

                Collections.shuffle(remainingTravels);
                orderedTravels.addAll(remainingTravels.subList(0, Math.min(12 - orderedTravels.size(), remainingTravels.size())));
            }

            return orderedTravels.stream()
                    .map(this::createTravelDTO)
                    .collect(Collectors.toList());
        }

        return travels.stream()
                .map(this::createTravelDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<TravelDTO> findNearbyTravels(double userMapx, double userMapy, Integer resultNum) {
        double radius = 3.0 / 111.0;
        double minMapx = userMapx - radius;
        double maxMapx = userMapx + radius;
        double minMapy = userMapy - radius;
        double maxMapy = userMapy + radius;

        List<Travel> travels;
        if (resultNum == null) {
            travels = travelRepository.findNearbyMap(minMapx, maxMapx, minMapy, maxMapy);
        } else {
            travels = travelRepository.findNearbyMapLimit(minMapx, maxMapx, minMapy, maxMapy, resultNum);
        }

        if (travels == null) {
            log.warn("No nearby travels found.");
            return Collections.emptyList();
        }

        return travels.stream()
                .map(this::createTravelDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<TravelDTO> findByOrderByViewCnt(int resultNum) {
        List<Travel> travels = travelRepository.findByOrderByViewCntDesc(resultNum);

        return travels.stream()
                .map(this::createTravelDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void regBookmark(String id, Integer userSeq) {
        Bookmark bookmark = Bookmark.builder()
                .travelId(id)
                .userSeq(userSeq)
                .build();
        bookmarkRepository.save(bookmark);
    }

    @Transactional
    @Override
    public void cancelBookmark(String id, Integer userSeq) {
        Bookmark bookmark = bookmarkRepository.findByTravelIdAndUserSeq(id, userSeq);
        bookmarkRepository.delete(bookmark);
    }

    @Transactional
    @Override
    public Page<BookmarkDTO> getMyBookmarkList(Integer userSeq, Pageable pageable) {
        Page<Bookmark> bookmarks = bookmarkRepository.findAllByUserSeq(pageable, userSeq);
        return bookmarks.map(bookmark -> {
            TravelDTO travelDTO = getTravelDTO(bookmark.getTravelId(), userSeq);
            return bookmark.toDTO(travelDTO);
        });
    }

    @Transactional
    @Override
    public void updateViewCnt(String id) {
        Travel travel = travelRepository.findById(id).get();
        travel.setViewCnt(travel.getViewCnt() + 1);
        travelRepository.save(travel);
    }
}
