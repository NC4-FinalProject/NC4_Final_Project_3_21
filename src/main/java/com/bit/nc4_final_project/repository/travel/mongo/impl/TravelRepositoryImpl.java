package com.bit.nc4_final_project.repository.travel.mongo.impl;

import com.bit.nc4_final_project.document.travel.Travel;
import com.bit.nc4_final_project.repository.travel.mongo.TravelRepositoryCustom;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.LimitOperation;
import org.springframework.data.mongodb.core.aggregation.MatchOperation;
import org.springframework.data.mongodb.core.aggregation.SortOperation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.List;

@Slf4j
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
        SortOperation sortOperation = null;

        if ("alphabetical".equals(sort)) {
            sortOperation = Aggregation.sort(Sort.by("title").ascending());
        } else if ("view".equals(sort)) {
            sortOperation = Aggregation.sort(Sort.by("viewCnt").descending());
        }

        Aggregation aggregation;
        if ("random".equals(sort)) {
            aggregation = Aggregation.newAggregation(matchOperation, Aggregation.sample(12));
        } else if ("bookmark".equals(sort)) {
            aggregation = Aggregation.newAggregation(matchOperation);
        } else {
            LimitOperation limitOperation = Aggregation.limit(12);
            aggregation = Aggregation.newAggregation(matchOperation, sortOperation, limitOperation);
        }

        return mongoTemplate.aggregate(aggregation, "travel", Travel.class).getMappedResults();
    }
}