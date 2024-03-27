package com.bit.nc4_final_project.service.chat.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.bit.nc4_final_project.dto.chat.ChatDTO;
import com.bit.nc4_final_project.entity.chat.Chat;
import com.bit.nc4_final_project.repository.chat.ChatRepository;
import com.bit.nc4_final_project.service.chat.ChatService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ChatServiceImpl implements ChatService {
    private final ChatRepository chatRepository;

    @Override
    public List<ChatDTO> getChatList(String userId) {
        List<Chat> chatList = chatRepository.findAllByUserId(userId);
        List<ChatDTO> chatDTOList = chatList.stream().map(Chat::toDTO).toList();

        return chatDTOList;
    }
    
}
