package com.bit.nc4_final_project.service.chat.impl;

import java.util.List;

import com.bit.nc4_final_project.dto.chat.ChatMakeInfo;
import com.bit.nc4_final_project.repository.user.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import com.bit.nc4_final_project.dto.chat.ChatDTO;
import com.bit.nc4_final_project.entity.chat.Chat;
import com.bit.nc4_final_project.repository.chat.ChatRepository;
import com.bit.nc4_final_project.service.chat.ChatService;

import lombok.RequiredArgsConstructor;

@Slf4j
@Service
@RequiredArgsConstructor
public class ChatServiceImpl implements ChatService {
    private final ChatRepository chatRepository;
    private final UserRepository userRepository;

    @Override
    public List<ChatDTO> getChatList(String currentUserId) {
        String currentUserName = userRepository.findById(currentUserId).get().getNickname();
        List<Chat> chatList = chatRepository.findAllByMakerNameOrPartnerName(currentUserName);
        List<ChatDTO> chatDTOList = chatList.stream().map(Chat::toDTO).toList();

        return chatDTOList;
    }

    // 새로운 채팅방 만들기
    @Override
    public List<ChatDTO> makeChatRoom(ChatMakeInfo chatMakeInfo) {
        try {
            String partnerName = userRepository.findById(chatMakeInfo.getPartnerId()).orElseThrow(() -> new RuntimeException("사용자를 찾을 수 없습니다.")).getNickname();
            String partnerImg = userRepository.findById(chatMakeInfo.getPartnerId()).orElseThrow().getProfileImageUrl();
            String makerName = userRepository.findById(chatMakeInfo.getMakerId()).orElseThrow(() -> new RuntimeException("사용자를 찾을 수 없습니다.")).getNickname();
            String makerImg = userRepository.findById(chatMakeInfo.getMakerId()).orElseThrow().getProfileImageUrl();
            ChatDTO chatDTO = ChatDTO.builder()
                    .makerName(makerName)
                    .makerImg(makerImg)
                    .partnerName(partnerName)
                    .partnerImg(partnerImg)
                    .lastChat("")
                    .unreadCnt(0)
                    .build();

            chatRepository.save(chatDTO.toEntity());

            List<ChatDTO> returnChatDTOList = getChatList(chatMakeInfo.getMakerId());
            return returnChatDTOList;
        } catch (Exception e) {
            log.error("makeChatRoom error : " + e.getMessage());
            return null;
        }
    }

    @Override
    public void updateUnreadCnt(String sender, String chatRoomId) {

    }

}
