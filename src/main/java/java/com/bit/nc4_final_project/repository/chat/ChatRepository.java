package java.com.bit.nc4_final_project.repository.chat;

import com.bit.nc4_final_project.entity.chat.Chat;
import com.bit.nc4_final_project.repository.chat.ChatRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ChatRepository extends JpaRepository<Chat, Integer>, ChatRepositoryCustom {

    Optional<Chat> findBySeq(int chatRoomId);
}
