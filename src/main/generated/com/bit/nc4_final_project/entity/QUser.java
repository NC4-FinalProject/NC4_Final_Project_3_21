package com.bit.nc4_final_project.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QUser is a Querydsl query type for User
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QUser extends EntityPathBase<User> {

    private static final long serialVersionUID = 868216330L;

    public static final QUser user = new QUser("user");

    public final BooleanPath isActive = createBoolean("isActive");

    public final DateTimePath<java.time.LocalDateTime> lastLoginDate = createDateTime("lastLoginDate", java.time.LocalDateTime.class);

    public final StringPath profileImageUrl = createString("profileImageUrl");

    public final StringPath role = createString("role");

    public final NumberPath<Integer> seq = createNumber("seq", Integer.class);

    public final DateTimePath<java.time.LocalDateTime> userBirth = createDateTime("userBirth", java.time.LocalDateTime.class);

    public final StringPath userId = createString("userId");

    public final StringPath userName = createString("userName");

    public final StringPath userPw = createString("userPw");

    public final DateTimePath<java.time.LocalDateTime> userRegDate = createDateTime("userRegDate", java.time.LocalDateTime.class);

    public final StringPath userTel = createString("userTel");

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

