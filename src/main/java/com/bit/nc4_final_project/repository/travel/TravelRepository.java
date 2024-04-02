package com.bit.nc4_final_project.repository.travel;

import com.bit.nc4_final_project.entity.travel.Travel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface TravelRepository extends MongoRepository<Travel, String>, TravelRepositoryCustom {
    // @Query(value = "{ $and: [ { $cond: { if: { $ne: [ ?0, '' ] }, then: { areaCode: ?0 }, else: {} } }, { $cond: { if: { $ne: [ ?1, '' ] }, then: { sigunguCode: ?1 }, else: {} } }, { $cond: { if: { $ne: [ ?2, '' ] }, then: { title: { $regex: ?2, $options: 'i' } }, else: {} } } ] }",
    //         sort = "{?#{#sort.equals('alphabetical') ? {title: 1} : #sort.equals('view') ? {viewCnt: -1} : {}}}")
    // @Aggregation(pipeline = {
    //         "?#{#sort.equals('random') ? { $sample: { size: 12 } } : { $limit : 12 } }"
    // })
    // List<Travel> findAllCarousel(String area, String sigungu, String keyword, String sort);
    // @Aggregation(pipeline = {
    //         "{$match: { $expr: { $and: [ " +
    //                 "{$cond: { if: { $ne: [ :#{#area}, null ] }, then: { areaCode: :#{#area} }, else: {} }}, " +
    //                 "{$cond: { if: { $ne: [ :#{#sigungu}, null ] }, then: { sigunguCode: :#{#sigungu} }, else: {} }}, " +
    //                 "{$cond: { if: { $ne: [ :#{#keyword}, null ] }, then: { title: { $regex: :#{#keyword}, $options: 'i' } }, else: {} }}" +
    //                 "]}}}",
    //         "?#{#sort.equals('random') ? { $sample: { size: 12 } } : { $limit : 12 } }"
    // })
    // @Aggregation(pipeline = {
    //         "{$match: { $expr: { $and: [ " +
    //                 "{$cond: { if: { $ne: [ :#{#area}, null ] }, then: { areaCode: :#{#area} }, else: {} }}, " +
    //                 "{$cond: { if: { $ne: [ :#{#sigungu}, null ] }, then: { sigunguCode: :#{#sigungu} }, else: {} }}, " +
    //                 "{$cond: { if: { $ne: [ :#{#keyword}, null ] }, then: { title: { $regex: :#{#keyword}, $options: 'i' } }, else: {} }}" +
    //                 "]}}}",
    //         "{$addFields: { 'randomField': { $cond: { if: { $eq: [ :#{#sort}, 'random' ] }, then: { $rand: {} }, else: null } } }",
    //         "{$sort: { 'randomField': 1 } }",
    //         "{$limit: 12}",
    //         "{$project: { 'randomField': 0 }}"
    // })

    @org.springframework.data.mongodb.repository.Aggregation(pipeline = {
            "{$match: { $expr: { $and: [ { $gt: [ { $toDouble: \"$mapx\" }, ?0 ] }, { $lt: [ { $toDouble: \"$mapx\" }, ?1 ] }, { $gt: [ { $toDouble: \"$mapy\" }, ?2 ] }, { $lt: [ { $toDouble: \"$mapy\" }, ?3 ] } ] } } }"
    })
    List<Travel> findNearbyTravels(double minMapx, double maxMapx, double minMapy, double maxMapy);


    @Query(value = "{ $and: [ { $cond: { if: { $ne: [ ?0, '' ] }, then: { area: ?0 }, else: {} } }, { $cond: { if: { $ne: [ ?1, '' ] }, then: { sigungu: ?1 }, else: {} } }, { $cond: { if: { $ne: [ ?2, '' ] }, then: { title: { $regex: ?2, $options: 'i' } }, else: {} } } ] }",
            sort = "{?#{#sort.equals('alphabetical') ? {title: 1} : {viewCnt: -1}}}")
    Page<Travel> findAllPagination(String area, String sigungu, String keyword, String sort, Pageable pageable);
}
