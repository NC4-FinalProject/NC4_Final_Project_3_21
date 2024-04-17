package java.com.bit.nc4_final_project.service.review.impl;

import com.bit.nc4_final_project.dto.review.ReviewDTO;
import com.bit.nc4_final_project.entity.CustomUserDetails;
import com.bit.nc4_final_project.entity.Review;
import com.bit.nc4_final_project.entity.User;
import com.bit.nc4_final_project.repository.review.ReviewRepository;
import com.bit.nc4_final_project.repository.travel.mongo.TravelRepository;
import com.bit.nc4_final_project.repository.user.UserRepository;
import com.bit.nc4_final_project.service.review.ReviewService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Slf4j
@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {
    private final ReviewRepository reviewRepository;
    private final UserRepository userRepository;
    private final TravelRepository travelRepository;

    @Override
    public Page<ReviewDTO> searchAll(Pageable pageable, String searchCondition, String searchKeyword, String sort) {
        Page<Review> reviewPage = reviewRepository.searchAll(pageable, searchCondition, searchKeyword, sort);

        return reviewPage.map(review -> review.toDTO());
    }

    @Override
    public void post(ReviewDTO reviewDTO, CustomUserDetails customUserDetails) {
        reviewDTO.setRegDate(LocalDateTime.now());

        User user = userRepository.findByUserId(customUserDetails.getUserId()).orElseThrow(() -> new UsernameNotFoundException("사용자를 찾을 수 없습니다. ID: " + customUserDetails.getUserId()));

        Review review = reviewDTO.toEntity();

        review.setUser(user);

        reviewRepository.save(review);
    }

    @Override
    public ReviewDTO findById(int seq) {
        Review review = reviewRepository.findById(seq).orElseThrow();

        return review.toDTO();
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
    public Page<ReviewDTO> getMyReviewList(String userId, Pageable pageable) {

        Page<Review> reviewPage = reviewRepository.searchMyReviewList(userId, pageable);

        return reviewPage.map(review -> review.toDTO());
    }


}
