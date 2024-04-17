package java.com.bit.nc4_final_project.repository.review;

import com.bit.nc4_final_project.entity.Review;
import com.bit.nc4_final_project.repository.review.ReviewRepositoryCustom;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;

@Transactional
public interface ReviewRepository extends JpaRepository<Review, Integer>, ReviewRepositoryCustom {

}
