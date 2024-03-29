package com.bit.nc4_final_project.repository.travel;

import com.bit.nc4_final_project.entity.travel.Travel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface TravelRepository extends MongoRepository<Travel, String> {
    @Query(value = "{$and: [{area: ?0}, {sigungu: ?1, $exists: true}, {title: {$regex: ?2, $options: 'i', $exists: true}}]}",
            sort = "{?#{#sort.equals('alphabetical') ? {title: 1} : {viewCnt: -1}}}")
    Page<Travel> findByAreaAndSigunguAndTitle(String area, String sigungu, String keyword, String sort, Pageable pageable);
}
