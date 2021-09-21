package com.caudev.roadmap.restaurant;

import com.caudev.roadmap.place.Place;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
public interface RestaurantRepositoryExtension {
    Page<Restaurant> findWithSearchCond(String keyword, Pageable pageable);

    Page<Restaurant> findWithViewNumDesc(Pageable pageable);

    Page<Restaurant> findWithStarPointDesc(Pageable pageable);

}
