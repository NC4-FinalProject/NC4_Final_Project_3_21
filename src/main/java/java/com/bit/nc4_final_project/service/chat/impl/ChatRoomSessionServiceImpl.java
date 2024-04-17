package java.com.bit.nc4_final_project.service.chat.impl;

import com.bit.nc4_final_project.service.chat.ChatRoomSessionService;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class ChatRoomSessionServiceImpl implements ChatRoomSessionService {
    private final ConcurrentHashMap<String, Set<String>> chatRoomSessions = new ConcurrentHashMap<>();

    @Override
    public void addSession(String chatRoomId, String sessionId) {
        chatRoomSessions.computeIfAbsent(chatRoomId, k -> ConcurrentHashMap.newKeySet()).add(sessionId);
    }

    @Override
    public void removeSession(String sessionId) {
        chatRoomSessions.values().forEach(set -> set.remove(sessionId));
    }

    @Override
    public int getSessionCount(String chatRoomId) {
        return chatRoomSessions.getOrDefault(chatRoomId, Collections.emptySet()).size();
    }
}
