package java.com.bit.nc4_final_project.repository.travel.mongo;

import com.bit.nc4_final_project.document.travel.Travel;
import com.bit.nc4_final_project.repository.travel.mongo.TravelRepositoryCustom;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface TravelRepository extends MongoRepository<Travel, String>, TravelRepositoryCustom {
    @Aggregation(pipeline = {
            "{$match: { $expr: { $and: [ { $gt: [ { $toDouble: \"$mapx\" }, ?0 ] }, { $lt: [ { $toDouble: \"$mapx\" }, ?1 ] }, { $gt: [ { $toDouble: \"$mapy\" }, ?2 ] }, { $lt: [ { $toDouble: \"$mapy\" }, ?3 ] } ] } } }"
    })
    List<Travel> findNearbyTravels(double minMapx, double maxMapx, double minMapy, double maxMapy);
}
