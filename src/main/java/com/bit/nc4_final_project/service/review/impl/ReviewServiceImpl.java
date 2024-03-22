package com.bit.nc4_final_project.service.review.impl;

import com.bit.nc4_final_project.repository.review.ReviewRepository;
import com.bit.nc4_final_project.service.review.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {
    private final ReviewRepository reviewRepository;
}
