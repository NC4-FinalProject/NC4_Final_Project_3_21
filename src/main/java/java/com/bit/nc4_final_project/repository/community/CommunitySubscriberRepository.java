package java.com.bit.nc4_final_project.repository.community;

import com.bit.nc4_final_project.entity.community.CommunitySubscriber;
import com.bit.nc4_final_project.entity.community.CommunitySubscriberId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommunitySubscriberRepository extends JpaRepository<CommunitySubscriber, CommunitySubscriberId> {
}
