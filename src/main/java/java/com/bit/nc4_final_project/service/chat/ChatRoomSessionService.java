package java.com.bit.nc4_final_project.service.chat;

public interface ChatRoomSessionService {
    void addSession(String chatRoomId, String sessionId);

    void removeSession(String sessionId);

    int getSessionCount(String chatRoomId);
}
