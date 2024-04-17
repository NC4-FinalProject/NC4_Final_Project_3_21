package java.com.bit.nc4_final_project.service.chat.impl;

import com.bit.nc4_final_project.dto.chat.ChatDTO;
import com.bit.nc4_final_project.dto.chat.ChatMakeInfo;
import com.bit.nc4_final_project.entity.chat.Chat;
import com.bit.nc4_final_project.repository.chat.ChatRepository;
import com.bit.nc4_final_project.repository.user.UserRepository;
import com.bit.nc4_final_project.service.chat.ChatService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ChatServiceImpl implements ChatService {
    private final ChatRepository chatRepository;
    private final UserRepository userRepository;

    @Override
    public List<ChatDTO> getChatList(String currentUserId) {
        String currentUserName = userRepository.findByUserId(currentUserId).get().getUserName();
        List<Chat> chatList = chatRepository.findAllByMakerNameOrPartnerName(currentUserName);
        List<ChatDTO> chatDTOList = chatList.stream().map(Chat::toDTO).toList();

        return chatDTOList;
    }

    // 새로운 채팅방 만들기
    @Override
    public List<ChatDTO> makeChatRoom(ChatMakeInfo chatMakeInfo) {
        log.info("makeChatRoom : " + chatMakeInfo.toString());
        try {
            String partnerName = userRepository.findByUserId(chatMakeInfo.getPartnerId()).orElseThrow(() -> new RuntimeException("사용자를 찾을 수 없습니다.")).getUserName();
            String partnerImg = userRepository.findByUserId(chatMakeInfo.getPartnerId()).orElseThrow().getProfileImageUrl();
            String makerName = userRepository.findByUserId(chatMakeInfo.getMakerId()).orElseThrow(() -> new RuntimeException("사용자를 찾을 수 없습니다.")).getUserName();
            String makerImg = userRepository.findByUserId(chatMakeInfo.getMakerId()).orElseThrow().getProfileImageUrl();
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

    // todo : 안읽은 메세지 구현
    @Override
    public void plusUnreadCnt(String chatRoomId) {
        log.info("===== plusUnreadCnt Arrived =====");
        Chat chat = chatRepository.findBySeq(Integer.parseInt(chatRoomId)).orElseThrow(() -> new RuntimeException("채팅방을 찾을 수 없습니다."));

        try {
            chat.setUnreadCnt(chat.getUnreadCnt() + 1);
            chatRepository.save(chat);
        } catch (Exception e) {
            log.error("plusUnreadCnt error : " + e.getMessage());
        }
    }

    @Override
    public void resetUnreadCnt(String chatRoomId) {
        Chat chat = chatRepository.findBySeq(Integer.parseInt(chatRoomId)).orElseThrow(() -> new RuntimeException("채팅방을 찾을 수 없습니다."));
        try {
            chat.setUnreadCnt(0);
            chatRepository.save(chat);
        } catch (Exception e) {
            log.error("plusUnreadCnt error : " + e.getMessage());
        }
    }

}
