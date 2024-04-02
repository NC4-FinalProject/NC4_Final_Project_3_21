package com.bit.nc4_final_project.repository.travel;

import com.bit.nc4_final_project.entity.travel.AreaCode;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface AreaCodeRepository extends MongoRepository<AreaCode, String> {
    @Query("{'code': ?0}")
    AreaCode findAreaCodesByCode(String areaCode);
}
