package java.com.bit.nc4_final_project.service.user.impl;


import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.bit.nc4_final_project.common.FileUtils;
import com.bit.nc4_final_project.dto.user.UserDTO;
import com.bit.nc4_final_project.entity.User;
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
        User user = userRepository.save(userDTO.toEntity());
//        List<String> tags = userDTO.getTags();
//
//        tags.forEach(tagContent -> {
//            UserTag userTag = new UserTag();
//            userTag.setContent(tagContent);
//            user.addUserTag(userTag);
//        });

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
    public void deleteProfileImage(String userid) {
        User user = userRepository.findByUserId(userid)
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
    public String uploadProfileImage(MultipartFile file, String userid) {
        User user = userRepository.findByUserId(userid)
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
    public String updateProfileImage(MultipartFile file, String userid) {
        User user = userRepository.findByUserId(userid)
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
    public UserDTO modifyUser(String userid, UserDTO userDTO) {
        Optional<User> optionalUser = userRepository.findByUserId(userid);

        if (optionalUser.isEmpty()) {
            throw new RuntimeException("User not found");
        }

        User existingUser = optionalUser.get();

        if (!passwordEncoder.matches(userDTO.getUserPw(), existingUser.getUserPw())) {
            throw new RuntimeException("Incorrect current password");
        }

        User updatedUser = User.builder()
                .userId(existingUser.getUserId())
                .userPw(userDTO.getUserPw())
                .userName(userDTO.getUserName())
                .userTel(userDTO.getUserTel())
                .build();

        User savedUser = userRepository.save(updatedUser);
        UserDTO updatedUserDTO = savedUser.toDTO();

        return updatedUserDTO;
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

}
