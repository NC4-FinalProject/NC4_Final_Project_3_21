package com.bit.nc4_final_project.controller.chat;

import com.bit.nc4_final_project.dto.ResponseDTO;
import com.bit.nc4_final_project.dto.chat.ChatMessageDTO;
import com.bit.nc4_final_project.service.chat.ChatRoomService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@Slf4j
@RestController
@RequiredArgsConstructor
public class ChatRoomController {
    public final ChatRoomService chatRoomService;
    public final SimpMessagingTemplate simpMessagingTemplate;

    @MessageMapping("/send-message")
    public ResponseEntity<?> sendMessage(ChatMessageDTO messageDTO) {
        ResponseDTO<String> responseDTO = new ResponseDTO<>();
        try {
            ChatMessageDTO returnChatMessageDTO = chatRoomService.saveMessage(messageDTO);
            simpMessagingTemplate.convertAndSend("/sub/" + returnChatMessageDTO.getChatRoomId(), returnChatMessageDTO);

            return ResponseEntity.ok("Message Send Success.");
        } catch (Exception e) {
            log.error("ChatRoomController : send-message error {}", e.getMessage());
            responseDTO.setErrorMessage(e.getMessage());
            responseDTO.setErrorCode(400);
            return ResponseEntity.badRequest().body(responseDTO);
        }

    }

    // 채팅방 목록에서 채팅방 입장시 읽어오기
    @GetMapping("/chat/{chatRoomId}")
    public ResponseEntity<?> getMessages(@PathVariable("chatRoomId") String chatRoomId) {

        ResponseDTO<ChatMessageDTO> responseDTO = new ResponseDTO<>();

        try {
            List<ChatMessageDTO> chatMessageDTOList = chatRoomService.getMessages(chatRoomId);

            responseDTO.setItems(chatMessageDTOList);

            return ResponseEntity.ok(responseDTO);
        } catch (Exception e) {
            responseDTO.setErrorMessage(e.getMessage());
            responseDTO.setErrorCode(400);
            return ResponseEntity.badRequest().body(responseDTO);
        }
    }

    @DeleteMapping("/chat/{chatRoomId}")
    public ResponseEntity<?> deleteChatRoom(@PathVariable("chatRoomId") String chatRoomId) {
        ResponseDTO<String> responseDTO = new ResponseDTO<>();
        try {
            chatRoomService.deleteChatRoom(chatRoomId);

            responseDTO.setItem("deleteChatRoom Success");
            responseDTO.setStatusCode(HttpStatus.OK.value());
            
            return ResponseEntity.ok(responseDTO);
        } catch (Exception e) {
            log.error("ChatRoomController : deleteChatRoom error {}", e.getMessage());
            responseDTO.setErrorMessage(e.getMessage());
            responseDTO.setErrorCode(400);
            return ResponseEntity.badRequest().body(responseDTO);
        }
    }

}
