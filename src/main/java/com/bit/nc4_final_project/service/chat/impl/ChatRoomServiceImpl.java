package com.bit.nc4_final_project.service.chat.impl;

import com.bit.nc4_final_project.entity.chat.ChatMessage;
import lombok.extern.slf4j.Slf4j;
import org.joda.time.LocalDateTime;
import org.springframework.stereotype.Service;

import com.bit.nc4_final_project.dto.chat.ChatMessageDTO;
import com.bit.nc4_final_project.repository.chatroom.ChatRoomRepository;
import com.bit.nc4_final_project.service.chat.ChatRoomService;

import lombok.RequiredArgsConstructor;

import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class ChatRoomServiceImpl implements ChatRoomService {

    public final ChatRoomRepository chatRoomRepository;

    @Override
    public ChatMessageDTO saveMessage(ChatMessageDTO messageDTO) {
        log.info("ChatRoomService :  saveMessage Method arrived");

        ChatMessage chatMessage = new ChatMessage();
        chatMessage.setMessageId(UUID.randomUUID().toString());
        chatMessage.setChatRoomId(messageDTO.getChatRoomId());
        chatMessage.setSender(messageDTO.getSender());
        chatMessage.setMessage(messageDTO.getMessage());
        chatMessage.setSendDate(LocalDateTime.now());

        chatRoomRepository.save(chatMessage);

        ChatMessageDTO returnMessageDTO = new ChatMessageDTO();
        returnMessageDTO.setChatRoomId(chatMessage.getChatRoomId());
        returnMessageDTO.setSender(chatMessage.getSender());
        returnMessageDTO.setMessage(chatMessage.getMessage());
        returnMessageDTO.setSendDate(chatMessage.getSendDate().toString());

        log.info("ChatRoomService : returnMessageDTO : {}", returnMessageDTO.toString());
        return returnMessageDTO;
    }

//    @Override
//    public List<ChatMessageDTO> getMessages(String chatRoomId) {
//        List<ChatMessage> chatMessageList = chatRoomRepository.findAllByChatRoomId(chatRoomId);
//        List<ChatMessageDTO> chatMessageDTOList = chatMessageList.stream().map(ChatMessage::toDTO).toList();
//
//        return chatMessageDTOList;
//    }

}
