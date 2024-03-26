package com.bit.nc4_final_project.controller;


import com.bit.nc4_final_project.common.FileUtils;
import com.bit.nc4_final_project.dto.ResponseDTO;
import com.bit.nc4_final_project.dto.recruitment.RecruitmentDTO;
import com.bit.nc4_final_project.service.recruitment.RecruitmentService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/recruitment")
public class RecruitmentController {
    private final RecruitmentService recruitmentService;
    private final FileUtils fileUtils;

    @GetMapping("/recruitment-list")
    public ResponseEntity<?> getRecruitmentList(@PageableDefault(page = 0, size = 10) Pageable pageable,
                                                @RequestParam("searchCondition") String searchCondition,
                                                @RequestParam("searchKeyword") String searchKeyword) {
        ResponseDTO<RecruitmentDTO> responseDTO = new ResponseDTO<>();

        try {
             Page<RecruitmentDTO> recruitmentDTOPage = recruitmentService.searchAll(pageable,searchCondition, searchKeyword);

            responseDTO.setPageItems(recruitmentDTOPage);
            responseDTO.setItem(RecruitmentDTO.builder()
                    .searchCondition(searchCondition)
                    .searchKeyword(searchKeyword)
                    .build());
            responseDTO.setStatusCode(HttpStatus.OK.value());

            return ResponseEntity.ok(responseDTO);

        } catch (Exception e) {
            responseDTO.setErrorCode(101);
            responseDTO.setErrorMessage(e.getMessage());
            responseDTO.setStatusCode(HttpStatus.BAD_REQUEST.value());

            return ResponseEntity.badRequest().body(responseDTO);
        }
    }


    @PostMapping("/recruitment")
    public ResponseEntity<?> postRecruitment(@RequestPart("recruitmentDTO") RecruitmentDTO recruitmentDTO,
                                       @PageableDefault(page = 0, size = 10) Pageable pageable) {
        ResponseDTO<RecruitmentDTO> responseDTO = new ResponseDTO<>();

        try {
            recruitmentService.post(recruitmentDTO);

            Page<RecruitmentDTO> recruitmentDTOPage = recruitmentService.searchAll(pageable, "all", "");

            responseDTO.setPageItems(recruitmentDTOPage);
            responseDTO.setStatusCode(HttpStatus.OK.value());

            return ResponseEntity.ok(responseDTO);
        } catch(Exception e) {
            responseDTO.setErrorCode(102);
            responseDTO.setErrorMessage(e.getMessage());
            responseDTO.setStatusCode(HttpStatus.BAD_REQUEST.value());

            return ResponseEntity.badRequest().body(responseDTO);
        }
    }


    @GetMapping("/recruitment/{seq}")
    public ResponseEntity<?> getRecruitment(@PathVariable("seq") int seq) {
        ResponseDTO<RecruitmentDTO> responseDTO = new ResponseDTO<>();

        try {
            RecruitmentDTO recruitmentDTO = recruitmentService.findById(seq);

            responseDTO.setItem(recruitmentDTO);
            responseDTO.setStatusCode(HttpStatus.OK.value());

            return ResponseEntity.ok(responseDTO);
        } catch(Exception e) {
            responseDTO.setErrorCode(103);
            responseDTO.setErrorMessage(e.getMessage());
            responseDTO.setStatusCode(HttpStatus.BAD_REQUEST.value());

            return ResponseEntity.badRequest().body(responseDTO);
        }
    }

    @PutMapping("/recruitment")
    public ResponseEntity<?> modify(@RequestPart("recruitmentDTO") RecruitmentDTO recruitmentDTO) {
        ResponseDTO<RecruitmentDTO> responseDTO = new ResponseDTO<>();

        try {
            recruitmentService.modify(recruitmentDTO);

            RecruitmentDTO modifiedRecruitmentDTO = recruitmentService.findById(recruitmentDTO.getSeq());

            responseDTO.setItem(modifiedRecruitmentDTO);
            responseDTO.setStatusCode(HttpStatus.OK.value());

            return ResponseEntity.ok(responseDTO);
        } catch(Exception e) {
            System.out.println(e.getMessage());
            responseDTO.setErrorCode(105);
            responseDTO.setErrorMessage(e.getMessage());
            responseDTO.setStatusCode(HttpStatus.BAD_REQUEST.value());

            return ResponseEntity.badRequest().body(responseDTO);
        }
    }


    @DeleteMapping("/recruitment/{seq}")
    public ResponseEntity<?> removeRecruitment(@PathVariable("seq") int seq,
                                         @PageableDefault(page = 0, size = 10) Pageable pageable) {
        ResponseDTO<RecruitmentDTO> responseDTO = new ResponseDTO<>();

        try {
            recruitmentService.deleteById(seq);

            responseDTO.setPageItems(recruitmentService.searchAll(pageable, "all", ""));
            responseDTO.setStatusCode(HttpStatus.OK.value());

            return ResponseEntity.ok(responseDTO);
        } catch(Exception e) {
            System.out.println(e.getMessage());
            responseDTO.setErrorCode(106);
            responseDTO.setErrorMessage(e.getMessage());
            responseDTO.setStatusCode(HttpStatus.BAD_REQUEST.value());

            return ResponseEntity.badRequest().body(responseDTO);
        }
    }
}
