package com.bit.nc4_final_project.service.user.impl;

import com.bit.nc4_final_project.dto.user.UserDTO;
import com.bit.nc4_final_project.entity.User;
import com.bit.nc4_final_project.entity.UserTag;
import com.bit.nc4_final_project.repository.user.UserRepository;
import com.bit.nc4_final_project.repository.user.UserTagRepository;
import com.bit.nc4_final_project.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserTagRepository userTagRepository;

    @Override
    public UserDTO signup(UserDTO userDTO) {
        User user = userRepository.save(userDTO.toEntity());

        List<UserTag> userTags = userDTO.getTags().stream()
                .map(tagDTO -> UserTag.builder()
                        .content(tagDTO.getContent())
                        .build())
                .collect(Collectors.toList());

        userTags.forEach(user::addUserTag);

        return user.toDTO();
    }
}
