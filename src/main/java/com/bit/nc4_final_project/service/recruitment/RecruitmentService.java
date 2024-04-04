package com.bit.nc4_final_project.service.recruitment;

import com.bit.nc4_final_project.dto.recruitment.RecruitmentDTO;
import com.bit.nc4_final_project.entity.CustomUserDetails;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface RecruitmentService {
     Page<RecruitmentDTO> searchAll(Pageable pageable, String searchCondition, String searchKeyword, String sort);
     void post(RecruitmentDTO recruitmentDTO, CustomUserDetails customUserDetails);

     RecruitmentDTO findById(int seq);

     void modify(RecruitmentDTO recruitmentDTO, CustomUserDetails customUserDetails);

     void deleteById(int seq);

     Page<RecruitmentDTO> getMyRecruitmentList(String userId, Pageable pageable);
}
