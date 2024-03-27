package com.bit.nc4_final_project.entity.travel;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QBookmark is a Querydsl query type for Bookmark
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QBookmark extends EntityPathBase<Bookmark> {

    private static final long serialVersionUID = 350392875L;

    public static final QBookmark bookmark = new QBookmark("bookmark");

    public final DateTimePath<java.time.LocalDateTime> bookmarkDate = createDateTime("bookmarkDate", java.time.LocalDateTime.class);

    public final NumberPath<Long> seq = createNumber("seq", Long.class);

    public final StringPath travelId = createString("travelId");

    public final NumberPath<Integer> userSeq = createNumber("userSeq", Integer.class);

    public QBookmark(String variable) {
        super(Bookmark.class, forVariable(variable));
    }

    public QBookmark(Path<? extends Bookmark> path) {
        super(path.getType(), path.getMetadata());
    }

    public QBookmark(PathMetadata metadata) {
        super(Bookmark.class, metadata);
    }

}

