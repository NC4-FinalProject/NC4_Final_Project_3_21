package com.bit.nc4_final_project.repository.recruitment.impl;


import com.bit.nc4_final_project.entity.Recruitment;
import com.bit.nc4_final_project.repository.recruitment.RecruitmentRepositoryCustom;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.bit.nc4_final_project.entity.QRecruitment.recruitment;

@Repository
@RequiredArgsConstructor
public class RecruitmentRepositoryCustomImpl implements RecruitmentRepositoryCustom {
    private final EntityManager em;
    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public Page<Recruitment> searchAll(Pageable pageable, String searchCondition, String searchKeyword) {
        List<Recruitment> recruitmentList = jpaQueryFactory
                .selectFrom(recruitment)
                .where(getSearch(searchCondition, searchKeyword))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        long totalCnt = jpaQueryFactory
                .select(recruitment.count())
                .where(getSearch(searchCondition, searchKeyword))
                .from(recruitment)
                .fetchOne();

        return new PageImpl<>(recruitmentList, pageable, totalCnt);
    }

    public BooleanBuilder getSearch(String searchCondition, String searchKeyword) {
        BooleanBuilder booleanBuilder = new BooleanBuilder();

        if(searchKeyword == null || searchKeyword.isEmpty()) {
            return null;
        }

        if(searchCondition.equalsIgnoreCase("all")) {
            booleanBuilder.or(recruitment.title.containsIgnoreCase(searchKeyword));
            booleanBuilder.or(recruitment.content.containsIgnoreCase(searchKeyword));
            booleanBuilder.or(recruitment.writer.containsIgnoreCase(searchKeyword));
        } else if(searchCondition.equalsIgnoreCase("title")) {
            booleanBuilder.or(recruitment.title.containsIgnoreCase(searchKeyword));
        } else if(searchCondition.equalsIgnoreCase("content")) {
            booleanBuilder.or(recruitment.content.containsIgnoreCase(searchKeyword));
        } else if(searchCondition.equalsIgnoreCase("writer")) {
            booleanBuilder.or(recruitment.writer.containsIgnoreCase(searchKeyword));
        }

        return booleanBuilder;
    }
}
