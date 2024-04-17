package java.com.bit.nc4_final_project.controller.travel;

import com.bit.nc4_final_project.dto.ResponseDTO;
import com.bit.nc4_final_project.dto.review.ReviewDTO;
import com.bit.nc4_final_project.entity.CustomUserDetails;
import com.bit.nc4_final_project.service.review.ReviewService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/review")
public class ReviewController {
    private final ReviewService reviewService;

    @GetMapping("/list")
    public ResponseEntity<?> getReviewList(@PageableDefault(page = 0, size = 4) Pageable pageable,
                                           @RequestParam("searchCondition") String searchCondition,
                                           @RequestParam("searchKeyword") String searchKeyword,
                                           @RequestParam("sort") String sort) {

        ResponseDTO<ReviewDTO> responseDTO = new ResponseDTO<>();

        try {
            Page<ReviewDTO> reviewDTOPage = reviewService.searchAll(pageable, searchCondition, searchKeyword, sort);

            responseDTO.setPageItems(reviewDTOPage);
            responseDTO.setItem(ReviewDTO.builder()
                    .searchCondition(searchCondition)
                    .searchKeyword(searchKeyword)
                    .sort(sort)
                    .build());
            responseDTO.setStatusCode(HttpStatus.OK.value());

            return ResponseEntity.ok(responseDTO);

        } catch (Exception e) {
            System.out.println(e.getMessage());
            responseDTO.setErrorCode(201);
            responseDTO.setErrorMessage(e.getMessage());
            responseDTO.setStatusCode(HttpStatus.BAD_REQUEST.value());

            return ResponseEntity.badRequest().body(responseDTO);
        }
    }


    @PostMapping("/reg")
    public ResponseEntity<?> postReview(@RequestBody ReviewDTO reviewDTO,
                                        @AuthenticationPrincipal CustomUserDetails customUserDetails) {
        ResponseDTO<ReviewDTO> responseDTO = new ResponseDTO<>();

        try {
            reviewService.post(reviewDTO, customUserDetails);

            responseDTO.setStatusCode(HttpStatus.OK.value());

            return ResponseEntity.ok(responseDTO);
        } catch (Exception e) {
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
        } catch (Exception e) {
            responseDTO.setErrorCode(203);
            responseDTO.setErrorMessage(e.getMessage());
            responseDTO.setStatusCode(HttpStatus.BAD_REQUEST.value());

            return ResponseEntity.badRequest().body(responseDTO);
        }
    }

    @PutMapping("/modify")
    public ResponseEntity<?> modify(@RequestBody ReviewDTO reviewDTO,
                                    @AuthenticationPrincipal CustomUserDetails customUserDetails) {
        ResponseDTO<ReviewDTO> responseDTO = new ResponseDTO<>();
        try {
            reviewService.modify(reviewDTO, customUserDetails);


            ReviewDTO modifiedReviewDTO = reviewService.findById(reviewDTO.getSeq());

            responseDTO.setItem(modifiedReviewDTO);
            responseDTO.setStatusCode(HttpStatus.OK.value());

            return ResponseEntity.ok(responseDTO);
        } catch (Exception e) {
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

            responseDTO.setPageItems(reviewService.searchAll(pageable, "all", "", ""));
            responseDTO.setStatusCode(HttpStatus.OK.value());

            return ResponseEntity.ok(responseDTO);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            responseDTO.setErrorCode(206);
            responseDTO.setErrorMessage(e.getMessage());
            responseDTO.setStatusCode(HttpStatus.BAD_REQUEST.value());

            return ResponseEntity.badRequest().body(responseDTO);
        }
    }

    @GetMapping("/my")
    public ResponseEntity<?> getMyReview(@PageableDefault(page = 0, size = 4) Pageable pageable,
                                         @AuthenticationPrincipal CustomUserDetails customUserDetails) {

        ResponseDTO<ReviewDTO> responseDTO = new ResponseDTO<>();

        try {

            String userId = customUserDetails.getUserId();
            Page<ReviewDTO> reviewDTOPage = reviewService.getMyReviewList(userId, pageable);

            responseDTO.setPageItems(reviewDTOPage);
            responseDTO.setItem(ReviewDTO.builder()
                    .build());
            responseDTO.setStatusCode(HttpStatus.OK.value());

            return ResponseEntity.ok(responseDTO);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            responseDTO.setErrorCode(207);
            responseDTO.setErrorMessage(e.getMessage());
            responseDTO.setStatusCode(HttpStatus.BAD_REQUEST.value());

            return ResponseEntity.badRequest().body(responseDTO);
        }
    }
}
