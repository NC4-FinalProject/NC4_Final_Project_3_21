package com.bit.nc4_final_project.repository;

import com.bit.nc4_final_project.entity.Review;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;

@Transactional
public interface ReviewRepository extends JpaRepository<Review, Integer> {

}
