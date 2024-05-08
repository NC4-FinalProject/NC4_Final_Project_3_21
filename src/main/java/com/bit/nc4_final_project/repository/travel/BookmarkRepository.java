package com.bit.nc4_final_project.repository.travel;

import com.bit.nc4_final_project.entity.travel.Bookmark;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookmarkRepository extends JpaRepository<Bookmark, Long> {
    @Query("select count(*) from Bookmark b where b.travelId = :travelId")
    Integer findTotalCntByTravelId(String travelId);

    Bookmark findByTravelIdAndUserSeq(String travelId, Integer userSeq);

    Page<Bookmark> findAllByUserSeq(Pageable pageable, Integer userSeq);

    @Query("select b.travelId from Bookmark b where b.travelId in :travelIds group by b.travelId order by count(*) desc limit 12")
    List<String> findTop12BYTravelId(List<String> travelIds);
}
