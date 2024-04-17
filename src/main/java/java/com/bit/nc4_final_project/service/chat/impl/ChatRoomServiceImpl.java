package java.com.bit.nc4_final_project.service.chat.impl;

import com.bit.nc4_final_project.document.chat.ChatMessage;
import com.bit.nc4_final_project.dto.chat.ChatMessageDTO;
import com.bit.nc4_final_project.entity.chat.Chat;
import com.bit.nc4_final_project.repository.chat.ChatRepository;
import com.bit.nc4_final_project.repository.chatroom.ChatRoomRepository;
import com.bit.nc4_final_project.service.chat.ChatRoomService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ChatRoomServiceImpl implements ChatRoomService {

    public final ChatRepository chatRepository;
    public final ChatRoomRepository chatRoomRepository;

    @Override
    public ChatMessageDTO saveMessage(ChatMessageDTO messageDTO) {
        String currentChatRoomId = messageDTO.getChatRoomId();

        Chat currentChat = chatRepository.findById(Integer.parseInt(currentChatRoomId)).orElse(null);
        log.info("find by message currentChat : {}", currentChat.toString());

        currentChat.setLastChat(messageDTO.getMessage());
        log.info("find builded currentChat : {}", currentChat.toString());
        ChatMessage chatMessage = messageDTO.toEntity();
        try {
            chatRoomRepository.save(chatMessage);
            log.info("try save currnetChat");
            chatRepository.save(currentChat);
        } catch (Exception e) {
            log.error("saveMessage error : {}", e.getMessage());
        }

        ChatMessageDTO returnMessageDTO = new ChatMessageDTO();
        returnMessageDTO.setChatRoomId(chatMessage.getChatRoomId());
        returnMessageDTO.setSender(chatMessage.getSender());
        returnMessageDTO.setMessage(chatMessage.getMessage());
        returnMessageDTO.setSendDate(chatMessage.getSendDate());

        return returnMessageDTO;
    }

    @Override
    public List<ChatMessageDTO> getMessages(String chatRoomId) {
        List<ChatMessage> chatMessageList = chatRoomRepository.findAllByChatRoomId(chatRoomId);
        List<ChatMessageDTO> chatMessageDTOList = chatMessageList.stream().map(ChatMessage::toDTO).toList();

        return chatMessageDTOList;
    }

    @Override
    public void deleteChatRoom(String chatRoomId) {
        Chat chat = chatRepository.findBySeq(Integer.parseInt(chatRoomId)).orElseThrow();
        chatRepository.delete(chat);
        chatRoomRepository.deleteAll(chatRoomRepository.findAllByChatRoomId(chatRoomId));
    }


}
