package com.bit.nc4_final_project.service.chat;

import com.bit.nc4_final_project.dto.chat.ChatMessageDTO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

public interface ChatRoomService {

    ChatMessageDTO saveMessage(ChatMessageDTO messageDTO);

    List<ChatMessageDTO> getMessages(String CurrentUserId);

    void deleteChatRoom(String chatRoomId);

    void saveImg(MultipartFile image, Map<String, String> sendMap);
} 
