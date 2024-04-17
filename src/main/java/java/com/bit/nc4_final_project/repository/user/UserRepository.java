package java.com.bit.nc4_final_project.repository.user;

import com.bit.nc4_final_project.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {

    User findBySeq(Integer seq);

//    Optional<User> findById(String id);

    long countByUserId(String userid);

    boolean existsByUserName(String username);

    boolean existsByUserId(String userid);

    Optional<User> findByUserId(String username);

}

