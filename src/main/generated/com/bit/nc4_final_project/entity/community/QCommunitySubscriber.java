package com.bit.nc4_final_project.entity.community;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QCommunitySubscriber is a Querydsl query type for CommunitySubscriber
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QCommunitySubscriber extends EntityPathBase<CommunitySubscriber> {

    private static final long serialVersionUID = -1482196403L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QCommunitySubscriber communitySubscriber = new QCommunitySubscriber("communitySubscriber");

    public final QCommunity community;

    public final com.bit.nc4_final_project.entity.QUser user;

    public QCommunitySubscriber(String variable) {
        this(CommunitySubscriber.class, forVariable(variable), INITS);
    }

    public QCommunitySubscriber(Path<? extends CommunitySubscriber> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QCommunitySubscriber(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QCommunitySubscriber(PathMetadata metadata, PathInits inits) {
        this(CommunitySubscriber.class, metadata, inits);
    }

    public QCommunitySubscriber(Class<? extends CommunitySubscriber> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.community = inits.isInitialized("community") ? new QCommunity(forProperty("community"), inits.get("community")) : null;
        this.user = inits.isInitialized("user") ? new com.bit.nc4_final_project.entity.QUser(forProperty("user")) : null;
    }

}

