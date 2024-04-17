package java.com.bit.nc4_final_project.repository.review;

import com.bit.nc4_final_project.entity.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ReviewRepositoryCustom {
    Page<Review> searchAll(Pageable pageable, String searchCondition, String searchKeyword, String sort);
    Page<Review> searchMyReviewList(String userId, Pageable pageable);

}
