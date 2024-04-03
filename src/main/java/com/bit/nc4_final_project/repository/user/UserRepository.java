package com.bit.nc4_final_project.repository.user;

import com.bit.nc4_final_project.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {

    User findBySeq(Integer seq);

    Optional<User> findById(String id);


    long countById(String id);

    boolean existsByNickname(String nickname);

    boolean existsById(String id);
}

