package com.bit.nc4_final_project.repository.review;

import com.bit.nc4_final_project.entity.Review;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

@Transactional
public interface ReviewRepository extends JpaRepository<Review, Integer>, ReviewRepositoryCustom {
    Page<Review> findAllByTravelId(Pageable pageable, String travelId);

    List<Review> findTop4ByTravelId(String travelId);

    @Query(value = "SELECT r FROM Review r ORDER BY RAND() LIMIT 5")
    List<Review> findRandom5Reviews();
}
