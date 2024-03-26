package com.bit.nc4_final_project.repository.review;

import com.bit.nc4_final_project.entity.Review;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

@Transactional
public interface ReviewRepository extends JpaRepository<Review, Integer>, ReviewRepositoryCustom {

}
