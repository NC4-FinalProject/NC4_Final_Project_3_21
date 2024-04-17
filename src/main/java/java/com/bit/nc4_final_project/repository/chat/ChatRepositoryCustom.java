package java.com.bit.nc4_final_project.repository.chat;

import com.bit.nc4_final_project.entity.chat.Chat;

import java.util.List;

public interface ChatRepositoryCustom {
    List<Chat> findAllByMakerNameOrPartnerName(String currentUserId);
}
