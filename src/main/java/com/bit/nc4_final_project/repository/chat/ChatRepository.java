package com.bit.nc4_final_project.repository.chat;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bit.nc4_final_project.entity.chat.Chat;
public interface ChatRepository extends JpaRepository <Chat, Integer> {

    List<Chat> findAllByUserId(String userId);
}
