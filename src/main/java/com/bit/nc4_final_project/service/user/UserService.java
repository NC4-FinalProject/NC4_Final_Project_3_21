package com.bit.nc4_final_project.service.user;

import com.bit.nc4_final_project.dto.user.UserDTO;
import org.springframework.web.multipart.MultipartFile;


public interface UserService {
    UserDTO join(UserDTO userDTO);

    UserDTO getUserDTO(Integer userSeq);

    UserDTO signup(UserDTO userDTO);

    UserDTO signin(UserDTO userDTO);

    boolean isUserIdAvailable(String userid);

    boolean isUserNameAvailable(String username);

    void deleteProfileImage(String username);

    String uploadProfileImage(MultipartFile file, String userid);

    String updateProfileImage(MultipartFile file, String userid);

    UserDTO modifyUser(String userid, UserDTO userDTO);
}
