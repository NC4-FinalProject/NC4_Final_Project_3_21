package com.bit.nc4_final_project.repository.travel.mongo;

import com.bit.nc4_final_project.document.PetTravel;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PetTravelRepository extends MongoRepository<PetTravel, String> {
    PetTravel findByContentid(String contentid);
}
