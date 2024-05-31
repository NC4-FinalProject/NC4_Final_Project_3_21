package com.bit.nc4_final_project.service.user.impl;


import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.bit.nc4_final_project.common.FileUtils;
import com.bit.nc4_final_project.dto.user.UserDTO;
import com.bit.nc4_final_project.entity.User;
import com.bit.nc4_final_project.entity.UserTag;
import com.bit.nc4_final_project.jwt.JwtTokenProvider;
import com.bit.nc4_final_project.repository.user.UserRepository;
import com.bit.nc4_final_project.service.user.UserService;
import io.micrometer.common.util.StringUtils;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final PasswordEncoder passwordEncoder;
    private final FileUtils fileUtils;

    @Override
    public UserDTO signup(UserDTO userDTO) {
        if (userRepository.existsByUserId(userDTO.getUserId())) {
            throw new RuntimeException("User already exists");
        }
        if (StringUtils.isBlank(userDTO.getUserId())) {
            throw new IllegalArgumentException("id is required");
        }
        if (StringUtils.isBlank(userDTO.getUserPw())) {
            throw new IllegalArgumentException("password is required");
        }
        if (StringUtils.isBlank(userDTO.getUserName())) {
            throw new IllegalArgumentException("name is required");
        }
        User user = userRepository.save(userDTO.toEntity());
        List<String> tags = userDTO.getTags();

        tags.forEach(tagContent -> {
            UserTag userTag = new UserTag();
            userTag.setContent(tagContent);
            user.addUserTag(userTag);
        });
        return user.toDTO();
    }

    @Override
    public UserDTO signin(UserDTO userDTO) {
        Optional<User> signInUser = userRepository.findByUserId(userDTO.getUserId());

        if(signInUser.isEmpty()) {
            throw new RuntimeException("not exist userid");
        }

        if(!passwordEncoder.matches(userDTO.getUserPw(), signInUser.get().getUserPw())) {
            throw new RuntimeException("wrong password");
        }

        UserDTO signinDTO = signInUser.get().toDTO();

        signinDTO.setLastLoginDate(LocalDateTime.now().toString());
        signinDTO.setToken(jwtTokenProvider.create(signInUser.get()));
        System.out.println(jwtTokenProvider.create(signInUser.get()));
        userRepository.save(signinDTO.toEntity());
//         System.out.println(jwtTokenProvider.create(signInUser.get()));
        userRepository.flush();

        return signinDTO;
    }

    public boolean isUserIdAvailable(String userid) {
        return !userRepository.existsByUserId(userid);
    }

    public boolean isUserNameAvailable(String username) {
        return !userRepository.existsByUserName(username);
    }

    @Override
    public void deleteProfileImage(String userId) {
        User user = userRepository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        String fileUrl = user.getProfileImageUrl();
        if (fileUrl != null) {
            String fileName = fileUrl.substring(fileUrl.lastIndexOf("/") + 1);
            fileUtils.getS3().deleteObject(fileUtils.getBucketName(), fileName);
            user.setProfileImageUrl(null);
            userRepository.save(user);
        }
    }

    @Override
    public String uploadProfileImage(MultipartFile file, String userId) {
        User user = userRepository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        String fileName = UUID.randomUUID().toString();
        try {
            PutObjectRequest request = new PutObjectRequest(fileUtils.getBucketName(), fileName, file.getInputStream(), new ObjectMetadata())
                    .withCannedAcl(CannedAccessControlList.PublicRead);
            fileUtils.getS3().putObject(request);
        } catch (IOException e) {
            throw new RuntimeException("Failed to upload file", e);
        }
        String fileUrl = "https://" + fileUtils.getBucketName() + ".s3." + "ap-northeast-2" + ".amazonaws.com/" + fileName;
        user.setProfileImageUrl(fileUrl);
            userRepository.save(user);
        return fileUrl;
    }

    @Override
    public String updateProfileImage(MultipartFile file, String userId) {
        User user = userRepository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        String existingFileUrl = user.getProfileImageUrl();
        if (existingFileUrl != null) {
            String existingFileName = existingFileUrl.substring(existingFileUrl.lastIndexOf("/") + 1);
            fileUtils.getS3().deleteObject(fileUtils.getBucketName(), existingFileName);
        }

        String newFileName = UUID.randomUUID().toString();
        try {
            PutObjectRequest request = new PutObjectRequest(fileUtils.getBucketName(), newFileName, file.getInputStream(), new ObjectMetadata())
                    .withCannedAcl(CannedAccessControlList.PublicRead);
            fileUtils.getS3().putObject(request);
        } catch (IOException e) {
            throw new RuntimeException("Failed to upload file", e);
        }
        String newFileUrl = "https://" + fileUtils.getBucketName() + ".s3." + "ap-northeast-2" + ".amazonaws.com/" + newFileName;

        user.setProfileImageUrl(newFileUrl);
        userRepository.save(user);

        return newFileUrl;
    }

    @Override
    public UserDTO modifyUser(String userId, UserDTO userDTO) {
        Optional<User> optionalUser = userRepository.findByUserId(userId);

        if (optionalUser.isEmpty()) {
            throw new RuntimeException("User not found");
        }

        User existingUser = optionalUser.get();

        if (userDTO.getUserPw() != null && !userDTO.getUserPw().isEmpty()) {
            String encodedPassword = passwordEncoder.encode(userDTO.getUserPw());
            existingUser.setUserPw(encodedPassword);
        }

        if (userDTO.getUserName() != null && !userDTO.getUserName().isEmpty()) {
            existingUser.setUserName(userDTO.getUserName());
        }

        if (userDTO.getUserTel() != null && !userDTO.getUserTel().isEmpty()) {
            existingUser.setUserTel(userDTO.getUserTel());
        }

        if (userDTO.getTags() != null) {
            List<UserTag> newTags = userDTO.getTags().stream()
                    .map(tag -> UserTag.builder().content(tag).user(existingUser).build())
                    .collect(Collectors.toList());
            existingUser.setTags(newTags);
        }


        if (userDTO.getAreaCode() != null && !userDTO.getAreaCode().isEmpty()) {
            existingUser.setAreaCode(userDTO.getAreaCode());
        }

        if (userDTO.getAreaName() != null && !userDTO.getAreaName().isEmpty()) {
            existingUser.setAreaName(userDTO.getAreaName());
        }

        if (userDTO.getSigunguCode() != null && !userDTO.getSigunguCode().isEmpty()) {
            existingUser.setSigunguCode(userDTO.getSigunguCode());
        }

        if (userDTO.getSigunguName() != null && !userDTO.getSigunguName().isEmpty()) {
            existingUser.setSigunguName(userDTO.getSigunguName());
        }
        User savedUser = userRepository.save(existingUser);
        UserDTO updatedUserDTO = savedUser.toDTO();

        return updatedUserDTO;
    }

    public UserDTO getUserInfo(String userId) {
        User user = userRepository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return user.toDTO();
    }


    @Override
    public UserDTO join(UserDTO userDTO) {
        return null;
    }

    @Override
    public UserDTO getUserDTO(Integer userSeq) {
        Optional<User> user = Optional.ofNullable(userRepository.findBySeq(userSeq));
        if (user.isEmpty()) {
            log.warn("Travel with ID {} not found", userSeq);
            return null;
        }
        return user.get().toDTO();
    }

    @Override
    public boolean checkPassword(String userId, String password) {
        User user = userRepository.findByUserId(userId)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with userId: " + userId));

        return passwordEncoder.matches(password, user.getUserPw());
    }

    @Override
    public void updatePassword(String userId, String newPassword) {
        User user = userRepository.findByUserId(userId)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with userId: " + userId));

        String encodedPassword = passwordEncoder.encode(newPassword);
        user.setUserPw(encodedPassword);
        userRepository.save(user);
    }
}
