package com.bit.nc4_final_project.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QRecruitment is a Querydsl query type for Recruitment
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QRecruitment extends EntityPathBase<Recruitment> {

    private static final long serialVersionUID = -240871811L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QRecruitment recruitment = new QRecruitment("recruitment");

    public final com.bit.nc4_final_project.entity.community.QCommunity community;

    public final StringPath content = createString("content");

    public final DateTimePath<java.time.LocalDateTime> regDate = createDateTime("regDate", java.time.LocalDateTime.class);

    public final NumberPath<Integer> seq = createNumber("seq", Integer.class);

    public final StringPath title = createString("title");

    public final QUser user;

    public final StringPath writer = createString("writer");

    public QRecruitment(String variable) {
        this(Recruitment.class, forVariable(variable), INITS);
    }

    public QRecruitment(Path<? extends Recruitment> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QRecruitment(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QRecruitment(PathMetadata metadata, PathInits inits) {
        this(Recruitment.class, metadata, inits);
    }

    public QRecruitment(Class<? extends Recruitment> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.community = inits.isInitialized("community") ? new com.bit.nc4_final_project.entity.community.QCommunity(forProperty("community"), inits.get("community")) : null;
        this.user = inits.isInitialized("user") ? new QUser(forProperty("user")) : null;
    }

}

