package com.bit.nc4_final_project.service.user;

import com.bit.nc4_final_project.dto.user.UserDTO;


public interface UserService {


    UserDTO signup(UserDTO userDTO);

    UserDTO signin(UserDTO userDTO);

    long idCheck(UserDTO userDTO);
}
