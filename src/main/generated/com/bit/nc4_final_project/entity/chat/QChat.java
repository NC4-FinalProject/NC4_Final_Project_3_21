package com.bit.nc4_final_project.entity.chat;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QChat is a Querydsl query type for Chat
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QChat extends EntityPathBase<Chat> {

    private static final long serialVersionUID = 1436512655L;

    public static final QChat chat = new QChat("chat");

    public final StringPath lastChat = createString("lastChat");

    public final StringPath makerImg = createString("makerImg");

    public final StringPath makerName = createString("makerName");

    public final StringPath partnerImg = createString("partnerImg");

    public final StringPath partnerName = createString("partnerName");

    public final NumberPath<Integer> seq = createNumber("seq", Integer.class);

    public final NumberPath<Integer> unreadCnt = createNumber("unreadCnt", Integer.class);

    public QChat(String variable) {
        super(Chat.class, forVariable(variable));
    }

    public QChat(Path<? extends Chat> path) {
        super(path.getType(), path.getMetadata());
    }

    public QChat(PathMetadata metadata) {
        super(Chat.class, metadata);
    }

}

