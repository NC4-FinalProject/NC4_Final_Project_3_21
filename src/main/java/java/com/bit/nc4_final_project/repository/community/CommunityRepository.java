package java.com.bit.nc4_final_project.repository.community;

import com.bit.nc4_final_project.entity.community.Community;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommunityRepository extends JpaRepository<Community, Integer> {
    Community findBySeq(Integer seq);
}
