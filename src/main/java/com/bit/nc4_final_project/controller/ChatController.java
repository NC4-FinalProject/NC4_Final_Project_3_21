package com.bit.nc4_final_project.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bit.nc4_final_project.dto.ResponseDTO;
import com.bit.nc4_final_project.dto.chat.ChatMessageDTO;
import com.bit.nc4_final_project.service.chat.ChatService;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequiredArgsConstructor
@RequestMapping("/chat")
public class ChatController {
    public final ChatService chatService;
    public final SimpMessagingTemplate messagingTemplate;

    @MessageMapping("/message")
    public ResponseEntity<?> recieveMessage (@RequestBody ChatMessageDTO messageDTO) {
        ResponseDTO<String> responseDTO = new ResponseDTO<>();

        try {
            chatService.saveMessage(messageDTO);

            messagingTemplate.convertAndSend("/sub/chat-room/" + messageDTO.getChannelId(), messageDTO);        

            return ResponseEntity.ok("Message Transfer Success.");
        } catch (Exception e) {
            responseDTO.setErrorMessage(e.getMessage());
            
            return ResponseEntity.badRequest().body(responseDTO);
        }

    }

    @GetMapping("/chat-room")
    public String getMethodName(@RequestParam String param) {
        return new String();
    }
    
}
