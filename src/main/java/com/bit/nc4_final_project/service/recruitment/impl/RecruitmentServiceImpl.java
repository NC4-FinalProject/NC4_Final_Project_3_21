package com.bit.nc4_final_project.service.recruitment.impl;

import com.bit.nc4_final_project.dto.recruitment.RecruitmentDTO;
import com.bit.nc4_final_project.entity.Recruitment;
import com.bit.nc4_final_project.repository.recruitment.RecruitmentRepository;
import com.bit.nc4_final_project.service.recruitment.RecruitmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;


@Service
@RequiredArgsConstructor
public class RecruitmentServiceImpl implements RecruitmentService {
    private final RecruitmentRepository recruitmentRepository;

     @Override
     public Page<RecruitmentDTO> searchAll(Pageable pageable,String searchCondition, String searchKeyword) {
         Page<Recruitment> recruitmentPage = recruitmentRepository.searchAll(pageable, searchCondition, searchKeyword);

         return recruitmentPage.map(recruitment -> recruitment.toDTO());
     }

    @Override
    public void post(RecruitmentDTO recruitmentDTO) {
        recruitmentDTO.setRegDate(LocalDateTime.now());

        Recruitment recruitment = recruitmentDTO.toEntity();

        recruitmentRepository.save(recruitment);
    }

    @Override
    public RecruitmentDTO findById(int seq) {
         Recruitment recruitment = recruitmentRepository.findById(seq).orElseThrow();
         return recruitment.toDTO();
    }

    @Override
    public void modify(RecruitmentDTO recruitmentDTO) {
        Recruitment recruitment = recruitmentDTO.toEntity();

        recruitmentRepository.save(recruitment);
    }

    @Override
    public void deleteById(int seq) {
        recruitmentRepository.deleteById(seq);
    }
}
