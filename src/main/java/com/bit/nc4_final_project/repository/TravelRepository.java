package com.bit.nc4_final_project.repository;

import com.bit.nc4_final_project.entity.Travel;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TravelRepository extends MongoRepository<Travel, String> {
}
