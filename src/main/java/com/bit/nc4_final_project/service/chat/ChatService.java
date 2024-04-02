package com.bit.nc4_final_project.service.chat;

import java.util.List;

import com.bit.nc4_final_project.dto.chat.ChatDTO;
import com.bit.nc4_final_project.dto.chat.ChatMakeInfo;

public interface ChatService {
    List<ChatDTO> getChatList(String userId);

    List<ChatDTO> makeChatRoom (ChatMakeInfo chatMakeInfo);
}
