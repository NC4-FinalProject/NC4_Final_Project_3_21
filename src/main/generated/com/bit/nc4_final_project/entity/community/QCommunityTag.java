package com.bit.nc4_final_project.entity.community;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QCommunityTag is a Querydsl query type for CommunityTag
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QCommunityTag extends EntityPathBase<CommunityTag> {

    private static final long serialVersionUID = 841464149L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QCommunityTag communityTag = new QCommunityTag("communityTag");

    public final QCommunity community;

    public final StringPath content = createString("content");

    public final NumberPath<Integer> seq = createNumber("seq", Integer.class);

    public QCommunityTag(String variable) {
        this(CommunityTag.class, forVariable(variable), INITS);
    }

    public QCommunityTag(Path<? extends CommunityTag> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QCommunityTag(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QCommunityTag(PathMetadata metadata, PathInits inits) {
        this(CommunityTag.class, metadata, inits);
    }

    public QCommunityTag(Class<? extends CommunityTag> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.community = inits.isInitialized("community") ? new QCommunity(forProperty("community"), inits.get("community")) : null;
    }

}

