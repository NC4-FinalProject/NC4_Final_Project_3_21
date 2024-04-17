package java.com.bit.nc4_final_project.repository.review.impl;

import com.bit.nc4_final_project.entity.Review;
import com.bit.nc4_final_project.repository.review.ReviewRepositoryCustom;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.bit.nc4_final_project.entity.QReview.review;

@Repository
@RequiredArgsConstructor
public class ReviewRepositoryCustomImpl implements ReviewRepositoryCustom {
    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public Page<Review> searchAll(Pageable pageable, String searchCondition, String searchKeyword, String sort) {
        BooleanBuilder searchPredicate = getSearch(searchCondition, searchKeyword);
        OrderSpecifier<?> orderSpecifier = getOrderSpecifier(sort);

        List<Review> reviewList = jpaQueryFactory
                .selectFrom(review)
                .where(searchPredicate)
                .orderBy(orderSpecifier)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        long totalCnt = jpaQueryFactory
                .select(review.count())
                .where(searchPredicate)
                .from(review)
                .fetchOne();



        return new PageImpl<>(reviewList, pageable, totalCnt);
    }

    @Override
    public Page<Review> searchMyReviewList(String userId, Pageable pageable) {
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        booleanBuilder.and(review.user.userId.eq(userId));

        List<Review> reviews = jpaQueryFactory
                .selectFrom(review)
                .where(booleanBuilder)
                .orderBy(review.regDate.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        long totalCnt = jpaQueryFactory
                .select(review.count())
                .from(review)
                .where(booleanBuilder)
                .fetchOne();

        return new PageImpl<>(reviews, pageable, totalCnt);
    }


    private BooleanBuilder getSearch(String searchCondition, String searchKeyword) {
        BooleanBuilder booleanBuilder = new BooleanBuilder();

        if (searchKeyword != null && !searchKeyword.isEmpty()) {
            if ("all".equalsIgnoreCase(searchCondition)) {
                booleanBuilder.or(review.title.containsIgnoreCase(searchKeyword))
                        .or(review.content.containsIgnoreCase(searchKeyword))
                        .or(review.writer.containsIgnoreCase(searchKeyword));
            } else if ("title".equalsIgnoreCase(searchCondition)) {
                booleanBuilder.and(review.title.containsIgnoreCase(searchKeyword));
            } else if ("content".equalsIgnoreCase(searchCondition)) {
                booleanBuilder.and(review.content.containsIgnoreCase(searchKeyword));
            } else if ("writer".equalsIgnoreCase(searchCondition)) {
                booleanBuilder.and(review.writer.containsIgnoreCase(searchKeyword));
            }
        }

        return booleanBuilder;
    }

    private OrderSpecifier<?> getOrderSpecifier(String sort) {
        if (sort == null) {
            return review.regDate.desc();
        }
        switch (sort) {
            case "latest":
                return review.regDate.desc();
            case "oldest":
                return review.regDate.asc();
            case "rating_high":
                return review.rating.desc();
            case "rating_low":
                return review.rating.asc();
            default:
                return review.regDate.desc();
        }
    }

}
