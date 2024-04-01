package com.bit.nc4_final_project.service.review.impl;

import com.bit.nc4_final_project.dto.review.ReviewDTO;
import com.bit.nc4_final_project.entity.Review;
import com.bit.nc4_final_project.repository.review.ReviewRepository;
import com.bit.nc4_final_project.service.review.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {
    private final ReviewRepository reviewRepository;

    @Override
    public Page<ReviewDTO> searchAll(Pageable pageable, String searchCondition, String searchKeyword, String sort) {
        Page<Review> reviewPage = reviewRepository.searchAll(pageable, searchCondition, searchKeyword, sort);

        return reviewPage.map(review -> review.toDTO());
    }

    @Override
    public void post(ReviewDTO reviewDTO) {
        reviewDTO.setRegDate(LocalDateTime.now());

        Review review = reviewDTO.toEntity();

        reviewRepository.save(review);
    }

    @Override
    public ReviewDTO findById(int seq) {
        Review review = reviewRepository.findById(seq).orElseThrow();
        return review.toDTO();
    }

    @Override
    public void modify(ReviewDTO reviewDTO) {
        reviewDTO.setRegDate(LocalDateTime.now());

        Review review = reviewDTO.toEntity();

        reviewRepository.save(review);
    }

    @Override
    public void deleteById(int seq) {
        reviewRepository.deleteById(seq);
    }


}
