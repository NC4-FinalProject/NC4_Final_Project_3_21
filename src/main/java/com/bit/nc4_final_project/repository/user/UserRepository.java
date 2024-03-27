package com.bit.nc4_final_project.repository.user;

import com.bit.nc4_final_project.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {

    User findAllBySeq(Integer seq);

    Optional<User> findById(String nickname);

    long countById(String id);
}

