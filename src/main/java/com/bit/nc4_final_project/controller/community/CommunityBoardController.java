package com.bit.nc4_final_project.controller.community;

import com.bit.nc4_final_project.dto.ResponseDTO;
import com.bit.nc4_final_project.dto.board.BoardDTO;
import com.bit.nc4_final_project.entity.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/board")
public class CommunityBoardController {
    @PostMapping("/post")
    public ResponseEntity<?> post(@RequestPart("boardDTO") BoardDTO boardDTO,
                                  @RequestPart("uploadFiles") MultipartFile[] uploadFiles,
                                  @AuthenticationPrincipal CustomUserDetails customUserDetails) {
        ResponseDTO<BoardDTO> responseDTO = new ResponseDTO<>();

        try {
            log.info("boardDTO: {}", boardDTO);


            return ResponseEntity.ok(null);
        } catch (Exception e) {
            log.info("exception: {}", e.getMessage());
            responseDTO.setErrorCode(500); // 예외 상황에 대한 에러 코드
            responseDTO.setErrorMessage("Failed to modify the community post: " + e.getMessage());
            responseDTO.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseDTO);
        }
    }
}
