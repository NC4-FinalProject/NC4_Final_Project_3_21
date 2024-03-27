package com.bit.nc4_final_project.service.chat.impl;

import org.springframework.stereotype.Service;

import com.bit.nc4_final_project.dto.chat.ChatMessageDTO;
import com.bit.nc4_final_project.repository.chat.ChatRepository;
import com.bit.nc4_final_project.service.chat.ChatService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ChatServiceImpl implements ChatService{

    public final ChatRepository chatRepository;

    @Override
    public void saveMessage(ChatMessageDTO messageDTO) {
        
    }
    
}
