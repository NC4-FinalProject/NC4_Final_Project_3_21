package com.bit.nc4_final_project.controller;


import com.bit.nc4_final_project.common.FileUtils;
import com.bit.nc4_final_project.dto.recruitment.RecruitmentDTO;
import com.bit.nc4_final_project.dto.ResponseDTO;
import com.bit.nc4_final_project.service.recruitment.RecruitmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/recruitment")
public class RecruitmentController {
    private final RecruitmentService recruitmentService;
    private final FileUtils fileUtils;

    @GetMapping("/recruitment-list")
    public ResponseEntity<?> getRecruitmentList(@RequestParam("searchCondition") String searchCondition,
                                                @RequestParam("searchKeyword") String searchKeyword) {
        ResponseDTO<RecruitmentDTO> responseDTO = new ResponseDTO<>();

        try {
            Page<RecruitmentDTO> recruitmentDTOPage = recruitmentService.searchAll(searchCondition, searchKeyword);

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
}
