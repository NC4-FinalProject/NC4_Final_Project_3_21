package com.bit.nc4_final_project.service.review;

import com.bit.nc4_final_project.dto.review.ReviewDTO;
import com.bit.nc4_final_project.entity.CustomUserDetails;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ReviewService {
    Page<ReviewDTO> searchAll(Pageable pageable, String searchCondition, String searchKeyword, String sort, Integer userSeq);

    void post(ReviewDTO reviewDTO, CustomUserDetails customUserDetails);

    ReviewDTO findById(int seq, Integer userSeq);

    void modify(ReviewDTO reviewDTO, CustomUserDetails customUserDetails);

    void deleteById(int seq);

    Page<ReviewDTO> getReviewsByTravelId(Pageable pageable, String travelId);

    List<ReviewDTO> getReviewsByTravelId(String travelId);

    List<ReviewDTO> getReviewsByRandom();

    Page<ReviewDTO> getMyReviewList(String userId, Pageable pageable, Integer userSeq);
}
