package com.bit.nc4_final_project.service.chat;

import com.bit.nc4_final_project.dto.chat.ChatMessageDTO;

public interface ChatMessageService {

    void saveMessage(ChatMessageDTO messageDTO);
} 
