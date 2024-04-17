package java.com.bit.nc4_final_project.repository.recruitment.impl;


import com.bit.nc4_final_project.entity.Recruitment;
import com.bit.nc4_final_project.repository.recruitment.RecruitmentRepositoryCustom;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.jpa.impl.JPAQueryFactory;
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
    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public Page<Recruitment> searchAll(Pageable pageable, String searchCondition, String searchKeyword, String sort) {
        BooleanBuilder searchPredicate = getSearch(searchCondition, searchKeyword);
        OrderSpecifier<?> orderSpecifier = getOrderSpecifier(sort);

        List<Recruitment> recruitmentList = jpaQueryFactory
                .selectFrom(recruitment)
                .where(searchPredicate)
                .orderBy(orderSpecifier)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        long totalCnt = jpaQueryFactory
                .select(recruitment.count())
                .where(searchPredicate)
                .from(recruitment)
                .fetchOne();



        return new PageImpl<>(recruitmentList, pageable, totalCnt);
    }

    @Override
    public Page<Recruitment> searchMyRecruitmentList(String userId, Pageable pageable) {
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        booleanBuilder.and(recruitment.user.userId.eq(userId));

        List<Recruitment> recruitments = jpaQueryFactory
                .selectFrom(recruitment)
                .where(booleanBuilder)
                .orderBy(recruitment.regDate.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        long totalCnt = jpaQueryFactory
                .select(recruitment.count())
                .from(recruitment)
                .where(booleanBuilder)
                .fetchOne();

        return new PageImpl<>(recruitments, pageable, totalCnt);
    }


    private BooleanBuilder getSearch(String searchCondition, String searchKeyword) {
        BooleanBuilder booleanBuilder = new BooleanBuilder();

        if (searchKeyword != null && !searchKeyword.isEmpty()) {
            if ("all".equalsIgnoreCase(searchCondition)) {
                booleanBuilder.or(recruitment.title.containsIgnoreCase(searchKeyword))
                        .or(recruitment.content.containsIgnoreCase(searchKeyword))
                        .or(recruitment.writer.containsIgnoreCase(searchKeyword));
            } else if ("title".equalsIgnoreCase(searchCondition)) {
                booleanBuilder.and(recruitment.title.containsIgnoreCase(searchKeyword));
            } else if ("content".equalsIgnoreCase(searchCondition)) {
                booleanBuilder.and(recruitment.content.containsIgnoreCase(searchKeyword));
            } else if ("writer".equalsIgnoreCase(searchCondition)) {
                booleanBuilder.and(recruitment.writer.containsIgnoreCase(searchKeyword));
            }
        }

        return booleanBuilder;
    }

    private OrderSpecifier<?> getOrderSpecifier(String sort) {
        if (sort == null) {
            return recruitment.regDate.desc();
        }
        switch (sort) {
            case "latest":
                return recruitment.regDate.desc();
            case "oldest":
                return recruitment.regDate.asc();
//            case "member_high":
//                return review.rating.desc();
//            case "member_low":
//                return review.rating.asc();
            default:
                return recruitment.regDate.desc();
        }
    }

}
