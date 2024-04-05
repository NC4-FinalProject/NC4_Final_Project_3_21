package com.bit.nc4_final_project.controller.chat;

import com.bit.nc4_final_project.dto.ResponseDTO;
import com.bit.nc4_final_project.dto.chat.ChatImageMessage;
import com.bit.nc4_final_project.dto.chat.ChatMessageDTO;
import com.bit.nc4_final_project.service.chat.ChatRoomService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;


@Slf4j
@RestController
@RequiredArgsConstructor
public class ChatRoomController {
    private final ChatRoomService chatRoomService;
    private final SimpMessagingTemplate simpMessagingTemplate;
    private final ObjectMapper objectMapper;

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

    @PostMapping("/chatting/send-img")
    public ResponseEntity<?> sendImg(@RequestParam("image") MultipartFile image,
                                     @RequestParam("sendData") String sendData) {
        log.info("===== sendImg controller arrived =====");
        ResponseDTO<String> responseDTO = new ResponseDTO<>();
        try {
            log.info("===== sendImg controller try arrived =====");
            ChatImageMessage chatImageMessage = objectMapper.readValue(sendData, ChatImageMessage.class);
            Map<String, String> sendMap = chatImageMessage.toMap();
            chatRoomService.saveImg(image, sendMap);
            responseDTO.setStatusCode(HttpStatus.OK.value());
            return ResponseEntity.ok(responseDTO);
        } catch (Exception e) {
            log.error("ChatRoomController : sendImg error {}", e.getMessage());
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
