package com.caudev.roadmap.spot;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QSpot is a Querydsl query type for Spot
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QSpot extends EntityPathBase<Spot> {

    private static final long serialVersionUID = 1187653657L;

    public static final QSpot spot = new QSpot("spot");

    public final StringPath detail = createString("detail");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath Image = createString("Image");

    public final NumberPath<Double> location_x = createNumber("location_x", Double.class);

    public final NumberPath<Double> location_y = createNumber("location_y", Double.class);

    public final StringPath name = createString("name");

    public final NumberPath<Long> phoneNum = createNumber("phoneNum", Long.class);

    public QSpot(String variable) {
        super(Spot.class, forVariable(variable));
    }

    public QSpot(Path<? extends Spot> path) {
        super(path.getType(), path.getMetadata());
    }

    public QSpot(PathMetadata metadata) {
        super(Spot.class, metadata);
    }

}

