package com.bit.nc4_final_project.controller;

import com.bit.nc4_final_project.dto.ResponseDTO;
import com.bit.nc4_final_project.dto.travel.TravelDTO;
import com.bit.nc4_final_project.entity.travel.AreaCode;
import com.bit.nc4_final_project.entity.travel.SigunguCode;
import com.bit.nc4_final_project.service.taravel.TravelService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/travel")
@RequiredArgsConstructor
public class TravelController {
    private final TravelService travelService;

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
            for (SigunguCode sigunguCode : sigunguCodes) {
                log.info(sigunguCode.getCode());
            }
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
    public ResponseEntity<?> getBoardList(@RequestParam("searchArea") String searchArea,
                                          @RequestParam("searchSigungu") String searchSigungu,
                                          @RequestParam("searchKeyword") String searchKeyword,
                                          @RequestParam("sort") String sort) {
        ResponseDTO<TravelDTO> responseDTO = new ResponseDTO<>();

        try {
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

}
