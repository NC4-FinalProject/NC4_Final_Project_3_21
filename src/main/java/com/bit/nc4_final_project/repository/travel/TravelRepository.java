package com.bit.nc4_final_project.repository.travel;

import com.bit.nc4_final_project.entity.travel.Travel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface TravelRepository extends MongoRepository<Travel, String> {
    @Query("{$or:[{'area': {$regex : ?0, $options: 'i'}}, {'keyword': {$regex : ?1, $options: 'i'}}]}")
    Page<Travel> findByAreaOrKeyword(String area, String keyword, Pageable pageable);
}
