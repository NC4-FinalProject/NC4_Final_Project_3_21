package com.bit.nc4_final_project.repository.user.area;

import com.bit.nc4_final_project.document.user.AreaCode;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface UserAreaCodeRepository extends MongoRepository<AreaCode, String> {
    List<AreaCode> findByName(String name);
}
