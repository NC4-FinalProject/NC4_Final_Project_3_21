package com.bit.nc4_final_project.controller;

import com.bit.nc4_final_project.common.FileUtils;
import com.bit.nc4_final_project.dto.ResponseDTO;
import com.bit.nc4_final_project.dto.community.CommunityDTO;
import com.bit.nc4_final_project.dto.community.CommunitySubscriberDTO;
import com.bit.nc4_final_project.dto.community.CommunityTagDTO;
import com.bit.nc4_final_project.entity.CustomUserDetails;
import com.bit.nc4_final_project.repository.user.UserRepository;
import com.bit.nc4_final_project.service.community.CommunityService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/community")
public class CommunityController {
    private final CommunityService communityService;
    private final FileUtils fileUtils;
    private final UserRepository userRepository;

    @PostMapping("/reg")
    public ResponseEntity<?> postBoard(@RequestPart("community") CommunityDTO communityDTO,
                                       @RequestPart("tags") List<CommunityTagDTO> communityTagDTOList,
                                       @RequestPart(value = "picture", required = false) MultipartFile picture,
                                       @AuthenticationPrincipal CustomUserDetails customUserDetails) {
//        log.info(String.valueOf(">> communityDTO : " + communityDTO.getUser().getSeq()));
        ResponseDTO<CommunityDTO> responseDTO = new ResponseDTO<>();
//        System.out.println(communityDTO.toString());
        communityTagDTOList.forEach(communityTagDTO -> log.info(">> communityTagDTO.toString() : " + communityTagDTO.toString()));
        try {
            communityDTO.setTags(communityTagDTOList);
            communityDTO.setUser(customUserDetails.getUser().toDTO());
//            communityDTO.setUserSeq(customUserDetails.getUser().getSeq());
//            UserDTO user = userRepository.findBySeq(1).toDTO();
//            log.info(">> user : " + user.getSeq());
//            communityDTO.setUser(user);
            // 파일 처리
            if (picture != null && !picture.isEmpty()) {
                // 여기에 파일을 처리하는 로직을 추가합니다.
                // 예를 들어, 파일을 서버에 저장하고, 그 경로를 CommunityDTO의 picture 필드에 설정할 수 있습니다.
                String picturePath = fileUtils.saveFile(picture); // fileUtils는 파일을 저장하는 유틸리티 클래스를 가정합니다.
                communityDTO.setPicture(picturePath);
            }

            communityService.post(communityDTO);
            responseDTO.setStatusCode(HttpStatus.OK.value());

            return ResponseEntity.ok(responseDTO);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            responseDTO.setErrorCode(402);
            responseDTO.setErrorMessage(e.getMessage());
            responseDTO.setStatusCode(HttpStatus.BAD_REQUEST.value());

            return ResponseEntity.badRequest().body(responseDTO);
        }
    }

    @GetMapping("/community/{seq}")
    public ResponseEntity<?> getCommunity(@PathVariable("seq") Integer seq) {
        ResponseDTO<CommunityDTO> responseDTO = new ResponseDTO<>();

        try {
            // 커뮤니티 서비스를 통해 ID에 해당하는 커뮤니티 정보 조회
            CommunityDTO communityDTO = communityService.findBySeq(seq);
//            log.info(">> dto " + communityDTO);
//            log.info(">> dto " + communityDTO.getName() + "," + communityDTO.getSeq());
            // 조회된 정보를 responseDTO에 설정
            responseDTO.setItem(communityDTO);
            responseDTO.setStatusCode(HttpStatus.OK.value());

            // 성공 응답 반환
            return ResponseEntity.ok(responseDTO);
        } catch (EntityNotFoundException e) {
            // 조회된 커뮤니티가 없을 경우 예외 처리
            responseDTO.setErrorCode(404);
            responseDTO.setErrorMessage("Community not found with id: " + seq);
            responseDTO.setStatusCode(HttpStatus.NOT_FOUND.value());

            // Not Found 응답 반환
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseDTO);
        } catch (Exception e) {
            // 그 외 예외 발생 시
            responseDTO.setErrorCode(500);
            responseDTO.setErrorMessage(e.getMessage());
            responseDTO.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());

            // 내부 서버 오류 응답 반환
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseDTO);
        }
    }

    // 기존 게시글 수정 처리
    @PutMapping("/modify")
    public ResponseEntity<?> modifyBoard(@RequestPart("community") CommunityDTO communityDTO,
                                         @RequestPart("tags") List<CommunityTagDTO> communityTagDTOList,
                                         @RequestPart("picture") MultipartFile picture,
                                         @AuthenticationPrincipal CustomUserDetails customUserDetails) {
        System.out.println("fff");
        ResponseDTO<CommunityDTO> responseDTO = new ResponseDTO<>();
        log.info(">> " + communityDTO.toString());
        log.info("communityDTO : " + communityDTO.getName());
        communityTagDTOList.forEach(tag -> System.out.println(tag.toString()));
        try {
            // 커뮤니티 게시글과 태그 리스트 정보 업데이트
            communityDTO.setTags(communityTagDTOList);

            if (picture != null && !picture.isEmpty()) {
                // 여기에 파일을 처리하는 로직을 추가합니다.
                // 예를 들어, 파일을 서버에 저장하고, 그 경로를 CommunityDTO의 picture 필드에 설정할 수 있습니다.
                String picturePath = fileUtils.saveFile(picture); // fileUtils는 파일을 저장하는 유틸리티 클래스를 가정합니다.
                communityDTO.setPicture(picturePath);
            }

            CommunityDTO updatedCommunityDTO = communityService.modify(communityDTO, customUserDetails.getUser());

            responseDTO.setItem(updatedCommunityDTO);
            responseDTO.setStatusCode(HttpStatus.OK.value());
            return ResponseEntity.ok(responseDTO);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            responseDTO.setErrorCode(500); // 예외 상황에 대한 에러 코드
            responseDTO.setErrorMessage("Failed to modify the community post: " + e.getMessage());
            responseDTO.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseDTO);
        }
    }

    @PostMapping("subscribe/{seq}")
    public ResponseEntity<?> subscribe(@PathVariable("seq") int seq,
                                       @AuthenticationPrincipal CustomUserDetails customUserDetails) {
        ResponseDTO<CommunitySubscriberDTO> responseDTO = new ResponseDTO<>();

        try {
            CommunitySubscriberDTO communitySubscriberDTO = communityService.subscribe(seq, customUserDetails.getUser());

            responseDTO.setItem(communitySubscriberDTO);

            responseDTO.setStatusCode(HttpStatus.OK.value());

            return ResponseEntity.ok(responseDTO);
        } catch (Exception e) {
            log.info("exception: {}", e.getMessage());
            responseDTO.setErrorCode(500); // 예외 상황에 대한 에러 코드
            responseDTO.setErrorMessage("Failed to modify the community post: " + e.getMessage());
            responseDTO.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseDTO);
        }
    }

    @DeleteMapping("/subscribe/{seq}")
    public ResponseEntity<?> cancelSubscribe(@PathVariable("seq") int seq,
                                             @AuthenticationPrincipal CustomUserDetails customUserDetails) {
        ResponseDTO<Map<String, String>> responseDTO = new ResponseDTO<>();

        try {
            communityService.cancelSubscribe(seq, customUserDetails.getUser());

            Map<String, String> returnMap = new HashMap<>();

            returnMap.put("msg", "cancel success");

            responseDTO.setItem(returnMap);

            responseDTO.setStatusCode(HttpStatus.OK.value());

            return ResponseEntity.ok(responseDTO);
        } catch (Exception e) {
            log.info("exception: {}", e.getMessage());
            responseDTO.setErrorCode(500); // 예외 상황에 대한 에러 코드
            responseDTO.setErrorMessage("Failed to modify the community post: " + e.getMessage());
            responseDTO.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseDTO);
        }
    }
}
