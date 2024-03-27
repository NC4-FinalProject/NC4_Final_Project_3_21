// package com.bit.nc4_final_project.controller;

// import org.springframework.http.ResponseEntity;
// import org.springframework.messaging.handler.annotation.MessageMapping;
// import org.springframework.web.bind.annotation.RequestBody;
// import org.springframework.web.bind.annotation.RestController;

// import com.bit.nc4_final_project.dto.ResponseDTO;
// import com.bit.nc4_final_project.dto.chat.ChatMessageDTO;
// import com.bit.nc4_final_project.service.chat.ChatService;

// import lombok.RequiredArgsConstructor;
// import org.springframework.web.bind.annotation.RequestMapping;

// @RestController
// @RequiredArgsConstructor
// @RequestMapping("/chat")
// public class ChatController {
//     public final ChatService chatService;

//     @MessageMapping("/message")
//     public ResponseEntity<?> recieveMessage (@RequestBody ChatMessageDTO messageDTO) {
//         ResponseDTO<String> responseDTO = new ResponseDTO<>();

//         try {
//             chatService.saveMessage(messageDTO);

//             return ResponseEntity.ok("Message Transfer Success.");
//         } catch (Exception e) {
//             responseDTO.setErrorMessage(e.getMessage());
            
//             return ResponseEntity.badRequest().body(responseDTO);
//         }

//     }
// }
