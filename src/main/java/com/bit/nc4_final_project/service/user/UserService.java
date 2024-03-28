package com.bit.nc4_final_project.service.user;

import com.bit.nc4_final_project.dto.user.UserDTO;


public interface UserService {
    UserDTO join(UserDTO userDTO);

    UserDTO getUserDTO(Integer userSeq);

    UserDTO signup(UserDTO userDTO);

    UserDTO signin(UserDTO userDTO);

    long idCheck(UserDTO userDTO);

    boolean isIdAvailable(String id);

    boolean isNicknameAvailable(String nickname);
}
