package com.bit.nc4_final_project.repository.user;

import com.bit.nc4_final_project.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
}
