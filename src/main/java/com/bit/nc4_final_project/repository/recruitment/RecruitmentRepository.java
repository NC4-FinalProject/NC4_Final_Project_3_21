package com.bit.nc4_final_project.repository.recruitment;

import com.bit.nc4_final_project.entity.Recruitment;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

@Transactional
public interface RecruitmentRepository extends JpaRepository<Recruitment, Integer> {
    Page<Recruitment> searchAll(String searchCondition, String searchKeyword);
}
