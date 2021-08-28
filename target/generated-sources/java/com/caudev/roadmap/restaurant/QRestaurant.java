package com.caudev.roadmap.restaurant;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QRestaurant is a Querydsl query type for Restaurant
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QRestaurant extends EntityPathBase<Restaurant> {

    private static final long serialVersionUID = 1983099833L;

    public static final QRestaurant restaurant = new QRestaurant("restaurant");

    public final StringPath detail = createString("detail");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Double> location_x = createNumber("location_x", Double.class);

    public final NumberPath<Double> location_y = createNumber("location_y", Double.class);

    public final StringPath name = createString("name");

    public final NumberPath<Long> phoneNum = createNumber("phoneNum", Long.class);

    public final NumberPath<Long> viewNum = createNumber("viewNum", Long.class);

    public QRestaurant(String variable) {
        super(Restaurant.class, forVariable(variable));
    }

    public QRestaurant(Path<? extends Restaurant> path) {
        super(path.getType(), path.getMetadata());
    }

    public QRestaurant(PathMetadata metadata) {
        super(Restaurant.class, metadata);
    }

}

