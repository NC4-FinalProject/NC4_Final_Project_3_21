package com.bit.nc4_final_project.service.user.impl;

import com.bit.nc4_final_project.dto.user.UserDTO;
import com.bit.nc4_final_project.entity.User;
import com.bit.nc4_final_project.repository.user.UserRepository;
import com.bit.nc4_final_project.service.user.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public UserDTO join(UserDTO userDTO) {
        return null;
    }

    @Override
    public UserDTO getUserDTO(Integer userSeq) {
        Optional<User> user = userRepository.findById(userSeq);
        if (user.isEmpty()) {
            log.warn("Travel with ID {} not found", userSeq);
            return null;
        }
        return user.get().toDTO();
    }
}
