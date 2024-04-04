package com.bit.nc4_final_project.service.chat;

import com.bit.nc4_final_project.dto.chat.ChatDTO;
import com.bit.nc4_final_project.dto.chat.ChatMakeInfo;

import java.util.List;

public interface ChatService {
    List<ChatDTO> getChatList(String userId);

    List<ChatDTO> makeChatRoom(ChatMakeInfo chatMakeInfo);

    void plusUnreadCnt(String chatRoomId);

    void resetUnreadCnt(String chatRoomId);
}
