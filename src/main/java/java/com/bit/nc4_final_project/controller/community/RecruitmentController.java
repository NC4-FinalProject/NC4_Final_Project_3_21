package java.com.bit.nc4_final_project.controller.community;


import com.bit.nc4_final_project.dto.ResponseDTO;
import com.bit.nc4_final_project.dto.recruitment.RecruitmentDTO;
import com.bit.nc4_final_project.entity.CustomUserDetails;
import com.bit.nc4_final_project.service.recruitment.RecruitmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/recruitment")
public class RecruitmentController {
    private final RecruitmentService recruitmentService;

    @GetMapping("/list")
    public ResponseEntity<?> getRecruitmentList(@PageableDefault(page = 0) Pageable pageable,
                                                @RequestParam("searchCondition") String searchCondition,
                                                @RequestParam("searchKeyword") String searchKeyword,
                                                @RequestParam("sort") String sort) {

        ResponseDTO<RecruitmentDTO> responseDTO = new ResponseDTO<>();

        try {
            Page<RecruitmentDTO> recruitmentDTOPage = recruitmentService.searchAll(pageable, searchCondition, searchKeyword, sort);

            responseDTO.setPageItems(recruitmentDTOPage);
            responseDTO.setItem(RecruitmentDTO.builder()
                    .searchCondition(searchCondition)
                    .searchKeyword(searchKeyword)
                    .sort(sort)
                    .build());
            responseDTO.setStatusCode(HttpStatus.OK.value());

            return ResponseEntity.ok(responseDTO);

        } catch (Exception e) {
            responseDTO.setErrorCode(301);
            responseDTO.setErrorMessage(e.getMessage());
            responseDTO.setStatusCode(HttpStatus.BAD_REQUEST.value());

            return ResponseEntity.badRequest().body(responseDTO);
        }
    }


    @PostMapping("/reg")
    public ResponseEntity<?> postRecruitment(@RequestBody RecruitmentDTO recruitmentDTO,
                                             @AuthenticationPrincipal CustomUserDetails customUserDetails) {
        ResponseDTO<RecruitmentDTO> responseDTO = new ResponseDTO<>();

        try {
            recruitmentService.post(recruitmentDTO, customUserDetails);

            responseDTO.setStatusCode(HttpStatus.OK.value());

            return ResponseEntity.ok(responseDTO);
        } catch (Exception e) {
            responseDTO.setErrorCode(302);
            responseDTO.setErrorMessage(e.getMessage());
            responseDTO.setStatusCode(HttpStatus.BAD_REQUEST.value());

            return ResponseEntity.badRequest().body(responseDTO);
        }
    }

    @GetMapping("/{seq}")
    public ResponseEntity<?> getRecruitment(@PathVariable("seq") int seq) {
        ResponseDTO<RecruitmentDTO> responseDTO = new ResponseDTO<>();

        try {
            RecruitmentDTO recruitmentDTO = recruitmentService.findById(seq);

            responseDTO.setItem(recruitmentDTO);
            responseDTO.setStatusCode(HttpStatus.OK.value());

            return ResponseEntity.ok(responseDTO);
        } catch (Exception e) {
            responseDTO.setErrorCode(303);
            responseDTO.setErrorMessage(e.getMessage());
            responseDTO.setStatusCode(HttpStatus.BAD_REQUEST.value());

            return ResponseEntity.badRequest().body(responseDTO);
        }
    }

    @PutMapping("/modify")
    public ResponseEntity<?> modify(@RequestBody RecruitmentDTO recruitmentDTO,
                                    @AuthenticationPrincipal CustomUserDetails customUserDetails) {
        ResponseDTO<RecruitmentDTO> responseDTO = new ResponseDTO<>();
        try {
            recruitmentService.modify(recruitmentDTO, customUserDetails);

            RecruitmentDTO modifiedRecruitmentDTO = recruitmentService.findById(recruitmentDTO.getSeq());

            responseDTO.setItem(modifiedRecruitmentDTO);
            responseDTO.setStatusCode(HttpStatus.OK.value());

            return ResponseEntity.ok(responseDTO);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            responseDTO.setErrorCode(305);
            responseDTO.setErrorMessage(e.getMessage());
            responseDTO.setStatusCode(HttpStatus.BAD_REQUEST.value());

            return ResponseEntity.badRequest().body(responseDTO);
        }
    }

    @DeleteMapping("/{seq}")
    public ResponseEntity<?> removeRecruitment(@PathVariable("seq") int seq,
                                               @PageableDefault(page = 0, size = 10) Pageable pageable) {
        ResponseDTO<RecruitmentDTO> responseDTO = new ResponseDTO<>();

        try {
            recruitmentService.deleteById(seq);

            responseDTO.setPageItems(recruitmentService.searchAll(pageable, "all", "", ""));
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
    public ResponseEntity<?> getMyRecruitment(@PageableDefault(page = 0, size = 4) Pageable pageable,
                                              @AuthenticationPrincipal CustomUserDetails customUserDetails) {

        ResponseDTO<RecruitmentDTO> responseDTO = new ResponseDTO<>();

        try {

            String userId = customUserDetails.getUserId();
            Page<RecruitmentDTO> recruitmentDTOPage = recruitmentService.getMyRecruitmentList(userId, pageable);

            responseDTO.setPageItems(recruitmentDTOPage);
            responseDTO.setItem(RecruitmentDTO.builder()
                    .build());
            responseDTO.setStatusCode(HttpStatus.OK.value());

            return ResponseEntity.ok(responseDTO);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            responseDTO.setErrorCode(307);
            responseDTO.setErrorMessage(e.getMessage());
            responseDTO.setStatusCode(HttpStatus.BAD_REQUEST.value());

            return ResponseEntity.badRequest().body(responseDTO);
        }
    }
}
