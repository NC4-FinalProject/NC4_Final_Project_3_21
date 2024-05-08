package com.bit.nc4_final_project.repository.travel.mongo;

import com.bit.nc4_final_project.document.travel.Travel;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface TravelRepository extends MongoRepository<Travel, String>, TravelRepositoryCustom {
    @Aggregation(pipeline = {
            "{$match: { $expr: { $and: [ { $gt: [ { $toDouble: \"$mapx\" }, ?0 ] }, { $lt: [ { $toDouble: \"$mapx\" }, ?1 ] }, { $gt: [ { $toDouble: \"$mapy\" }, ?2 ] }, { $lt: [ { $toDouble: \"$mapy\" }, ?3 ] } ] } } }"
    })
    List<Travel> findNearbyMap(double minMapx, double maxMapx, double minMapy, double maxMapy);

    @Aggregation(pipeline = {
            "{$match: { $expr: { $and: [ { $gt: [ { $toDouble: \"$mapx\" }, ?0 ] }, { $lt: [ { $toDouble: \"$mapx\" }, ?1 ] }, { $gt: [ { $toDouble: \"$mapy\" }, ?2 ] }, { $lt: [ { $toDouble: \"$mapy\" }, ?3 ] } ] } } }",
            "{$sample: { size:?4 }}"
    })
    List<Travel> findNearbyMapLimit(double minMapx, double maxMapx, double minMapy, double maxMapy, int num);

    @Aggregation(pipeline = {
            "{$sort: {viewCnt: -1}}",
            "{$limit: ?0}"
    })
    List<Travel> findByOrderByViewCntDesc(int num);
}
