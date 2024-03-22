package com.bit.nc4_final_project.service.impl;

import com.bit.nc4_final_project.repository.RecruitmentRepository;
import com.bit.nc4_final_project.repository.ReviewRepository;
import com.bit.nc4_final_project.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {
    private final ReviewRepository reviewRepository;
}
