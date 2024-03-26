package com.bit.nc4_final_project.entity.board;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QBoardComment is a Querydsl query type for BoardComment
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QBoardComment extends EntityPathBase<BoardComment> {

    private static final long serialVersionUID = 1385728672L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QBoardComment boardComment1 = new QBoardComment("boardComment1");

    public final QBoard board;

    public final QBoardComment boardComment;

    public final NumberPath<Integer> likeCnt = createNumber("likeCnt", Integer.class);

    public final DateTimePath<java.time.LocalDateTime> regDate = createDateTime("regDate", java.time.LocalDateTime.class);

    public final NumberPath<Integer> seq = createNumber("seq", Integer.class);

    public QBoardComment(String variable) {
        this(BoardComment.class, forVariable(variable), INITS);
    }

    public QBoardComment(Path<? extends BoardComment> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QBoardComment(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QBoardComment(PathMetadata metadata, PathInits inits) {
        this(BoardComment.class, metadata, inits);
    }

    public QBoardComment(Class<? extends BoardComment> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.board = inits.isInitialized("board") ? new QBoard(forProperty("board"), inits.get("board")) : null;
        this.boardComment = inits.isInitialized("boardComment") ? new QBoardComment(forProperty("boardComment"), inits.get("boardComment")) : null;
    }

}

