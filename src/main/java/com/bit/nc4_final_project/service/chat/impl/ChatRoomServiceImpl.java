package com.bit.nc4_final_project.service.chat.impl;

import com.bit.nc4_final_project.entity.chat.ChatMessage;
import org.joda.time.LocalDateTime;
import org.springframework.stereotype.Service;

import com.bit.nc4_final_project.dto.chat.ChatMessageDTO;
import com.bit.nc4_final_project.repository.chat.ChatRoomRepository;
import com.bit.nc4_final_project.service.chat.ChatRoomService;

import lombok.RequiredArgsConstructor;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ChatRoomServiceImpl implements ChatRoomService {

    public final ChatRoomRepository chatRoomRepository;

    @Override
    public void saveMessage(ChatMessageDTO messageDTO) {
        ChatMessage chatMessage = new ChatMessage();
        chatMessage.setChatRoomId(messageDTO.getChatRoomId());
        chatMessage.setSender(messageDTO.getSender());
        chatMessage.setMessage(messageDTO.getMessage());
        chatMessage.setSendDate(LocalDateTime.now());
        chatRoomRepository.save(chatMessage);
    }

    @Override
    public List<ChatMessageDTO> getMessages(String chatRoomId) {
        List<ChatMessage> chatMessageList = chatRoomRepository.findAllByChatRoomId(chatRoomId);
        List<ChatMessageDTO> chatMessageDTOList = chatMessageList.stream().map(ChatMessage::toDTO).toList();

        return chatMessageDTOList;
    }

}
