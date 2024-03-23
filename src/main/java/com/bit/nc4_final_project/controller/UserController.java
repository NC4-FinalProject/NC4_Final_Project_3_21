package com.bit.nc4_final_project.controller;

import com.bit.nc4_final_project.dto.ResponseDTO;
import com.bit.nc4_final_project.dto.user.UserDTO;
import com.bit.nc4_final_project.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import java.time.LocalDateTime;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

   @PostMapping("/sign-up")
    public ResponseEntity<?> signup(@RequestBody UserDTO userDTO) {
       ResponseDTO<UserDTO> responseDTO = new ResponseDTO<>();

       try{
           userDTO.setActive(true);
           userDTO.setLastLoginDate(LocalDateTime.now().toString());
           userDTO.setRegDate(LocalDateTime.now().toString());
           userDTO.setRole("ROLE_USER");
           userDTO.setPw(passwordEncoder.encode(userDTO.getPw()));

           UserDTO signupUserDTO = userService.signup(userDTO);

           signupUserDTO.setPw("");

           responseDTO.setItem(signupUserDTO);
           responseDTO.setStatusCode(HttpStatus.OK.value());

           return ResponseEntity.ok(responseDTO);
       } catch (Exception e) {
           responseDTO.setErrorCode(100);
           responseDTO.setErrorMessage(e.getMessage());
           responseDTO.setStatusCode(HttpStatus.BAD_REQUEST.value());
           return ResponseEntity.badRequest().body(responseDTO);
       }
   }

}
