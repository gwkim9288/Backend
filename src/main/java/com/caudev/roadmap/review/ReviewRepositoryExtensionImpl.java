package com.caudev.roadmap.review;

import com.caudev.roadmap.restaurant.Restaurant;
import com.caudev.roadmap.restaurant.RestaurantRepositoryExtension;
import com.querydsl.core.QueryResults;
import com.querydsl.jpa.JPQLQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import static com.caudev.roadmap.restaurant.QRestaurant.restaurant;
import static com.caudev.roadmap.review.QReview.review;

public class ReviewRepositoryExtensionImpl extends QuerydslRepositorySupport implements ReviewRepositoryExtension {

    public ReviewRepositoryExtensionImpl() {
        super(Review.class);
    }

    @Override
    public Page<Review> findWithSearchCond(String keyword, Pageable pageable) {
        JPQLQuery<Review> query = from(review)
                .where(review.content.containsIgnoreCase(keyword))
                .distinct();
        JPQLQuery<Review> reviewQuery = getQuerydsl().applyPagination(pageable, query);
        QueryResults<Review> result = reviewQuery.fetchResults();
        return new PageImpl<>(result.getResults(), pageable, result.getTotal());
    }
}
