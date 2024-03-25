package com.bit.nc4_final_project.repository.impl;


import com.bit.nc4_final_project.repository.recruitment.RecruitmentRepositoryCustom;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class RecruitmentRepositoryCustomImpl implements RecruitmentRepositoryCustom {
    private final EntityManager em;
    private final JPAQueryFactory jpaQueryFactory;

    // @Override
    // public Page<Recruitment> searchAll(String searchCondition, String searchKeyword) {
    //     return null;
    // }
}
