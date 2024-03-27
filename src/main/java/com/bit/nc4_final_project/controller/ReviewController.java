package com.bit.nc4_final_project.controller;

import com.bit.nc4_final_project.common.FileUtils;
import com.bit.nc4_final_project.dto.ResponseDTO;
import com.bit.nc4_final_project.dto.recruitment.RecruitmentDTO;
import com.bit.nc4_final_project.dto.review.ReviewDTO;
import com.bit.nc4_final_project.service.review.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/review")
public class ReviewController {
    private final ReviewService reviewService;
    private final FileUtils fileUtils;

    @GetMapping("/list")
    public ResponseEntity<?> getReviewList(@PageableDefault(page = 0, size = 4) Pageable pageable,
                                           @RequestParam("searchCondition") String searchCondition,
                                           @RequestParam("searchKeyword") String searchKeyword,
                                           @RequestParam("sort") String sort) {
        Sort sortParameter  = Sort.by(Sort.Direction.DESC, "createdAt"); // 기본 정렬 기준
        if (sort.equals("latest")) {
            sortParameter = Sort.by(Sort.Direction.DESC, "createdAt");
        } else if (sort.equals("oldest")) {
            sortParameter = Sort.by(Sort.Direction.ASC, "createdAt");
        } else if (sort.equals("rating_high")) {
            sortParameter = Sort.by(Sort.Direction.DESC, "rating");
        } else if (sort.equals("rating_low")) {
            sortParameter = Sort.by(Sort.Direction.ASC, "rating");
        }

        pageable = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), sortParameter);

        ResponseDTO<ReviewDTO> responseDTO = new ResponseDTO<>();

        try {
            Page<ReviewDTO> reviewDTOPage = reviewService.searchAll(pageable,searchCondition, searchKeyword);

            responseDTO.setPageItems(reviewDTOPage);
            responseDTO.setItem(ReviewDTO.builder()
                    .searchCondition(searchCondition)
                    .searchKeyword(searchKeyword)
                    .build());
            responseDTO.setStatusCode(HttpStatus.OK.value());

            return ResponseEntity.ok(responseDTO);

        } catch (Exception e) {
            responseDTO.setErrorCode(201);
            responseDTO.setErrorMessage(e.getMessage());
            responseDTO.setStatusCode(HttpStatus.BAD_REQUEST.value());

            return ResponseEntity.badRequest().body(responseDTO);
        }
    }


    @PostMapping("/reg")
    public ResponseEntity<?> postReview(@RequestBody ReviewDTO reviewDTO,
                                        @PageableDefault(page = 0, size = 10) Pageable pageable) {
        System.out.println(reviewDTO.toString());
        ResponseDTO<ReviewDTO> responseDTO = new ResponseDTO<>();

        try {
            reviewService.post(reviewDTO);

            Page<ReviewDTO> recruitmentDTOPage = reviewService.searchAll(pageable, "all", "");

            responseDTO.setPageItems(recruitmentDTOPage);
            responseDTO.setStatusCode(HttpStatus.OK.value());

            return ResponseEntity.ok(responseDTO);
        } catch(Exception e) {
            responseDTO.setErrorCode(202);
            responseDTO.setErrorMessage(e.getMessage());
            responseDTO.setStatusCode(HttpStatus.BAD_REQUEST.value());

            return ResponseEntity.badRequest().body(responseDTO);
        }
    }

    @GetMapping("/{seq}")
    public ResponseEntity<?> getReview(@PathVariable("seq") int seq) {
        ResponseDTO<ReviewDTO> responseDTO = new ResponseDTO<>();

        try {
            ReviewDTO reviewDTO = reviewService.findById(seq);

            responseDTO.setItem(reviewDTO);
            responseDTO.setStatusCode(HttpStatus.OK.value());

            return ResponseEntity.ok(responseDTO);
        } catch(Exception e) {
            responseDTO.setErrorCode(203);
            responseDTO.setErrorMessage(e.getMessage());
            responseDTO.setStatusCode(HttpStatus.BAD_REQUEST.value());

            return ResponseEntity.badRequest().body(responseDTO);
        }
    }

    @PutMapping("/modify")
    public ResponseEntity<?> modify(@RequestPart("reviewDTO") ReviewDTO reviewDTO) {
        ResponseDTO<ReviewDTO> responseDTO = new ResponseDTO<>();

        try {
            reviewService.modify(reviewDTO);

            ReviewDTO modifiedReviewDTO = reviewService.findById(reviewDTO.getSeq());

            responseDTO.setItem(modifiedReviewDTO);
            responseDTO.setStatusCode(HttpStatus.OK.value());

            return ResponseEntity.ok(responseDTO);
        } catch(Exception e) {
            System.out.println(e.getMessage());
            responseDTO.setErrorCode(205);
            responseDTO.setErrorMessage(e.getMessage());
            responseDTO.setStatusCode(HttpStatus.BAD_REQUEST.value());

            return ResponseEntity.badRequest().body(responseDTO);
        }
    }

    @DeleteMapping("/{seq}")
    public ResponseEntity<?> removeReview(@PathVariable("seq") int seq,
                                         @PageableDefault(page = 0, size = 10) Pageable pageable) {
        ResponseDTO<ReviewDTO> responseDTO = new ResponseDTO<>();

        try {
            reviewService.deleteById(seq);

            responseDTO.setPageItems(reviewService.searchAll(pageable, "all", ""));
            responseDTO.setStatusCode(HttpStatus.OK.value());

            return ResponseEntity.ok(responseDTO);
        } catch(Exception e) {
            System.out.println(e.getMessage());
            responseDTO.setErrorCode(206);
            responseDTO.setErrorMessage(e.getMessage());
            responseDTO.setStatusCode(HttpStatus.BAD_REQUEST.value());

            return ResponseEntity.badRequest().body(responseDTO);
        }
    }
}
