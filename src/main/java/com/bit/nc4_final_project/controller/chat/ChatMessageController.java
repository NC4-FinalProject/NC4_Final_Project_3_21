package com.bit.nc4_final_project.controller.chat;

import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bit.nc4_final_project.dto.ResponseDTO;
import com.bit.nc4_final_project.dto.chat.ChatMessageDTO;
import com.bit.nc4_final_project.service.chat.ChatMessageService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;


@Slf4j
@RestController
@RequiredArgsConstructor
public class ChatMessageController {
    public final ChatMessageService chatService;
    public final SimpMessagingTemplate messagingTemplate;

    @MessageMapping("/chat")
    public ResponseEntity<?> recieveMessage (ChatMessageDTO messageDTO) {
        ResponseDTO<String> responseDTO = new ResponseDTO<>();
        log.info("Message Recieved: " + messageDTO.toString());
        try {
            chatService.saveMessage(messageDTO);

            messagingTemplate.convertAndSend("/sub/" + messageDTO.getChatId(), messageDTO);

            return ResponseEntity.ok("Message Send Success.");
        } catch (Exception e) {
            responseDTO.setErrorMessage(e.getMessage());
            responseDTO.setErrorCode(400);
            return ResponseEntity.badRequest().body(responseDTO);
        }

    }

    // 채팅 목록 읽어오기
    @GetMapping("/chat")
    public ResponseEntity<?> getChatList() {
        ResponseDTO<?> responseDTO = new ResponseDTO<>();
        try {

            return ResponseEntity.ok(responseDTO);
        } catch (Exception e) {
            responseDTO.setErrorMessage(e.getMessage());
            responseDTO.setErrorCode(400);
            return ResponseEntity.badRequest().body(responseDTO);
        }
    }
    

    
}
