package com.bit.nc4_final_project.controller.chat;

import org.springframework.web.bind.annotation.RestController;

import com.bit.nc4_final_project.dto.ResponseDTO;
import com.bit.nc4_final_project.dto.chat.ChatDTO;
import com.bit.nc4_final_project.service.chat.ChatService;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;



@RestController
@RequiredArgsConstructor
@RequestMapping("/chat")
public class ChatController {
    private final ChatService chatService;

    @GetMapping
    public ResponseEntity<?> getChatList(String userId) {
        ResponseDTO<ChatDTO> responseDTO = new ResponseDTO<>();

        try {
            List<ChatDTO> chatList = chatService.getChatList(userId);

        } catch (Exception e) {
            responseDTO.setErrorCode(100);
            responseDTO.setErrorMessage(e.getMessage());
            responseDTO.setStatusCode(400);
            return ResponseEntity.badRequest().body(responseDTO);
        }

        return ResponseEntity.ok("");
    }
    
}
