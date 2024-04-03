package com.bit.nc4_final_project.repository.chat;

import com.bit.nc4_final_project.entity.chat.Chat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatRepository extends JpaRepository<Chat, Integer>, ChatRepositoryCustom {

    Chat findBySeq(Integer chatRoomId);
}
