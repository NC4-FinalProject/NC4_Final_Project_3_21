package com.bit.nc4_final_project.service.recruitment.impl;

import com.bit.nc4_final_project.dto.recruitment.RecruitmentDTO;
import com.bit.nc4_final_project.entity.CustomUserDetails;
import com.bit.nc4_final_project.entity.Recruitment;
import com.bit.nc4_final_project.entity.User;
import com.bit.nc4_final_project.repository.recruitment.RecruitmentRepository;
import com.bit.nc4_final_project.repository.user.UserRepository;
import com.bit.nc4_final_project.service.recruitment.RecruitmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;


@Service
@RequiredArgsConstructor
public class RecruitmentServiceImpl implements RecruitmentService {
    private final RecruitmentRepository recruitmentRepository;
    private final UserRepository userRepository;

    @Override
    public Page<RecruitmentDTO> searchAll(Pageable pageable, String searchCondition, String searchKeyword, String sort) {
        Page<Recruitment> recruitmentPage = recruitmentRepository.searchAll(pageable, searchCondition, searchKeyword, sort);

        return recruitmentPage.map(recruitment -> recruitment.toDTO());
    }

    @Override
    public void post(RecruitmentDTO recruitmentDTO, CustomUserDetails customUserDetails) {
        recruitmentDTO.setRegDate(LocalDateTime.now());

        User user = userRepository.findByUserId(customUserDetails.getUserId()).orElseThrow(() -> new UsernameNotFoundException("사용자를 찾을 수 없습니다. ID: " + customUserDetails.getUserId()));

        Recruitment recruitment = recruitmentDTO.toEntity();

        recruitment.setUser(user);

        recruitmentRepository.save(recruitment);
    }

    @Override
    public RecruitmentDTO findById(int seq) {
        Recruitment recruitment = recruitmentRepository.findById(seq).orElseThrow();

        return recruitment.toDTO();
    }

    @Override
    public void modify(RecruitmentDTO recruitmentDTO, CustomUserDetails customUserDetails) {
        recruitmentDTO.setRegDate(LocalDateTime.now());

        User user = userRepository.findByUserId(customUserDetails.getUserId()).orElseThrow(() -> new UsernameNotFoundException("사용자를 찾을 수 없습니다. ID: " + customUserDetails.getUserId()));

        Recruitment recruitment = recruitmentDTO.toEntity();

        recruitment.setUser(user);

        recruitmentRepository.save(recruitment);
    }

    @Override
    public void deleteById(int seq) {
        recruitmentRepository.deleteById(seq);
    }

    @Override
    public Page<RecruitmentDTO> getMyRecruitmentList(String userId, Pageable pageable) {

        Page<Recruitment> recruitmentPage = recruitmentRepository.searchMyRecruitmentList(userId, pageable);

        return recruitmentPage.map(recruitment -> recruitment.toDTO());
    }


}
