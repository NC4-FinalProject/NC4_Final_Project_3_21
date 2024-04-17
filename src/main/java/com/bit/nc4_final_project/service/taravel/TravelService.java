package com.bit.nc4_final_project.service.taravel;

import com.bit.nc4_final_project.document.travel.AreaCode;
import com.bit.nc4_final_project.document.travel.SigunguCode;
import com.bit.nc4_final_project.dto.travel.BookmarkDTO;
import com.bit.nc4_final_project.dto.travel.TravelDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.io.UnsupportedEncodingException;
import java.util.List;

public interface TravelService {
    void save();

    List<AreaCode> getAreaCodes();

    List<SigunguCode> getSigunguCodes(String areaCode);

    void saveAreaCodes() throws UnsupportedEncodingException;

    AreaCode getAreaCode(String areaCode);

    String getSigunguName(AreaCode areaCode, String sigunguCode);

    TravelDTO getTravelDTO(String id, Integer userSeq);

    void removeDuplicateContentIds();

    List<TravelDTO> searchAllCarousel(String searchArea, String searchSigungu, String searchKeyword, String sort);

    Page<TravelDTO> searchAllPageable(Pageable pageable, String searchArea, String searchSigungu, String searchKeyword, String sort);

    List<TravelDTO> findNearbyTravels(double minMapx, double maxMapx, double minMapy, double maxMapy);

    void regBookmark(String id, Integer userSeq);

    void cancelBookmark(String id, Integer userSeq);

    Page<BookmarkDTO> getMyBookmarkList(Integer userSeq, Pageable pageable);

    void updateViewCnt(String id);
}
