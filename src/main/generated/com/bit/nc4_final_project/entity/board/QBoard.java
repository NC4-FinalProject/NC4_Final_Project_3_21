package com.bit.nc4_final_project.entity.board;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QBoard is a Querydsl query type for Board
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QBoard extends EntityPathBase<Board> {

    private static final long serialVersionUID = -1215449409L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QBoard board = new QBoard("board");

    public final ListPath<BoardFile, QBoardFile> boardFileList = this.<BoardFile, QBoardFile>createList("boardFileList", BoardFile.class, QBoardFile.class, PathInits.DIRECT2);

    public final com.bit.nc4_final_project.entity.community.QCommunity community;

    public final StringPath content = createString("content");

    public final DateTimePath<java.time.LocalDateTime> regDate = createDateTime("regDate", java.time.LocalDateTime.class);

    public final NumberPath<Integer> seq = createNumber("seq", Integer.class);

    public final com.bit.nc4_final_project.entity.QUser user;

    public QBoard(String variable) {
        this(Board.class, forVariable(variable), INITS);
    }

    public QBoard(Path<? extends Board> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QBoard(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QBoard(PathMetadata metadata, PathInits inits) {
        this(Board.class, metadata, inits);
    }

    public QBoard(Class<? extends Board> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.community = inits.isInitialized("community") ? new com.bit.nc4_final_project.entity.community.QCommunity(forProperty("community"), inits.get("community")) : null;
        this.user = inits.isInitialized("user") ? new com.bit.nc4_final_project.entity.QUser(forProperty("user")) : null;
    }

}

