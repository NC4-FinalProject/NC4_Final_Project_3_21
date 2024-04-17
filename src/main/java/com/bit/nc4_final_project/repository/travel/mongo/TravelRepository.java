package com.bit.nc4_final_project.repository.travel.mongo;

import com.bit.nc4_final_project.document.travel.Travel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface TravelRepository extends MongoRepository<Travel, String>, TravelRepositoryCustom {
    @Aggregation(pipeline = {
            "{$match: { $expr: { $and: [ { $gt: [ { $toDouble: \"$mapx\" }, ?0 ] }, { $lt: [ { $toDouble: \"$mapx\" }, ?1 ] }, { $gt: [ { $toDouble: \"$mapy\" }, ?2 ] }, { $lt: [ { $toDouble: \"$mapy\" }, ?3 ] } ] } } }"
    })
    List<Travel> findNearbyTravels(double minMapx, double maxMapx, double minMapy, double maxMapy);


    @Query(value = "{ $and: [ { $cond: { if: { $ne: [ ?0, '' ] }, then: { area: ?0 }, else: {} } }, { $cond: { if: { $ne: [ ?1, '' ] }, then: { sigungu: ?1 }, else: {} } }, { $cond: { if: { $ne: [ ?2, '' ] }, then: { title: { $regex: ?2, $options: 'i' } }, else: {} } } ] }",
            sort = "{?#{#sort.equals('alphabetical') ? {title: 1} : {viewCnt: -1}}}")
    Page<Travel> findAllPagination(String area, String sigungu, String keyword, String sort, Pageable pageable);
}
