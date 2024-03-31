package com.bit.nc4_final_project.service.chat;

import com.bit.nc4_final_project.dto.chat.ChatMessageDTO;

import java.util.List;

public interface ChatRoomService {

    void saveMessage(ChatMessageDTO messageDTO);

    List<ChatMessageDTO> getMessages(String CurrentUserId);
} 
