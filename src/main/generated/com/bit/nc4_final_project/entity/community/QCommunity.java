package com.bit.nc4_final_project.entity.community;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QCommunity is a Querydsl query type for Community
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QCommunity extends EntityPathBase<Community> {

    private static final long serialVersionUID = -2144644091L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QCommunity community = new QCommunity("community");

    public final QCommunityTag communityTag;

    public final StringPath description = createString("description");

    public final NumberPath<Integer> member = createNumber("member", Integer.class);

    public final StringPath name = createString("name");

    public final StringPath picture = createString("picture");

    public final DateTimePath<java.time.LocalDateTime> regDate = createDateTime("regDate", java.time.LocalDateTime.class);

    public final NumberPath<Integer> seq = createNumber("seq", Integer.class);

    public final com.bit.nc4_final_project.entity.QUser user;

    public QCommunity(String variable) {
        this(Community.class, forVariable(variable), INITS);
    }

    public QCommunity(Path<? extends Community> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QCommunity(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QCommunity(PathMetadata metadata, PathInits inits) {
        this(Community.class, metadata, inits);
    }

    public QCommunity(Class<? extends Community> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.communityTag = inits.isInitialized("communityTag") ? new QCommunityTag(forProperty("communityTag"), inits.get("communityTag")) : null;
        this.user = inits.isInitialized("user") ? new com.bit.nc4_final_project.entity.QUser(forProperty("user")) : null;
    }

}

