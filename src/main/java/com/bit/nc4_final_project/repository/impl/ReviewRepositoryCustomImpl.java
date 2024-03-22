package com.bit.nc4_final_project.repository.impl;

import com.bit.nc4_final_project.repository.review.ReviewRepositoryCustom;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ReviewRepositoryCustomImpl implements ReviewRepositoryCustom {
    private final EntityManager em;
    private final JPAQueryFactory jpaQueryFactory;
}
