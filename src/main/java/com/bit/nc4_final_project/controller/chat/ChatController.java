package com.bit.nc4_final_project.controller.chat;

import org.springframework.web.bind.annotation.RestController;

import com.bit.nc4_final_project.dto.ResponseDTO;
import com.bit.nc4_final_project.dto.chat.ChatDTO;
import com.bit.nc4_final_project.dto.user.UserDTO;
import com.bit.nc4_final_project.service.chat.ChatService;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;




@RestController
@RequiredArgsConstructor
@RequestMapping("/chat")
public class ChatController {
    private final ChatService chatService;

    // 채팅 목록 조회, 친구 요청 목록 조회
    @GetMapping("/{userId}")
    public ResponseEntity<?> getChatList(@PathVariable("userId") String userId) {
        ResponseDTO<Map<String, Object>> responseDTO = new ResponseDTO<>();

        try {
            Map<String, Object> responseMap = new HashMap<>();
            
            List<ChatDTO> chatDTOList = chatService.getChatList(userId);
            // 나중에 friendDTOList도 추가해야 함

            responseMap.put("chatList", chatDTOList);
            
            responseDTO.setItem(responseMap);
            
            return ResponseEntity.ok("");
        } catch (Exception e) {
            responseDTO.setErrorCode(100);
            responseDTO.setErrorMessage(e.getMessage());
            responseDTO.setStatusCode(400);
            return ResponseEntity.badRequest().body(responseDTO);
        }
    }

    // @PostMapping("/newChat")
    // public ResponseEntity<?> newChat (@RequestBody UserDTO partnerDTO) {
    //     ResponseDTO <String> responseDTO = new ResponseDTO<>();

    //     try {
            
    //     } catch (Exception e) {
            
    //         return responseDTO.setErrorCode(0);
    //     }
        
        
    // }
    
}
