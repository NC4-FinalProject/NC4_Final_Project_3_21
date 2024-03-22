package com.bit.nc4_final_project.repository.recruitment;

import com.bit.nc4_final_project.entity.Recruitment;
import org.springframework.data.domain.Page;

public interface RecruitmentRepositoryCustom {
    Page<Recruitment> searchAll(String searchCondition, String searchKeyword);
}
