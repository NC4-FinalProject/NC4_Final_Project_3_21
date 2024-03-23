package com.bit.nc4_final_project.service.recruitment.impl;

import com.bit.nc4_final_project.repository.recruitment.RecruitmentRepository;
import com.bit.nc4_final_project.service.recruitment.RecruitmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class RecruitmentServiceImpl implements RecruitmentService {
    private final RecruitmentRepository recruitmentRepository;

    // @Override
    // public Page<RecruitmentDTO> searchAll(String searchCondition, String searchKeyword) {
    //     Page<Recruitment> recruitmentPage = recruitmentRepository.searchAll(searchCondition, searchKeyword);
    //
    //     return recruitmentPage.map(recruitment -> recruitment.toDTO());
    // }
}
