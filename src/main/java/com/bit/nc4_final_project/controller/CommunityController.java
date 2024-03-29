package com.bit.nc4_final_project.controller;

import com.bit.nc4_final_project.common.FileUtils;
import com.bit.nc4_final_project.dto.ResponseDTO;
import com.bit.nc4_final_project.dto.community.CommunityDTO;
import com.bit.nc4_final_project.dto.community.CommunityTagDTO;
import com.bit.nc4_final_project.service.community.CommunityService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/community")
public class CommunityController {
    private final CommunityService communityService;
    private final FileUtils fileUtils;

    @PostMapping("/reg")
    public ResponseEntity<?> postBoard(@RequestPart("community") CommunityDTO communityDTO,
                                       @RequestPart("tags") List<CommunityTagDTO> communityTagDTOList) {
        ResponseDTO<CommunityDTO> responseDTO = new ResponseDTO<>();
        System.out.println(communityDTO.toString());
        communityTagDTOList.forEach(communityTagDTO -> System.out.println(communityTagDTO.toString()));
        try {
            communityDTO.setTagDTOList(communityTagDTOList);

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
}
