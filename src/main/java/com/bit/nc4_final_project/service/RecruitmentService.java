package com.bit.nc4_final_project.service;

import com.bit.nc4_final_project.dto.RecruitmentDTO;
import org.springframework.data.domain.Page;

public interface RecruitmentService {
    Page<RecruitmentDTO> searchAll(String searchCondition, String searchKeyword);
}
