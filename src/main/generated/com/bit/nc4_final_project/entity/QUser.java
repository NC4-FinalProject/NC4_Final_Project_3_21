package com.bit.nc4_final_project.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QUser is a Querydsl query type for User
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QUser extends EntityPathBase<User> {

    private static final long serialVersionUID = 868216330L;

    public static final QUser user = new QUser("user");

    public final DateTimePath<java.time.LocalDateTime> birth = createDateTime("birth", java.time.LocalDateTime.class);

    public final StringPath id = createString("id");

    public final BooleanPath isActive = createBoolean("isActive");

    public final DateTimePath<java.time.LocalDateTime> lastLoginDate = createDateTime("lastLoginDate", java.time.LocalDateTime.class);

    public final StringPath location = createString("location");

    public final StringPath nickname = createString("nickname");

    public final StringPath pw = createString("pw");

    public final DateTimePath<java.time.LocalDateTime> regDate = createDateTime("regDate", java.time.LocalDateTime.class);

    public final StringPath role = createString("role");

    public final NumberPath<Integer> seq = createNumber("seq", Integer.class);

    public final StringPath tel = createString("tel");

    public final ListPath<UserTag, QUserTag> userTags = this.<UserTag, QUserTag>createList("userTags", UserTag.class, QUserTag.class, PathInits.DIRECT2);

    public QUser(String variable) {
        super(User.class, forVariable(variable));
    }

    public QUser(Path<? extends User> path) {
        super(path.getType(), path.getMetadata());
    }

    public QUser(PathMetadata metadata) {
        super(User.class, metadata);
    }

}

