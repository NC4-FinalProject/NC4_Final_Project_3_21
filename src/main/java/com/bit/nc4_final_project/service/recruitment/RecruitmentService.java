package com.bit.nc4_final_project.service.recruitment;

import com.bit.nc4_final_project.dto.recruitment.RecruitmentDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface RecruitmentService {
     Page<RecruitmentDTO> searchAll(Pageable pageable, String searchCondition, String searchKeyword);

     void post(RecruitmentDTO recruitmentDTO);

     RecruitmentDTO findById(int seq);

     void modify(RecruitmentDTO recruitmentDTO);

     void deleteById(int seq);
}
