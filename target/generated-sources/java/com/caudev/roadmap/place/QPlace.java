package com.caudev.roadmap.place;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QPlace is a Querydsl query type for Place
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QPlace extends EntityPathBase<Place> {

    private static final long serialVersionUID = 1206485003L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QPlace place = new QPlace("place");

    public final StringPath detail = createString("detail");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath image = createString("image");

    public final NumberPath<Double> location_x = createNumber("location_x", Double.class);

    public final NumberPath<Double> location_y = createNumber("location_y", Double.class);

    public final StringPath name = createString("name");

    public final NumberPath<Long> phoneNum = createNumber("phoneNum", Long.class);

    public final com.caudev.roadmap.spot.QSpot spot;

    public QPlace(String variable) {
        this(Place.class, forVariable(variable), INITS);
    }

    public QPlace(Path<? extends Place> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QPlace(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QPlace(PathMetadata metadata, PathInits inits) {
        this(Place.class, metadata, inits);
    }

    public QPlace(Class<? extends Place> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.spot = inits.isInitialized("spot") ? new com.caudev.roadmap.spot.QSpot(forProperty("spot")) : null;
    }

}

