package com.bit.nc4_final_project.controller;

import com.bit.nc4_final_project.dto.ResponseDTO;
import com.bit.nc4_final_project.dto.user.UserDTO;
import com.bit.nc4_final_project.jwt.JwtTokenProvider;
import com.bit.nc4_final_project.service.user.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    @PostMapping("/sign-up")
    public ResponseEntity<?> signup(@RequestBody UserDTO userDTO) {

//        System.out.println(userDTO);
        ResponseDTO<UserDTO> responseDTO = new ResponseDTO<>();


       try{
           userDTO.setActive(true);
           userDTO.setLastLoginDate(LocalDateTime.now().toString());
           userDTO.setRegDate(LocalDateTime.now().toString());
           userDTO.setRole("ROLE_USER");
          userDTO.setPw(passwordEncoder.encode(userDTO.getPw()));

            System.out.println(userDTO);
            UserDTO signupUserDTO = userService.signup(userDTO);

            signupUserDTO.setPw("");

            responseDTO.setItem(signupUserDTO);
            responseDTO.setStatusCode(HttpStatus.OK.value());
//            System.out.println(userDTO);
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
//            System.out.println(signinUserDTO.getToken());
//            signinUserDTO.setPw("");
//            log.info("===========token: {} ==========", signinUserDTO.getToken());
            responseDTO.setItem(signinUserDTO);
            responseDTO.setStatusCode(HttpStatus.OK.value());
            return ResponseEntity.ok(responseDTO);
        } catch (Exception e) {
            if (e.getMessage().equalsIgnoreCase("not exist id")) {
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

    @GetMapping("/sign-out")
    public ResponseEntity<?> signout() {
        ResponseDTO<Map<String, String>> responseDTO = new ResponseDTO<>();

        try{
            SecurityContext securityContext = SecurityContextHolder.getContext();
            securityContext.setAuthentication(null);
            SecurityContextHolder.setContext(securityContext);

            Map<String, String> msgMap = new HashMap<>();

            msgMap.put("signoutMsg", "signout success");

            responseDTO.setItem(msgMap);
            responseDTO.setStatusCode(HttpStatus.OK.value());

            return ResponseEntity.ok(responseDTO);
        } catch(Exception e) {
            responseDTO.setErrorMessage(e.getMessage());
            responseDTO.setErrorCode(202);
            responseDTO.setStatusCode(HttpStatus.BAD_REQUEST.value());
            return ResponseEntity.badRequest().body(responseDTO);
        }
    }

    @GetMapping("/check-id")
    public ResponseEntity<ResponseDTO<Map<String, Object>>> checkId(@RequestParam("id") String id) {
        ResponseDTO<Map<String, Object>> responseDTO = new ResponseDTO<>();

        try {
            boolean available = userService.isIdAvailable(id);
            Map<String, Object> response = new HashMap<>();
            response.put("available", available);

            responseDTO.setItem(response);
            responseDTO.setStatusCode(HttpStatus.OK.value());
            return ResponseEntity.ok(responseDTO);
        } catch (Exception e) {
            responseDTO.setErrorMessage(e.getMessage());
            responseDTO.setErrorCode(102);
            responseDTO.setStatusCode(HttpStatus.BAD_REQUEST.value());
            return ResponseEntity.badRequest().body(responseDTO);
        }
    }

    @GetMapping("/check-nickname")
    public ResponseEntity<ResponseDTO<Map<String, Object>>> checkNickname(@RequestParam("nickname") String nickname) {
        ResponseDTO<Map<String, Object>> responseDTO = new ResponseDTO<>();

        try {
            boolean available = userService.isNicknameAvailable(nickname);
            Map<String, Object> response = new HashMap<>();
            response.put("available", available);

            responseDTO.setItem(response);
            responseDTO.setStatusCode(HttpStatus.OK.value());
            return ResponseEntity.ok(responseDTO);
        } catch (Exception e) {
            responseDTO.setErrorMessage(e.getMessage());
            responseDTO.setErrorCode(103);
            responseDTO.setStatusCode(HttpStatus.BAD_REQUEST.value());
            return ResponseEntity.badRequest().body(responseDTO);
        }
    }


    @PostMapping("/upload")
    public ResponseEntity<String> uploadProfileImage(@RequestParam("file") MultipartFile file, @RequestHeader("Authorization") String token) {
        String id = jwtTokenProvider.validateAndGetUsername(token);
        String profileImageUrl = userService.uploadProfileImage(file, id);
        return ResponseEntity.ok(profileImageUrl);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Void> deleteProfileImage(@RequestHeader("Authorization") String token) {
        String id = jwtTokenProvider.validateAndGetUsername(token);
        userService.deleteProfileImage(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/update")
    public ResponseEntity<String> updateProfileImage(@RequestParam("file") MultipartFile file, @RequestHeader("Authorization") String token) {
        String id = jwtTokenProvider.validateAndGetUsername(token);
        String profileImageUrl = userService.updateProfileImage(file, id);
        return ResponseEntity.ok(profileImageUrl);
    }
}
