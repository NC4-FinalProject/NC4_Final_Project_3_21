package com.bit.nc4_final_project.entity.travel;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QSigunguCode is a Querydsl query type for SigunguCode
 */
@Generated("com.querydsl.codegen.DefaultEmbeddableSerializer")
public class QSigunguCode extends BeanPath<SigunguCode> {

    private static final long serialVersionUID = 1068256912L;

    public static final QSigunguCode sigunguCode = new QSigunguCode("sigunguCode");

    public final StringPath code = createString("code");

    public final StringPath name = createString("name");

    public QSigunguCode(String variable) {
        super(SigunguCode.class, forVariable(variable));
    }

    public QSigunguCode(Path<? extends SigunguCode> path) {
        super(path.getType(), path.getMetadata());
    }

    public QSigunguCode(PathMetadata metadata) {
        super(SigunguCode.class, metadata);
    }

}

