package com.bit.nc4_final_project.controller.chat;

import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bit.nc4_final_project.dto.ResponseDTO;
import com.bit.nc4_final_project.dto.chat.ChatMessageDTO;
import com.bit.nc4_final_project.service.chat.ChatMessageService;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequiredArgsConstructor
public class ChatMessageController {
    public final ChatMessageService chatService;
    public final SimpMessagingTemplate messagingTemplate;

    @MessageMapping("/chat/send")
    public ResponseEntity<?> recieveMessage (@RequestBody ChatMessageDTO messageDTO) {
        ResponseDTO<String> responseDTO = new ResponseDTO<>();

        try {
            chatService.saveMessage(messageDTO);

            messagingTemplate.convertAndSend("/topic/" + messageDTO.getReceiver());        

            return ResponseEntity.ok("Message Send Success.");
        } catch (Exception e) {
            responseDTO.setErrorMessage(e.getMessage());
            
            return ResponseEntity.badRequest().body(responseDTO);
        }

    }

    
}
