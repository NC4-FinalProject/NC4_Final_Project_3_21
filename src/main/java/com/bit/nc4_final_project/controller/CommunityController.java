package com.bit.nc4_final_project.controller;

import com.bit.nc4_final_project.common.FileUtils;
import com.bit.nc4_final_project.dto.ResponseDTO;
import com.bit.nc4_final_project.dto.community.CommunityDTO;
import com.bit.nc4_final_project.service.community.CommunityService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/community")
public class CommunityController {
    private final CommunityService communityService;
    private final FileUtils fileUtils;

    /*@GetMapping("/list")
    public ResponseEntity<?> getCommunityList(@PageableDefault(page = 0, size = 10) Pageable pageable,
                                              @RequestParam("searchCondition") String searchCondition,
                                              @RequestParam("searchKeyword") String searchKeyword) {
        ResponseDTO<CommunityDTO> responseDTO = new ResponseDTO<>();

        try {
            Page<CommunityDTO> communityDTOPage = communityService.searchAll(pageable, searchCondition, searchKeyword);

            responseDTO.setPageItems(communityDTOPage);
            responseDTO.setItem(CommunityDTO.builder()
                    .searchCondition(searchCondition)
                    .searchKeyword(searchKeyword)
                    .build());
            responseDTO.setStatusCode(HttpStatus.OK.value());

            return ResponseEntity.ok(responseDTO);
        } catch (Exception e) {
            responseDTO.setErrorCode(401);
            responseDTO.setErrorMessage(e.getMessage());
            responseDTO.setStatusCode(HttpStatus.BAD_REQUEST.value());

            return ResponseEntity.badRequest().body(responseDTO);
        }
    }*/

    @PostMapping("/board")

    public ResponseEntity<?> postBoard(@RequestBody CommunityDTO communityDTO) {
        ResponseDTO<CommunityDTO> responseDTO = new ResponseDTO<>();

        try {
            communityService.post(communityDTO);
            responseDTO.setStatusCode(HttpStatus.OK.value());

            return ResponseEntity.ok(responseDTO);
        } catch (Exception e) {
            responseDTO.setErrorCode(402);
            responseDTO.setErrorMessage(e.getMessage());
            responseDTO.setStatusCode(HttpStatus.BAD_REQUEST.value());

            return ResponseEntity.badRequest().body(responseDTO);
        }
    }
}
