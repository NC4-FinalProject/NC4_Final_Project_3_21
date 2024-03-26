package com.bit.nc4_final_project.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QUserTag is a Querydsl query type for UserTag
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QUserTag extends EntityPathBase<UserTag> {

    private static final long serialVersionUID = 739714352L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QUserTag userTag = new QUserTag("userTag");

    public final StringPath content = createString("content");

    public final NumberPath<Integer> seq = createNumber("seq", Integer.class);

    public final QUser user;

    public QUserTag(String variable) {
        this(UserTag.class, forVariable(variable), INITS);
    }

    public QUserTag(Path<? extends UserTag> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QUserTag(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QUserTag(PathMetadata metadata, PathInits inits) {
        this(UserTag.class, metadata, inits);
    }

    public QUserTag(Class<? extends UserTag> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.user = inits.isInitialized("user") ? new QUser(forProperty("user")) : null;
    }

}

