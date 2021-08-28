package com.caudev.roadmap.restaurant;

import com.caudev.roadmap.place.Place;
import com.querydsl.core.QueryResults;
import com.querydsl.jpa.JPQLQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import static com.caudev.roadmap.restaurant.QRestaurant.restaurant;


public class RestaurantRepositoryExtensionImpl extends QuerydslRepositorySupport implements RestaurantRepositoryExtension{

    public RestaurantRepositoryExtensionImpl() {
        super(Restaurant.class);
    }

    @Override
    public Page<Restaurant> findWithSearchCond(String keyword, Pageable pageable) {
        JPQLQuery<Restaurant> query = from(restaurant)
                .where(restaurant.name.containsIgnoreCase(keyword))
                .distinct();
        JPQLQuery<Restaurant> planQuery = getQuerydsl().applyPagination(pageable, query);
        QueryResults<Restaurant> result = planQuery.fetchResults();
        return new PageImpl<>(result.getResults(), pageable, result.getTotal());
    }

}
