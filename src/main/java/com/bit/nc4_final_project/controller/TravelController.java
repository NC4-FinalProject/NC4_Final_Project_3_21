package com.bit.nc4_final_project.controller;

import com.bit.nc4_final_project.dto.ResponseDTO;
import com.bit.nc4_final_project.dto.travel.TravelDTO;
import com.bit.nc4_final_project.service.taravel.TravelService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
