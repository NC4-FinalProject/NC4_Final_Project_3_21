package java.com.bit.nc4_final_project.repository.chat.impl;

import com.bit.nc4_final_project.entity.chat.Chat;
import com.bit.nc4_final_project.entity.chat.QChat;
import com.bit.nc4_final_project.repository.chat.ChatRepositoryCustom;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;

import java.util.List;


public class ChatRepositoryCustomImpl implements ChatRepositoryCustom {
    private final JPAQueryFactory queryFactory;
    public ChatRepositoryCustomImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public List<Chat> findAllByMakerNameOrPartnerName(String currentUserName) {
        QChat chat = QChat.chat;
        return queryFactory
                .selectFrom(chat)
                .where(chat.makerName.eq(currentUserName).or(chat.partnerName.eq(currentUserName)))
                .fetch();
    }
}
