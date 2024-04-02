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
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

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
//      System.out.println(userDTO);

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
        Optional<User> signInUser = userRepository.findById(userDTO.getId());

        if (signInUser.isEmpty()) {
            throw new RuntimeException("not exist userid");
        }

        if (!passwordEncoder.matches(userDTO.getPw(), signInUser.get().getPw())) {
            throw new RuntimeException("wrong pw");
        }

        UserDTO signinDTO = signInUser.get().toDTO();

        signinDTO.setLastLoginDate(LocalDateTime.now().toString());
        signinDTO.setToken(jwtTokenProvider.create(signInUser.get()));

        userRepository.save(signinDTO.toEntity());
//        System.out.println(jwtTokenProvider.create(signinUser.get()));
        userRepository.flush();

        return signinDTO;
    }

    public boolean isIdAvailable(String id) {
        return !userRepository.existsById(id);
    }

    public boolean isNicknameAvailable(String nickname) {
        return !userRepository.existsByNickname(nickname);
    }

    @Override
    public void deleteProfileImage(String id) {
        User user = userRepository.findById(id)
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
    public String uploadProfileImage(MultipartFile file, String id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        String fileName = UUID.randomUUID().toString();
        try {
            PutObjectRequest request = new PutObjectRequest(fileUtils.getBucketName(), fileName, file.getInputStream(), new ObjectMetadata())
                    .withCannedAcl(CannedAccessControlList.PublicRead);
            fileUtils.getS3().putObject(request);
        } catch (IOException e) {
            throw new RuntimeException("Failed to upload file", e);
        }
        String fileUrl = fileUtils.getStorageUrl() + "/" + fileUtils.getBucketName() + "/" + fileName;

        user.setProfileImageUrl(fileUrl);
        userRepository.save(user);

        return fileUrl;
    }

    @Override
    public String updateProfileImage(MultipartFile file, String id) {
        User user = userRepository.findById(id)
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
        String newFileUrl = fileUtils.getStorageUrl() + "/" + fileUtils.getBucketName() + "/" + newFileName;

        user.setProfileImageUrl(newFileUrl);
        userRepository.save(user);

        return newFileUrl;
    }


    @Override
    public UserDTO join(UserDTO userDTO) {
        return null;
    }

    @Override
    public UserDTO getUserDTO(Integer userSeq) {
        Optional<User> user = userRepository.findById(String.valueOf(userSeq));
        if (user.isEmpty()) {
            log.warn("Travel with ID {} not found", userSeq);
            return null;
        }
        return user.get().toDTO();
    }

}
