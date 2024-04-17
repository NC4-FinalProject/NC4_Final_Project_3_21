package com.bit.nc4_final_project.controller.travel;

import com.bit.nc4_final_project.document.travel.AreaCode;
import com.bit.nc4_final_project.document.travel.SigunguCode;
import com.bit.nc4_final_project.dto.ResponseDTO;
import com.bit.nc4_final_project.dto.travel.TravelDTO;
import com.bit.nc4_final_project.entity.CustomUserDetails;
import com.bit.nc4_final_project.service.taravel.TravelService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/travel")
@RequiredArgsConstructor
public class TravelController {
    private final TravelService travelService;

    @GetMapping("/{id}")
    public ResponseEntity<?> getTravel(@PathVariable("id") String id,
                                       @RequestParam("isIncreaseViewCnt") boolean isIncreaseViewCnt,
                                       @AuthenticationPrincipal CustomUserDetails customUserDetails) {
        ResponseDTO<TravelDTO> responseDTO = new ResponseDTO<>();
        try {
            if (isIncreaseViewCnt) {
                travelService.updateViewCnt(id);
            }
            TravelDTO travelDTO;
            if (customUserDetails == null) {
                travelDTO = travelService.getTravelDTO(id, null);
            } else {
                travelDTO = travelService.getTravelDTO(id, customUserDetails.getUserSeq());
            }
            responseDTO.setItem(travelDTO);
            responseDTO.setStatusCode(HttpStatus.OK.value());

            return ResponseEntity.ok(responseDTO);
        } catch (Exception e) {
            responseDTO.setErrorCode(403);
            responseDTO.setErrorMessage(e.getMessage());
            responseDTO.setStatusCode(HttpStatus.BAD_REQUEST.value());

            return ResponseEntity.badRequest().body(responseDTO);
        }
    }

    @PostMapping("/api")
    public ResponseEntity<?> saveTravelInfo() {
        ResponseDTO<TravelDTO> responseDTO = new ResponseDTO<>();
        try {
            travelService.save();
            responseDTO.setStatusCode(HttpStatus.OK.value());
            return ResponseEntity.ok(responseDTO);
        } catch (Exception e) {
            responseDTO.setErrorCode(100);
            responseDTO.setErrorMessage(e.getMessage());
            responseDTO.setStatusCode(HttpStatus.BAD_REQUEST.value());
            return ResponseEntity.badRequest().body(responseDTO);
        }
    }

    @PutMapping("/duplicate")
    public ResponseEntity<?> removeDuplicateContentIds() {
        ResponseDTO<TravelDTO> responseDTO = new ResponseDTO<>();
        try {
            travelService.removeDuplicateContentIds();
            responseDTO.setStatusCode(HttpStatus.OK.value());
            return ResponseEntity.ok(responseDTO);
        } catch (Exception e) {
            responseDTO.setErrorCode(100);
            responseDTO.setErrorMessage(e.getMessage());
            responseDTO.setStatusCode(HttpStatus.BAD_REQUEST.value());
            return ResponseEntity.badRequest().body(responseDTO);
        }
    }

    @GetMapping("/areaCode")
    public ResponseEntity<?> getAreaCode() {
        ResponseDTO<AreaCode> responseDTO = new ResponseDTO<>();
        try {
            List<AreaCode> areaCodes = travelService.getAreaCodes();
            responseDTO.setItems(areaCodes);
            responseDTO.setStatusCode(HttpStatus.OK.value());
            return ResponseEntity.ok(responseDTO);
        } catch (Exception e) {
            responseDTO.setErrorCode(100);
            responseDTO.setErrorMessage(e.getMessage());
            responseDTO.setStatusCode(HttpStatus.BAD_REQUEST.value());
            return ResponseEntity.badRequest().body(responseDTO);
        }
    }

    @PostMapping("/areaCode")
    public ResponseEntity<?> saveAreaCode() {
        ResponseDTO<TravelDTO> responseDTO = new ResponseDTO<>();
        try {
            travelService.saveAreaCodes();
            responseDTO.setStatusCode(HttpStatus.OK.value());
            return ResponseEntity.ok(responseDTO);
        } catch (Exception e) {
            responseDTO.setErrorCode(100);
            responseDTO.setErrorMessage(e.getMessage());
            responseDTO.setStatusCode(HttpStatus.BAD_REQUEST.value());
            return ResponseEntity.badRequest().body(responseDTO);
        }
    }

    @GetMapping("/sigunguCode")
    public ResponseEntity<?> getSigunguCode(@RequestParam("areaCode") String areaCode) {
        ResponseDTO<SigunguCode> responseDTO = new ResponseDTO<>();
        try {
            List<SigunguCode> sigunguCodes = travelService.getSigunguCodes(areaCode);
            responseDTO.setItems(sigunguCodes);
            responseDTO.setStatusCode(HttpStatus.OK.value());
            return ResponseEntity.ok(responseDTO);
        } catch (Exception e) {
            responseDTO.setErrorCode(100);
            responseDTO.setErrorMessage(e.getMessage());
            responseDTO.setStatusCode(HttpStatus.BAD_REQUEST.value());
            return ResponseEntity.badRequest().body(responseDTO);
        }
    }

    @GetMapping("/carousel")
    public ResponseEntity<?> getTravelInCarousel(@RequestParam(value = "searchArea", defaultValue = "") String searchArea,
                                                 @RequestParam(value = "searchSigungu", defaultValue = "") String searchSigungu,
                                                 @RequestParam(value = "searchKeyword", defaultValue = "") String searchKeyword,
                                                 @RequestParam(value = "sort", defaultValue = "") String sort,
                                                 @AuthenticationPrincipal CustomUserDetails customUserDetails) {
        ResponseDTO<TravelDTO> responseDTO = new ResponseDTO<>();
        try {
            if (sort.equals("bookmark")) {
                // 북마크 조회 추가
            }
            List<TravelDTO> travelDTOs = travelService.searchAllCarousel(searchArea, searchSigungu, searchKeyword, sort);
            responseDTO.setItems(travelDTOs);
            responseDTO.setItem(TravelDTO.builder()
                    .searchArea(searchArea)
                    .searchSigungu(searchSigungu)
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
    }

    @GetMapping("/marker")
    public ResponseEntity<?> getTravelAroundMap(@RequestParam(value = "userMapx") double userMapx,
                                                @RequestParam(value = "userMapy") double userMapy) {
        ResponseDTO<TravelDTO> responseDTO = new ResponseDTO<>();
        try {
            double radius = 3.0 / 111.0;
            double minMapx = userMapx - radius;
            double maxMapx = userMapx + radius;
            double minMapy = userMapy - radius;
            double maxMapy = userMapy + radius;

            List<TravelDTO> travelDTOs = travelService.findNearbyTravels(minMapx, maxMapx, minMapy, maxMapy);
            responseDTO.setItems(travelDTOs);
            responseDTO.setStatusCode(HttpStatus.OK.value());

            return ResponseEntity.ok(responseDTO);
        } catch (Exception e) {
            responseDTO.setErrorCode(401);
            responseDTO.setErrorMessage(e.getMessage());
            responseDTO.setStatusCode(HttpStatus.BAD_REQUEST.value());

            return ResponseEntity.badRequest().body(responseDTO);
        }
    }
}
