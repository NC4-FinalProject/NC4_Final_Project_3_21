package com.bit.nc4_final_project.repository.recruitment;

import com.bit.nc4_final_project.entity.Recruitment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface RecruitmentRepositoryCustom {
    Page<Recruitment> searchAll(Pageable pageable, String searchCondition, String searchKeyword, String sort);
    Page<Recruitment> searchMyRecruitmentList(String userId, Pageable pageable);
}
