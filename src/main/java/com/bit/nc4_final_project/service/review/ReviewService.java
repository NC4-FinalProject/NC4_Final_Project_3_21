package com.bit.nc4_final_project.service.review;

import com.bit.nc4_final_project.dto.review.ReviewDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ReviewService {
    Page<ReviewDTO> searchAll(Pageable pageable, String searchCondition, String searchKeyword);
    void post(ReviewDTO reviewDTO);

    ReviewDTO findById(int seq);

    void modify(ReviewDTO reviewDTO);

    void deleteById(int seq);
}
