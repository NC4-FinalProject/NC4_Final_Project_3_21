package com.bit.nc4_final_project.repository.travel;

import com.bit.nc4_final_project.entity.travel.Travel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.MatchOperation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.List;

@Repository
class TravelRepositoryImpl implements TravelRepositoryCustom {

    private final MongoTemplate mongoTemplate;

    @Autowired
    public TravelRepositoryImpl(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public List<Travel> findAllCarousel(String area, String sigungu, String keyword, String sort) {
        Criteria criteria = new Criteria();
        if (StringUtils.hasText(area)) {
            criteria.and("areaCode").is(area);
        }
        if (StringUtils.hasText(sigungu)) {
            criteria.and("sigunguCode").is(sigungu);
        }
        if (StringUtils.hasText(keyword)) {
            criteria.and("title").regex(keyword, "i");
        }

        MatchOperation matchOperation = Aggregation.match(criteria);

        Aggregation aggregation = Aggregation.newAggregation(matchOperation);

        return mongoTemplate.aggregate(aggregation, "travel", Travel.class).getMappedResults();
    }
}