package com.bit.nc4_final_project.service.chat.impl;

import org.springframework.stereotype.Service;

import com.bit.nc4_final_project.dto.chat.ChatMessageDTO;
import com.bit.nc4_final_project.repository.chat.ChatMessageRepository;
import com.bit.nc4_final_project.service.chat.ChatMessageService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ChatMessageServiceImpl implements ChatMessageService{

    public final ChatMessageRepository chatRepository;

    @Override
    public void saveMessage(ChatMessageDTO messageDTO) {
        
    }
    
}
