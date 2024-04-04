package com.bit.nc4_final_project.repository.travel;

import com.bit.nc4_final_project.entity.travel.Bookmark;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookmarkRepository extends JpaRepository<Bookmark, Integer> {

    Page<Bookmark> findAllByUserSeq(Integer userSeq, Pageable pageable);
}
