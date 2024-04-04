package com.bit.nc4_final_project.repository.travel;

import com.bit.nc4_final_project.entity.travel.Travel;

import java.util.List;

public interface TravelRepositoryCustom {

    List<Travel> findAllCarousel(String area, String sigungu, String keyword, String sort);
}