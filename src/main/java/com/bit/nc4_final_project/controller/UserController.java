package com.bit.nc4_final_project.controller;

import com.bit.nc4_final_project.dto.ResponseDTO;
import com.bit.nc4_final_project.dto.user.UserDTO;
import com.bit.nc4_final_project.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

   @PostMapping("/sign-up")
    public ResponseEntity<?> signup(@RequestBody UserDTO userDTO) {

       System.out.println(userDTO);
       ResponseDTO<UserDTO> responseDTO = new ResponseDTO<>();

       try{
           userDTO.setActive(true);
           userDTO.setLastLoginDate(LocalDateTime.now().toString());
           userDTO.setRegDate(LocalDateTime.now().toString());
           userDTO.setRole("ROLE_USER");
//           userDTO.setPw(passwordEncoder.encode(userDTO.getPw()));

           System.out.println(userDTO);
           UserDTO signupUserDTO = userService.signup(userDTO);

           signupUserDTO.setPw("");

           responseDTO.setItem(signupUserDTO);
           responseDTO.setStatusCode(HttpStatus.OK.value());
           System.out.println(userDTO);
           return ResponseEntity.ok(responseDTO);
       } catch (Exception e) {
           System.out.println(e.getMessage());
           responseDTO.setErrorCode(100);
           responseDTO.setErrorMessage(e.getMessage());
           responseDTO.setStatusCode(HttpStatus.BAD_REQUEST.value());
           return ResponseEntity.badRequest().body(responseDTO);
       }
   }

   @PostMapping("/sign-in")
    public ResponseEntity<?> signin(@RequestBody UserDTO userDTO) {
       ResponseDTO<UserDTO> responseDTO = new ResponseDTO<>();
       try {
           UserDTO signinUserDTO = userService.signin(userDTO);

           signinUserDTO.setPw("");

           responseDTO.setItem(signinUserDTO);
           responseDTO.setStatusCode(HttpStatus.OK.value());
           return ResponseEntity.ok(responseDTO);
       } catch (Exception e) {
           if(e.getMessage().equalsIgnoreCase("not exist id")) {
               responseDTO.setErrorCode(200);
               responseDTO.setErrorMessage(e.getMessage());
           } else if (e.getMessage().equalsIgnoreCase("wrong pw")) {
               responseDTO.setErrorCode(201);
               responseDTO.setErrorMessage(e.getMessage());
           }

           responseDTO.setStatusCode(HttpStatus.BAD_REQUEST.value());
           return ResponseEntity.badRequest().body(responseDTO);
       }
   }

   @PostMapping("/id-check")
    public ResponseEntity<?> idCheck(@RequestBody UserDTO userDTO) {
       ResponseDTO<Map<String, String>> responseDTO = new ResponseDTO<>();

       try {
           long idCheck = userService.idCheck(userDTO);

           Map<String, String> returnMap = new HashMap<>();
           if(idCheck == 0) {
               returnMap.put("idCheckResult", "available id");
           } else {
               returnMap.put("idCheckResult", "invalid id");
           }

           responseDTO.setItem(returnMap);
           responseDTO.setStatusCode(HttpStatus.OK.value());

           return ResponseEntity.ok(responseDTO);
       } catch (Exception e) {
           responseDTO.setErrorMessage(e.getMessage());
           responseDTO.setErrorCode(101);
           responseDTO.setStatusCode(HttpStatus.BAD_REQUEST.value());
           return ResponseEntity.badRequest().body(responseDTO);
       }
   }


}
