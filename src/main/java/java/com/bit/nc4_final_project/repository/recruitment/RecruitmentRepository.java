package java.com.bit.nc4_final_project.repository.recruitment;

import com.bit.nc4_final_project.entity.Recruitment;
import com.bit.nc4_final_project.repository.recruitment.RecruitmentRepositoryCustom;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;

@Transactional
public interface RecruitmentRepository extends JpaRepository<Recruitment, Integer>, RecruitmentRepositoryCustom {

}
