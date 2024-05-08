package com.bit.nc4_final_project.controller.travel;

import com.bit.nc4_final_project.dto.ResponseDTO;
import com.bit.nc4_final_project.dto.travel.BookmarkDTO;
import com.bit.nc4_final_project.entity.CustomUserDetails;
import com.bit.nc4_final_project.service.taravel.TravelService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/bookmark")
@RequiredArgsConstructor
public class BookmarkController {
    private final TravelService travelService;

    @GetMapping("/")
    public ResponseEntity<?> getBookmark(@AuthenticationPrincipal CustomUserDetails customUserDetails,
                                         @PageableDefault(page = 0, size = 8) Pageable pageable) {
        ResponseDTO<BookmarkDTO> responseDTO = new ResponseDTO<>();
        try {
            if (customUserDetails == null) {
                throw new RuntimeException("not signed in");
            }

            Page<BookmarkDTO> bookmarkDTOS = travelService.getMyBookmarkList(customUserDetails.getUserSeq(), pageable);
            responseDTO.setPageItems(bookmarkDTOS);
            responseDTO.setStatusCode(HttpStatus.OK.value());
            return ResponseEntity.ok(responseDTO);
        } catch (Exception e) {
            if (e.getMessage().equalsIgnoreCase("not signed in")) {
                responseDTO.setErrorCode(200);
                responseDTO.setErrorMessage(e.getMessage());
            } else {
                responseDTO.setErrorCode(403);
                responseDTO.setErrorMessage(e.getMessage());
            }
            responseDTO.setStatusCode(HttpStatus.BAD_REQUEST.value());

            return ResponseEntity.badRequest().body(responseDTO);
        }
    }

    @PostMapping("/")
    public ResponseEntity<?> regBookmark(@RequestBody Map<String, ?> requestBody,
                                         @AuthenticationPrincipal CustomUserDetails customUserDetails) {
        String id = (String) requestBody.get("id");
        boolean isReg = (boolean) requestBody.get("isReg");
        ResponseDTO<String> responseDTO = new ResponseDTO<>();
        try {
            if (isReg) {
                travelService.cancelBookmark(id, customUserDetails.getUserSeq());
            } else {
                travelService.regBookmark(id, customUserDetails.getUserSeq());
            }
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
