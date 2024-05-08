package com.bit.nc4_final_project.service.review.impl;

import com.bit.nc4_final_project.dto.review.ReviewDTO;
import com.bit.nc4_final_project.entity.CustomUserDetails;
import com.bit.nc4_final_project.entity.Review;
import com.bit.nc4_final_project.entity.User;
import com.bit.nc4_final_project.repository.review.ReviewRepository;
import com.bit.nc4_final_project.repository.user.UserRepository;
import com.bit.nc4_final_project.service.review.ReviewService;
import com.bit.nc4_final_project.service.taravel.TravelService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {
    private final ReviewRepository reviewRepository;
    private final UserRepository userRepository;
    private final TravelService travelService;

    @Override
    public Page<ReviewDTO> searchAll(Pageable pageable, String searchCondition, String searchKeyword, String sort, Integer userSeq) {
        Page<Review> reviewPage = reviewRepository.searchAll(pageable, searchCondition, searchKeyword, sort);

        return reviewPage.map(review -> review.toDTO(travelService.getTravelDTO(review.getTravelId(), null)));
    }

    @Override
    public void post(ReviewDTO reviewDTO, CustomUserDetails customUserDetails) {
        reviewDTO.setRegDate(LocalDateTime.now());

        User user = userRepository.findByUserId(customUserDetails.getUserId()).orElseThrow(() -> new UsernameNotFoundException("사용자를 찾을 수 없습니다. ID: " + customUserDetails.getUserId()));

        Review review = reviewDTO.toEntity();
        review.setUser(user);
        review.setTravelId(review.getTravelId());

        reviewRepository.save(review);
    }

    @Override
    public ReviewDTO findById(int seq, Integer userSeq) {
        Review review = reviewRepository.findById(seq).orElseThrow();
        return review.toDTO(travelService.getTravelDTO(review.getTravelId(), userSeq));
    }

    @Override
    public void modify(ReviewDTO reviewDTO, CustomUserDetails customUserDetails) {
        reviewDTO.setRegDate(LocalDateTime.now());

        User user = userRepository.findByUserId(customUserDetails.getUserId()).orElseThrow(() -> new UsernameNotFoundException("사용자를 찾을 수 없습니다. ID: " + customUserDetails.getUserId()));

        Review review = reviewDTO.toEntity();

        review.setUser(user);

        reviewRepository.save(review);
    }

    @Override
    public void deleteById(int seq) {
        reviewRepository.deleteById(seq);
    }

    @Override
    public Page<ReviewDTO> getReviewsByTravelId(Pageable pageable, String travelId) {
        Page<Review> reviewPage = reviewRepository.findAllByTravelId(pageable, travelId);

        return reviewPage.map(review -> review.toDTO(travelService.getTravelDTO(review.getTravelId(), null)));
    }

    @Override
    public List<ReviewDTO> getReviewsByTravelId(String travelId) {
        List<Review> reviews = reviewRepository.findTop4ByTravelId(travelId);
        return reviews.stream()
                .map(review -> review.toDTO(travelService.getTravelDTO(review.getTravelId(), null)))
                .collect(Collectors.toList());
    }

    @Override
    public List<ReviewDTO> getReviewsByRandom() {
        List<Review> reviews = reviewRepository.findRandom5Reviews();
        return reviews.stream()
                .map(review -> review.toDTO(travelService.getTravelDTO(review.getTravelId(), null)))
                .collect(Collectors.toList());
    }

    @Override
    public Page<ReviewDTO> getMyReviewList(String userId, Pageable pageable, Integer userSeq) {

        Page<Review> reviewPage = reviewRepository.searchMyReviewList(userId, pageable);

        return reviewPage.map(review -> review.toDTO(travelService.getTravelDTO(review.getTravelId(), userSeq)));
    }


}
